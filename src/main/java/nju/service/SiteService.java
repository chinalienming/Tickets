package nju.service;

import nju.entity.Site;
import nju.entity.TicketRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by lienming on 2018/3/10.
 */
public interface SiteService {

    List<Integer> getAllSiteID () ;

//    boolean subscribeSiteInfo(Site site);
//    int registerSite(Site site);
//    boolean checkSiteNameExist(String siteName );

    List<Site> getSiteByPage(int page) ;

    Site getSiteInfo(int siteID) ;

    Site getSiteByPlanID(int planID) ;

    int register(String siteName,String password) ;

    int login(int siteID,String password) ;

    void saveModifyApplication(int siteID, String name, String address,int num_a,int num_b,int num_c) ;

    void saveOpenApplication( int siteID ,String reason) ;

    boolean isApplyingForOpen(int siteId) ;

    boolean isApplyingForEdit(int siteId) ;

    void applyPlan(int siteID, String description, String planType , String beginTime , String endTime ,
                   double price_a ,double price_b ,double price_c) ;

    List<TicketRecord> getSiteTRList(int siteID) ;

    int doBuyOffline(int planID,int userID, int seatA,int seatB,int seatC ) ;

    Map<String,Object> getRecentIncome( int siteID) ;

    Map<String,Object> getCancelStatistics(int siteID) ;

    Map<String,Object> getConsumption( int siteID) ;
}
