package nju.service.impl;

import nju.dao.ExternalAccountRepository;
import nju.dao.SitePlanRepository;
import nju.dao.UserInfoRepository;
import nju.entity.ExternalAccount;
import nju.entity.SitePlan;
import nju.entity.TicketRecord;
import nju.entity.UserInfo;
import nju.service.external.PaymentInterface;
import nju.service.FinanceService;
import nju.service.PlanService;
import nju.service.UserService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private UserService userService ;
    @Autowired
    private PlanService planService ;

    @Autowired
    private UserInfoRepository userInfoRepository ;
    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private ExternalAccountRepository externalAccountRepository ;




    /** Transfer money from user's account to site's account and add record to site and sitePlan.
     *  Since User balance is checked , user balance is always enough to pay.
     * */
    public double transfer2plan(int userID, int planID, int[] ticketNum) {

        UserInfo userInfo = userInfoRepository.findById(userID).get() ;

        SitePlan sitePlan = sitePlanRepository.findById(planID).get();


        double original_price_A = sitePlan.getOriginal_price_A() ;
        double original_price_B = sitePlan.getOriginal_price_B() ;
        double original_price_C = sitePlan.getOriginal_price_C() ;
        double[] original_price = {original_price_A,original_price_B,original_price_C} ;


//        double[] discountDetail = SystemDefault.switchDiscount(userInfo.getLevel());
        double[] discountDetail = {1,1,1} ;

        double total_price = 0.00 ;
        for( int i=0 ; i<ticketNum.length ; i++ ) {
            System.out.print(i+" "+ticketNum[i]+" "+original_price[i]+" "+discountDetail[i]+" , ");
            total_price += ticketNum[i] * original_price[i] * discountDetail[i] ;
        }

        System.out.println("finance service total price : " +total_price);


        boolean transferSuccess ;
        if( userInfo.getBalance() < total_price ) {
            transferSuccess = requestExternalInterface(userID, planID , total_price) ;
        } else {
            transferSuccess = requestInternalInterface(userID, planID , total_price) ;
        }

        System.out.println("finance service transfer success: " + transferSuccess );

        if(transferSuccess) {
            return total_price;
        }
        else {
            return -1.00;
        }
    }

    public boolean requestInternalInterface(int userID, int planID, double total_price) {

        boolean removeFromUserSuccess = userService.removeBalance(userID, total_price);

        boolean addToSitePlanSuccess = planService.addIncome(planID, userID, total_price);

        return removeFromUserSuccess && addToSitePlanSuccess ;
    }

    public boolean requestExternalInterface(int userID, int planID, double total_price){

        List<String> pil = getPaymentInterfaceList() ;

        String pi = requestPaymentPlatform(userID, pil); //ask for using external interface

        if (null == pi)
            return false;
        else {
            PaymentInterface ei = PaymentInterface.getInstance(pi);
            if(null==ei) {
                return false ;
            }

            //assume both user account and platform account have account on external payment platform
            SitePlan sitePlan =  planService.getPlanByID(planID) ;
            ExternalAccount from_account = findExternalAccount(userID,false) ;
            ExternalAccount to_account = findExternalAccount(sitePlan.getSiteID(),true) ;

            boolean external_deal_success =
                    ei.transfer( from_account , to_account , total_price );

            boolean addToSitePlanSuccess ;
            if(external_deal_success) {
                addToSitePlanSuccess = planService.addIncome(planID, userID, total_price);
            } else {
                return false ;
            }
            return external_deal_success && addToSitePlanSuccess ;
        }
    }

    /**
     * test
     */
    private String requestPaymentPlatform(int userID,List<String> pil){
        return pil.get(0) ;
    }

    public boolean cancelFromSite(TicketRecord tr,double return_amount) {

        boolean removeSuccess = planService.removeIncome(tr,return_amount) ;

        boolean drawbackSuccess = userService.drawback(tr.getUserID() ,return_amount,tr) ;

        return removeSuccess && drawbackSuccess ;
    }

    public boolean transfer2Site(int planID,int siteID) {
        SitePlan sitePlan = planService.getPlanByID(planID) ;

        double amount = sitePlan.getPlanIncome() ;

        planService.cleanIncome(planID) ;

        ExternalAccount ea = findExternalAccount(siteID,true) ;

        ea.setBalance(ea.getBalance()+amount);

        externalAccountRepository.save(ea) ;

        return true ;
    }

    public List<String> getPaymentInterfaceList() {
        List<String> result = new ArrayList<>() ;
        result.add("Alipay") ;
        result.add("OnlineBank");
        return result ;
    }

    public ExternalAccount findExternalAccount(int userID,boolean isSite) {
        return externalAccountRepository.findByUserIDAndIsSite(userID,isSite) ;
    }


}
