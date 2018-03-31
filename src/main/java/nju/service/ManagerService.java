package nju.service;

import nju.entity.*;
import org.springframework.web.bind.annotation.RequestParam;

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

    int login(int mID,String password) ;

    List<EditApply> getAllEditApply(int state,int page) ;

    List<OpenApply> getAllOpenApply(int state,int page) ;

    Map<String, Object> approve(boolean isApprove, boolean openOrModify, List<Integer> operationArray);

    int getAllApplyNum();

    Map<String, Object> doSettlement( List<Integer> operationArray) ;

    Map<String, Object> getSiteStatus() ;

    Map<String, Object> getMemberStatus() ;

    Map<String, Object> getFinanceStatus() ;
}
