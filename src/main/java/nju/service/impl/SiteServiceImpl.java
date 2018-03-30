package nju.service.impl;

import nju.dao.*;
import nju.entity.*;
import nju.service.ManagerService;
import nju.service.PlanService;
import nju.service.SiteService;
import nju.service.TicketService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    @Autowired
    private PlanApplyRepository planApplyRepository ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;

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
        sa.setActive(true);   //
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
        OpenApply oa = new OpenApply() ;
        oa.setSiteID(siteID);
        oa.setReason(reason);
        openApplyRepository.save(oa) ;
    }

    public boolean isApplyingForOpen(int siteId) {
        return openApplyRepository.existsBySiteIDAndState(siteId,0) ;
    }

    public boolean isApplyingForEdit(int siteId) {
        return editApplyRepository.existsBySiteIDAndState(siteId,0) ;
    }

    public void applyPlan(int siteID, String description, String planType , String beginTime , String endTime ,
                          double price_a ,double price_b ,double price_c) {

        System.out.println(beginTime) ;
        System.out.println(endTime) ;

        PlanApply pa = new PlanApply
                (siteID,planType,description,beginTime,endTime,price_a,price_b,price_c) ;

        planApplyRepository.save(pa) ;

    }

    public List<TicketRecord> getSiteTRList(int siteID) {
        List<TicketRecord> result = ticketRecordRepository.findBySiteIDAndPayType(siteID,SystemDefault.RECORD_PAYTYPE_CASH) ;
        Collections.reverse(result);
        return result ;
    }

    public int doBuyOffline(int planID,int userID, int seatA,int seatB,int seatC ) {
        return ticketService.buyTicketOffline(planID,userID,seatA,seatB,seatC) ;
    }
}
