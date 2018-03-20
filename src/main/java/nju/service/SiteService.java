package nju.service;

import nju.entity.Site;

import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
public interface SiteService {

    List<Integer> getAllSiteID () ;

    boolean subscribeSiteInfo(Site site);

    int registerSite(Site site);

    boolean checkSiteNameExist(String siteName );

    List<Site> getSiteByPage(int page) ;

}
