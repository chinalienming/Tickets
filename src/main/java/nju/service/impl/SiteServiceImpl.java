package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.PlanService;
import nju.service.SiteService;
import nju.service.TicketService;
import nju.util.Helper;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PlanService planService ;
    @Autowired
    private TicketService ticketService ;

    @Autowired
    private SiteRepository siteRepository ;
    @Autowired
    private SiteAccountRepository siteAccountRepository ;
    @Autowired
    private EditApplyRepository editApplyRepository ;
    @Autowired
    private OpenApplyRepository openApplyRepository ;
//    @Autowired
//    private PlanApplyRepository planApplyRepository ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;
    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private SeatRepository seatRepository ;

    public List<Integer> getAllSiteID () {
        List<Integer> list = new ArrayList<>() ;
        siteRepository.findAll().forEach(
                site -> {
                    list.add(site.getSiteID());
                }
        );
        return list ;
    }

//    public boolean subscribeSitePlan(SitePlan sitePlan){
//        return managerService.examineRequest(sitePlan);
//    }
//
//
//    public boolean subscribeSiteInfo(Site site){
//        return managerService.examineRequest(site) ;
//    }

    //return siteID
//    public int registerSite(Site site){
//
//        if(site.getSiteID() > 0 )
//            return -1 ;  //already registered
//
//        boolean nameExist = checkSiteNameExist(site.getSiteName()) ;
//        if(nameExist)
//            return -2 ;  //siteName Exist
//
////        boolean subscribe_pass = subscribeSiteInfo(site);
////
////        if(!subscribe_pass)
////            return -3;
//
//        Site site_getID = siteRepository.save(site);
//        return site_getID.getSiteID() ;
//    }

    public boolean checkSiteNameExist(String siteName ){
        return siteRepository.existsBySiteName(siteName) ;
    }

    public List<Site> getAllSite() {
        return siteRepository.findAll() ;
    }

    public List<Site> getSiteByPage(int page) {
        List<Site> result = new ArrayList<>() ;
        Page<Site> pages ;
        if (page >= 0) {
            pages = siteRepository.findAll
                    (new PageRequest(page, SystemDefault.SIZE_PER_PAGE_OF_SITE));
            pages.forEach(result::add);
        } else {
            siteRepository.findAll().forEach(result::add);
        }
        return result;
    }

    public Site getSiteInfo(int siteID) {
        return siteRepository.findBySiteID(siteID) ;
    }

    public Site getSiteByPlanID(int planID) {
        SitePlan sitePlan = planService.getPlanByID(planID) ;
        int siteID = sitePlan.getSiteID() ;
        return siteRepository.findBySiteID(siteID) ;
    }

    public int register(String siteName,String password) {
        SiteAccount sa = new SiteAccount() ;
        sa.setName(siteName);
        sa.setPassword(password);
//        sa.setActive(true);   //
        sa = siteAccountRepository.save(sa) ;

        //site
        Site site = new Site() ;
        site.setSiteName(siteName);
        site.setSiteID(sa.getSiteID());
        siteRepository.save(site) ;

        System.out.println(" site account id : " + sa.getSiteID());
        return sa.getSiteID() ;
    }

    public int login(int siteID,String password) {

        SiteAccount sa = siteAccountRepository.findById(siteID).get() ;
        if( null == sa )  return -1 ;

        if(!password.equals(sa.getPassword())){
            return -2 ;
        }
        return 0 ;
    }

    public void saveModifyApplication(int siteID , String name, String address,int num_a,int num_b,int num_c) {
        EditApply ea = new EditApply() ;
        ea.setSiteID(siteID);
        ea.setNewName(name);
        ea.setNewAdd(address);
        ea.setNum_a(num_a);
        ea.setNum_b(num_b);
        ea.setNum_c(num_c);
        editApplyRepository.save(ea) ;
    }

    public void saveOpenApplication(int siteID ,String reason) {
        Site site = getSiteInfo(siteID) ;
        String name = site.getSiteName() ;

        OpenApply oa = new OpenApply() ;
        oa.setSiteID(siteID);
        oa.setReason(name+":"+reason);
        openApplyRepository.save(oa) ;
    }

    public boolean isApplyingForOpen(int siteId) {
        return openApplyRepository.existsBySiteIDAndState(siteId,0) ;
    }

    public boolean isApplyingForEdit(int siteId) {
        return editApplyRepository.existsBySiteIDAndState(siteId,0) ;
    }

    public SiteAccount getSiteAccount(int siteID) { return siteAccountRepository.findById(siteID).get() ; }

    public void applyPlan(int siteID, String description, String planType , String beginTime , String endTime ,
                          double price_a ,double price_b ,double price_c) {

        SiteAccount sa = getSiteAccount(siteID) ;
        if(sa.getActive()==false) return ;

        System.out.println(beginTime) ;
        System.out.println(endTime) ;

        SitePlan sitePlan = new SitePlan(siteID,planType,description,beginTime,endTime,price_a,price_b,price_c) ;
        sitePlan = sitePlanRepository.save(sitePlan) ;
        int planID = sitePlan.getPlanID();
        System.out.println("planID:"+planID);

        //add seats
        Site site = getSiteInfo(siteID);
        int num_a = site.getSeatNumber_A();
        int num_b = site.getSeatNumber_B();
        int num_c = site.getSeatNumber_C();
        int[] num = {num_a,num_b,num_c} ;

        for(int p=0;p<num.length;p++) {
            char type = (char) ('A' + p);
            for (int i = 1; i <= num[p]; i++) {
                String seatNum = String.format("%02d", i);
                seatNum = type + seatNum;
                System.out.println(seatNum);
                Seat seat = new Seat(planID, seatNum);
                seatRepository.save(seat);
            }
        }
//        PlanApply pa = new PlanApply
//                (siteID,planType,description,beginTime,endTime,price_a,price_b,price_c) ;

//        planApplyRepository.save(pa) ;

    }

    public List<TicketRecord> getSiteTRList(int siteID) {
        List<TicketRecord> result = ticketRecordRepository.findBySiteIDAndPayType(siteID,SystemDefault.RECORD_PAYTYPE_CASH) ;
        Collections.reverse(result);
        return result ;
    }

    public int doBuyOffline(int planID,int userID, int seatA,int seatB,int seatC ) {
        return ticketService.buyTicketOffline(planID,userID,seatA,seatB,seatC) ;
    }

    public Map<String,Object> getRecentIncome(int siteID) {

        List<TicketRecord> trs = ticketRecordRepository.findBySiteID(siteID) ;

        Map<String, Object> result = new TreeMap<>();
        if (!trs.isEmpty()) {
            result.put(SystemDefault.HTTP_RESULT, true);

            Map<String, Integer> dateAndNumber = new TreeMap<>();

            trs.forEach(entity -> {
                String date = Helper.timeToDateString(entity.getCreateTime());
                if (dateAndNumber.containsKey(date)) {
                    dateAndNumber.put(date, dateAndNumber.get(date) + 1);
                } else {
                    dateAndNumber.put(date, 1);
                }
            });
            result.put("data", dateAndNumber);
        } else {
            result.put(SystemDefault.HTTP_RESULT, false);
            result.put(SystemDefault.HTTP_REASON, "没有数据");
        }
        return result;
    }

    public Map<String,Object> getCancelStatistics(int siteID) {

        List<TicketRecord> trs = ticketRecordRepository.
                findBySiteIDAndIsValid(siteID,SystemDefault.RECORD_STATE_CANCEL) ;

        Map<String, Object> result = new TreeMap<>();
        if (!trs.isEmpty()) {
            result.put(SystemDefault.HTTP_RESULT, true);
            Map<String, Integer> dateAndNumber = new TreeMap<>();
            trs.forEach(entity -> {
                String date = Helper.timeToDateString(entity.getCreateTime());
                if (dateAndNumber.containsKey(date)) {
                    dateAndNumber.put(date, dateAndNumber.get(date) + 1);
                } else {
                    dateAndNumber.put(date, 1);
                }
            });
            result.put("data", dateAndNumber);
        } else {
            result.put(SystemDefault.HTTP_RESULT, false);
            result.put(SystemDefault.HTTP_REASON, "没有数据");
        }
        return result;
    }

    public Map<String,Object> getConsumption(int siteID) {


        List<TicketRecord> trs = ticketRecordRepository.findBySiteID(siteID) ;

        Map<String, Object> result = new TreeMap<>();


        if (!trs.isEmpty()) {
            result.put(SystemDefault.HTTP_RESULT, true);

            Map<String, Integer> typeAndNumber = new TreeMap<>();

            typeAndNumber.put("balance", 0);
            typeAndNumber.put("cash", 0);
            typeAndNumber.put("alipay", 0);

            trs.forEach(entity -> {
                switch (entity.getPayType()) {
                    case 0:
                        typeAndNumber.put("cash", typeAndNumber.get("cash") + 1);
                        break;
                    case 1:
                        typeAndNumber.put("balance", typeAndNumber.get("balance") + 1);
                        break;
                    case 2:
                        typeAndNumber.put("alipay", typeAndNumber.get("alipay") + 1);
                        break;
                }


            });
            result.put("data", typeAndNumber);


        } else {
            result.put(SystemDefault.HTTP_RESULT, false);
            result.put(SystemDefault.HTTP_REASON, "没有数据");
        }

        return result;
    }

    public Map<String,Object> check(int recordID) {
        Map<String, Object> result = new TreeMap<>();

        TicketRecord tr = ticketRecordRepository.findById(recordID).get() ;
        if(tr.getIsValid()!=SystemDefault.RECORD_STATE_PAYED)
        {
            result.put("result",-1) ;
            result.put("msg","state error" ) ;
        }

        tr.setIsValid(SystemDefault.RECORD_STATE_CHECKED);
        ticketRecordRepository.save(tr);
        result.put("result",1) ;
        return result ;
    }

    public boolean getSiteState(int siteID) {
        SiteAccount sa = siteAccountRepository.findById(siteID).get() ;
        return sa.getActive() ;
    }


}
