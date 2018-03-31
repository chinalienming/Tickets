package nju.service.impl;

import io.swagger.models.auth.In;
import nju.dao.*;
import nju.entity.*;
import nju.service.*;
import nju.util.Helper;
import nju.util.SystemDefault;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private UserService userService ;
    @Autowired
    private FinanceService financeService ;
    @Autowired
    private SiteService siteService ;
    @Autowired
    private PlanService planService ;

    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SiteRepository siteRepository ;
    @Autowired
    private SeatRepository seatRepository ;
    @Autowired
    private ManagerRepository managerRepository ;
    @Autowired
    private EditApplyRepository editApplyRepository;
    @Autowired
    private OpenApplyRepository openApplyRepository ;
    @Autowired
    private SiteAccountRepository siteAccountRepository ;
    @Autowired
    private SettlementRepository settlementRepository ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;
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
        int siteID = sitePlan1.getSiteID();
        Site site = siteService.getSiteInfo(siteID);
        int seat_A = site.getSeatNumber_A();
        int seat_B = site.getSeatNumber_B();
        int seat_C = site.getSeatNumber_C();
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

    public int login(int mID,String password) {
        Manager m = managerRepository.findById(mID).get() ;
        if( null == m )  return -1 ;

        if(!password.equals(m.getPassword())){
            return -2 ;
        }
        return 0 ;
    }

    public List<EditApply> getAllEditApply(int state,int page) {
        List<EditApply> result = new ArrayList<>() ;
        Page<EditApply> pages ;
        if (page >= 0) {
            pages = editApplyRepository.findByState(state,new PageRequest(page , 10)) ; //每页10个
            pages.forEach(result::add);
        } else {
            editApplyRepository.findByState(state).forEach(result::add);
        }
        return result;
    }

    public List<OpenApply> getAllOpenApply(int state,int page) {
        List<OpenApply> result = new ArrayList<>() ;
        Page<OpenApply> pages ;
        if (page >= 0) {
            pages = openApplyRepository.findByState(state,new PageRequest(page , 10)) ; //每页10个
            pages.forEach(result::add);
        } else {
            openApplyRepository.findByState(state).forEach(result::add);
        }
        return result;
    }

    public Map<String, Object> approve(boolean approve, boolean openOrModify, List<Integer> applicationId) {
        int status = 0;
        if (approve) {
            status = 1;
        } else {
            status = 2;
        }
       // final int finalStatus = status; // in lambda expression must be final or effectively final

        if (openOrModify) {

            for(int id : applicationId ) {
                System.out.println(id);
                OpenApply entity = openApplyRepository.findById(id).get();
                int siteID = entity.getSiteID() ;
                SiteAccount sa = siteAccountRepository.findById(siteID).get() ;
                sa.setActive(true);
                siteAccountRepository.save(sa);

                entity.setState(SystemDefault.APPLY_STATE_APPROVE);
                openApplyRepository.save(entity) ;
            }

        } else {

            for(int id : applicationId ) {
                EditApply entity = editApplyRepository.findById(id).get();
                int siteID = entity.getSiteID() ;
                Site site = siteRepository.findBySiteID(siteID) ;
                site.setSiteName(entity.getNewName());
                site.setAddress(entity.getNewAdd());
                site.setSeatNumber_A(entity.getNum_a());
                site.setSeatNumber_B(entity.getNum_b());
                site.setSeatNumber_C(entity.getNum_c());
                siteRepository.save(site) ;

                entity.setState(SystemDefault.APPLY_STATE_APPROVE);
                editApplyRepository.save(entity) ;
            }
        }

        Map<String, Object> result = new TreeMap<>();
        result.put(SystemDefault.HTTP_RESULT, true);

        return result;
    }

    public int getAllApplyNum() {
        int t  ;
        List<EditApply> result1 = new ArrayList<>() ;
        List<OpenApply> result2 = new ArrayList<>() ;
        editApplyRepository.findByState(SystemDefault.APPLY_STATE_WAIT).forEach(result1::add);
        openApplyRepository.findByState(SystemDefault.APPLY_STATE_WAIT).forEach(result2::add);
        t = result1.size() + result2.size() ;
//        System.out.println("Tttttt: "+t) ;
        return  t ;
    }

    public Map<String, Object> doSettlement( List<Integer> operationArray) {

        for(int planID:operationArray) {
            SitePlan sitePlan = planService.getPlanByID(planID) ;
            int siteID = sitePlan.getSiteID() ;
            Site site = siteService.getSiteInfo(siteID) ;
            double planIncome = sitePlan.getPlanIncome() ;
            double settleAmount = planIncome*SystemDefault.SETTLEMENT_RATE ;

            Settlement settlement = new Settlement() ;
            settlement.setPrice(settleAmount);
            settlement.setPlanID(planID);
            settlement.setSiteID(siteID);

            settlementRepository.save(settlement) ;

            sitePlan.setPlanIncome(0);
            site.setIncome(site.getIncome()+(planIncome-settleAmount));
            sitePlanRepository.save(sitePlan);
            siteRepository.save(site);

        }

        Map<String, Object> result = new TreeMap<>();
        result.put(SystemDefault.HTTP_RESULT, true);


        return result;
    }

    public Map<String,Object> getSiteStatus() {

        Map<String, Object> result = new TreeMap<>();

        List<Site> sites =siteService.getAllSite() ;


        List<String> nameList = new ArrayList<>(sites.size());
        List<Integer> cashList = new ArrayList<>(sites.size()) ;
        List<Integer> balanceList = new ArrayList<>(sites.size()) ;
        List<Integer> alipayList = new ArrayList<>(sites.size()) ;

        int index = 0 ;

        for(Site site:sites) {
            index ++;
            if (index > 20) {
                break;
            }
            List<Integer> planIDList = planService.getPlanIDBySiteID(site.getSiteID()) ;

            int[] total_get = new int[3] ;

            for(int planID:planIDList) {

                List<TicketRecord> trs0 = ticketRecordRepository.findByPlanIDAndPayType(planID,SystemDefault.RECORD_PAYTYPE_CASH) ;
                trs0.forEach(tr->{
                    total_get[0]+=tr.getPrice();
                });

                List<TicketRecord> trs1 = ticketRecordRepository.findByPlanIDAndPayType(planID,SystemDefault.RECORD_PAYTYPE_BALANCE) ;
                trs1.forEach(tr->{
                    total_get[1]+=tr.getPrice();
                });

                List<TicketRecord> trs2 = ticketRecordRepository.findByPlanIDAndPayType(planID,SystemDefault.RECORD_PAYTYPE_ALIPAY) ;
                trs2.forEach(tr->{
                    total_get[2]+=tr.getPrice();
                });

            }

            nameList.add(site.getSiteName()) ;
            cashList.add(total_get[0]);
            balanceList.add(total_get[1]);
            alipayList.add(total_get[2]);
        }


        result.put(SystemDefault.HTTP_RESULT, true);
        result.put("nameList",nameList) ;
        result.put("cash",cashList);
        result.put("balance",balanceList);
        result.put("alipay",alipayList);
        return result;
    }


    public Map<String, Object> getMemberStatus() {

        Map<String, Object> result = new TreeMap<>();

        List<Integer> allUserIDList = userService.getAllUserID() ;

        List<String> userIDList = new ArrayList<>();
        List<Integer> cashList = new ArrayList<>();
        List<Integer> balanceList = new ArrayList<>();
        List<Integer> alipayList = new ArrayList<>();

        int index = 0;
        for (int userID : allUserIDList) {

            index++;
            if (index > 40) {
                break;
            }
            int[] total_get = new int[3] ;


            List<TicketRecord> trs0 = ticketRecordRepository.findByUserIDAndPayType(userID,SystemDefault.RECORD_PAYTYPE_CASH) ;
            trs0.forEach(tr->{
                total_get[0]+=tr.getPrice();
            });

            List<TicketRecord> trs1 = ticketRecordRepository.findByUserIDAndPayType(userID,SystemDefault.RECORD_PAYTYPE_BALANCE) ;
            trs1.forEach(tr->{
                total_get[1]+=tr.getPrice();
            });

            List<TicketRecord> trs2 = ticketRecordRepository.findByUserIDAndPayType(userID,SystemDefault.RECORD_PAYTYPE_ALIPAY) ;
            trs2.forEach(tr->{
                total_get[2]+=tr.getPrice();
            });

            userIDList.add(String.format("%07d" , userID));
            cashList.add(total_get[0]);
            balanceList.add(total_get[1]);
            alipayList.add(total_get[2]);

        }

        result.put(SystemDefault.HTTP_RESULT, true);
        result.put("userIDList",userIDList) ;
        result.put("cash",cashList);
        result.put("balance",balanceList);
        result.put("alipay",alipayList);
        return result;
    }

    public Map<String, Object> getFinanceStatus() {
        Map<String, Object> result = new TreeMap<>();

        List<String> date = new ArrayList<>();
        List<Integer> cashList = new ArrayList<>() ;
        List<Integer> balanceList = new ArrayList<>() ;
        List<Integer> alipayList = new ArrayList<>();

        Map<String, Integer> cashMap = new TreeMap<>();
        Map<String, Integer> balanceMap = new TreeMap<>();
        Map<String, Integer> alipayMap = new TreeMap<>();

        List<TicketRecord> trs0 = ticketRecordRepository.findByPayType(SystemDefault.RECORD_PAYTYPE_CASH);
        List<TicketRecord> trs1 = ticketRecordRepository.findByPayType(SystemDefault.RECORD_PAYTYPE_BALANCE);
        List<TicketRecord> trs2 = ticketRecordRepository.findByPayType(SystemDefault.RECORD_PAYTYPE_ALIPAY);

        List<TicketRecord> tr3 = new ArrayList<>();
        tr3.addAll(trs0) ;
        tr3.addAll(trs1) ;
        tr3.addAll(trs2) ;

        for(TicketRecord tr:tr3) {
            String liveDate = Helper.timeToDateString(tr.getCreateTime());
            if( ! cashMap.containsKey(liveDate) ) {
                cashMap.put(liveDate,0);
                balanceMap.put(liveDate,0);
                alipayMap.put(liveDate,0);
            }

            switch (tr.getPayType()) {
                case SystemDefault.RECORD_PAYTYPE_CASH   :     cashMap.put(liveDate,cashMap.get(liveDate)    + (int)tr.getPrice()  ) ; break ;
                case SystemDefault.RECORD_PAYTYPE_BALANCE:  balanceMap.put(liveDate,balanceMap.get(liveDate) + (int)tr.getPrice()  ) ; break ;
                case SystemDefault.RECORD_PAYTYPE_ALIPAY :   alipayMap.put(liveDate,alipayMap.get(liveDate)  + (int)tr.getPrice()  ) ; break ;
            }
        }


        for (String eachDate : cashMap.keySet()) {
            date.add(eachDate);
            cashList.add(cashMap.get(eachDate));
            balanceList.add(balanceMap.get(eachDate));
            alipayList.add(alipayMap.get(eachDate));
        }


        result.put(SystemDefault.HTTP_RESULT, true);
        result.put("date",date) ;
        result.put("cash",cashList);
        result.put("balance",balanceList);
        result.put("alipay",alipayList);
        return result;
    }
}
