package nju.service.impl;

import nju.dao.SiteAccountRepository;
import nju.dao.SiteRepository;
import nju.entity.Site;
import nju.entity.SiteAccount;
import nju.entity.SitePlan;
import nju.service.ManagerService;
import nju.service.PlanService;
import nju.service.SiteService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PlanService planService ;
    @Autowired
    private ManagerService managerService ;

    @Autowired
    private SiteRepository siteRepository ;
    @Autowired
    private SiteAccountRepository siteAccountRepository ;

    public List<Integer> getAllSiteID () {
        List<Integer> list = new ArrayList<>() ;
        siteRepository.findAll().forEach(
                site -> {
                    list.add(site.getSiteID());
                }
        );
        return list ;
    }

    public boolean subscribeSitePlan(SitePlan sitePlan){
        return managerService.examineRequest(sitePlan);
    }


    public boolean subscribeSiteInfo(Site site){
        return managerService.examineRequest(site) ;
    }

    //return siteID
    public int registerSite(Site site){

        if(site.getSiteID() > 0 )
            return -1 ;  //already registered

        boolean nameExist = checkSiteNameExist(site.getSiteName()) ;
        if(nameExist)
            return -2 ;  //siteName Exist

        boolean subscribe_pass = subscribeSiteInfo(site);

        if(!subscribe_pass)
            return -3;

        Site site_getID = siteRepository.save(site);
        return site_getID.getSiteID() ;
    }

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
}
