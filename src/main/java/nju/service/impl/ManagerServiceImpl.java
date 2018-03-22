package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.FinanceService;
import nju.service.ManagerService;
import nju.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private FinanceService financeService ;
    @Autowired
    private SiteService siteService ;

    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SiteRepository siteRepository ;
    @Autowired
    private SeatRepository seatRepository ;
//    @Autowired
//    private ExternalAccountRepository externalAccountRepository ;


    /**
     * 更新table`site`
     */
    public boolean examineRequest(Site site){
        Site site1 = siteRepository.save(site) ;
        return true ;
    }

    /**
     * 插入table `siteplan` 和 table `seat`
     */
    public boolean examineRequest(SitePlan sitePlan) {
        SitePlan sitePlan1 = sitePlanRepository.save(sitePlan) ;

        int planID = sitePlan1.getPlanID() ;

        int seat_A = sitePlan1.getSeat_A() ;
        int seat_B = sitePlan1.getSeat_B() ;
        int seat_C = sitePlan1.getSeat_C() ;
        int[] seats = new int[]{seat_A,seat_B,seat_C};

        char type = 'A' ;
        for(int i = 0 ; i< seats.length; i++ ) {
            type = (char)(type + i) ;
            for(int j = 0; j < seats[i] ; j ++ ) {
                Seat seat ;
                if(j>=9) {
                    seat = new Seat(planID, type + "" + (j + 1));
                } else {
                    seat = new Seat(planID, type + "0" + (j + 1));
                }
                seatRepository.save(seat) ;
            }
        }

        return true ;
    }


    //结算 , SitePlan.planIncome -> site account
    //比例自行设计
    public boolean cleanPlanIncome(int planID,int siteID){
        return financeService.transfer2Site(planID,siteID) ;
    }


    //会员统计
    public List<UserInfo> getUserStatics() {
        return userInfoRepository.findAll() ;
    }

    //Tickets财务情况
    //返回所有场馆的收入报表
    public Map<Integer,Double> getTotalStatics(){
        //select all from table `site`
        List<Integer> siteID_list = siteService.getAllSiteID();

        Map<Integer,Double> allSiteStatics = new HashMap<>() ;   // <siteID,Income>

        Map<Integer,Double> singleSiteStatics ; // <planID,planIncome>

        double singleSiteIncome = 0.00 ;

        for(int siteID : siteID_list){
            singleSiteStatics = getSiteStatics(siteID) ;

            Iterator<Map.Entry<Integer,Double>> it = singleSiteStatics.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<Integer,Double> tmp = it.next() ;
                singleSiteIncome += tmp.getValue() ;
            }
            allSiteStatics.put(siteID,singleSiteIncome);
        }

        return allSiteStatics;
    }

    //Tickets财务情况搜集:各场馆统计
    //返回一个场馆所有计划的报表
    public Map<Integer,Double> getSiteStatics(int siteID){

        //select from table `siteplan` by siteID
        List<SitePlan> allSitePlan = sitePlanRepository.findBySiteID(siteID) ;

        //<planID , income>
        Map<Integer,Double> siteStatics = new HashMap<>();

        for(SitePlan sitePlan : allSitePlan) {
            siteStatics.put(sitePlan.getPlanID(),sitePlan.getPlanIncome()) ;
        }

        return siteStatics ;
    }
}
