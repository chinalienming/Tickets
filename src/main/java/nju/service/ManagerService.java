package nju.service;

import nju.entity.Site;
import nju.entity.SitePlan;
import nju.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by lienming on 2018/3/10.
 */
public interface ManagerService {

    boolean examineRequest(Site site);

    boolean examineRequest(SitePlan sitePlan);

    boolean cleanPlanIncome(int planID,int siteID) ;

    List<UserInfo> getUserStatics();

    Map<Integer,Double> getTotalStatics();

    Map<Integer,Double> getSiteStatics(int siteID);
}
