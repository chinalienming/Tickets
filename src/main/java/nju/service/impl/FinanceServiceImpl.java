package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.TicketService;
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
    private TicketService ticketService ;

    @Autowired
    private UserInfoRepository userInfoRepository ;
    @Autowired
    private ExternalAccountRepository externalAccountRepository ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;
    @Autowired
    private PayMessageRepository payMessageRepository ;

    public double payByBalance(int recordID) {
        TicketRecord tr = ticketRecordRepository.findById(recordID).get() ;

        int userID = tr.getUserID() ;
        double total_price = tr.getPrice() ;
        int planID = tr.getPlanID() ;

        UserInfo userInfo = userInfoRepository.findById(userID).get() ;

        if( userInfo.getBalance() < total_price ) {
            return -1 ; //error code
        }

        boolean transferSuccess = requestInternalInterface(userID,planID,total_price) ;

        if(transferSuccess) {

            //about seat , record
            boolean changeStatus = ticketService.setPayed(recordID,SystemDefault.RECORD_PAYTYPE_BALANCE) ;

            if(!changeStatus)
                return -3;

            return total_price;

        }
        else {
            return -2;
        }
    }

    public double payByExternalAccount(int recordID,int accountID,String pwd) {
        TicketRecord tr = ticketRecordRepository.findById(recordID).get() ;

        if(tr.getIsValid()!=SystemDefault.RECORD_STATE_WAITPAY)
            return -5;

        double total_price = requestExternalInterface(accountID,pwd,tr.getUserID(),tr.getPlanID(),tr.getPrice());

        if(total_price>0) {

            //about seat , record
            boolean changeStatus = ticketService.setPayed(recordID,SystemDefault.RECORD_PAYTYPE_ALIPAY) ;

            if(!changeStatus)
                return -4;

            return total_price;
        }

        return total_price ;
    }

    /** Transfer money from user's account to site's account and add record to site and sitePlan.
     *  Since User balance is checked , user balance is always enough to pay.
     * */
    public double transfer2plan(int userID, int planID, int[] ticketNum) {
//        UserInfo userInfo = userInfoRepository.findById(userID).get() ;
//
//        SitePlan sitePlan = sitePlanRepository.findById(planID).get();
//
//
//        double original_price_A = sitePlan.getOriginal_price_A() ;
//        double original_price_B = sitePlan.getOriginal_price_B() ;
//        double original_price_C = sitePlan.getOriginal_price_C() ;
//        double[] original_price = {original_price_A,original_price_B,original_price_C} ;
//
//
////        double[] discountDetail = SystemDefault.switchDiscount(userInfo.getLevel());
//        double[] discountDetail = {1,1,1} ;
//
//        double total_price = 0.00 ;
//        for( int i=0 ; i<ticketNum.length ; i++ ) {
//            System.out.print(i+" "+ticketNum[i]+" "+original_price[i]+" "+discountDetail[i]+" , ");
//            total_price += ticketNum[i] * original_price[i] * discountDetail[i] ;
//        }
//
//        System.out.println("finance service total price : " +total_price);
//
//
//        boolean transferSuccess ;
//        if( userInfo.getBalance() < total_price ) {
//            //transferSuccess = requestExternalInterface(userID, planID , total_price) ;
//        } else {
//            transferSuccess = requestInternalInterface(userID, planID , total_price) ;
//        }
//
////        System.out.println("finance service transfer success: " + transferSuccess );
//
//        if(transferSuccess) {
//            return total_price;
//        }
//
        return -1.00;
    }

    public boolean requestInternalInterface(int userID, int planID, double total_price) {

        boolean removeFromUserSuccess = userService.removeBalance(userID, total_price);

        PayMessage pm = generatePayMessage(userID,SystemDefault.PM_INTERNAL_PAY,-total_price) ;

        boolean addToSitePlanSuccess = planService.addIncome(planID, userID, total_price);

        return removeFromUserSuccess && addToSitePlanSuccess ;
    }

    public double requestExternalInterface(int accountID,String password,int userID, int planID, double total_price){

        List<String> pil = getPaymentInterfaceList() ;
        String pi = requestPaymentPlatform(pil); //ask for using external interface

        if (null == pi)
            return -1;
        else {
            PaymentInterface ei = PaymentInterface.getInstance(pi);
            if (null == ei) {
                return -2;
            }

            //assume both user account and platform account have account on external payment platform
//            SitePlan sitePlan =  planService.getPlanByID(planID) ;
            ExternalAccount from_account = findExternalAccount(accountID, password);
            if (from_account == null) {
                return -3; //账号名或密码错误
            }
            //ExternalAccount to_account = findExternalAccount(sitePlan.getSiteID(),true) ;

            double transfer_amount =
                    ei.transfer(from_account, userID, planID, total_price);

            if (transfer_amount > 0) {

                PayMessage pm = generatePayMessage(userID,SystemDefault.PM_EXTERNAL_PAY,-total_price) ;
                externalAccountRepository.save(from_account);
                planService.addIncome(planID, userID, total_price);
            }
            return transfer_amount  ;
        }
    }

    /**
     * test
     */
    private String requestPaymentPlatform(List<String> pil){
        return pil.get(0) ;
    }

    public boolean cancelFromSite(TicketRecord tr,double return_amount) {

        boolean removeSuccess = planService.removeIncome(tr,return_amount) ;

        boolean drawbackSuccess = userService.drawback(tr.getUserID() ,return_amount,tr) ;

        PayMessage pm = generatePayMessage(tr.getUserID(),SystemDefault.PM_DRAWBACK,+return_amount) ;

        return removeSuccess && drawbackSuccess ;
    }

    public boolean transfer2Site(int planID,int accountID) {
        SitePlan sitePlan = planService.getPlanByID(planID) ;

        double amount = sitePlan.getPlanIncome() ;

        planService.cleanIncome(planID) ;

        ExternalAccount ea = externalAccountRepository.findByUserIDAndIsSite(accountID,true) ;

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

    public ExternalAccount findExternalAccount(int accountID,String pwd) {
//        System.out.println(accountID+" " +pwd) ;//

        return externalAccountRepository.findByAccountIDAndPassword(accountID,pwd) ;
    }

    public PayMessage generatePayMessage(int userID,String msg,double change) {
        PayMessage pm = new PayMessage() ;
        pm.setUserID(userID);
        pm.setMsg(msg);
        pm.setMoney(change);
        pm = payMessageRepository.save(pm) ;
        return pm ;
    }


}
