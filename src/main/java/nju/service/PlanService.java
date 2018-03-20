package nju.service;

import nju.entity.SitePlan;
import nju.entity.TicketRecord;

import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
public interface PlanService {

    boolean addIncome(int planID , int userID , double amount) ;

    boolean removeIncome(TicketRecord tr, double return_amount);

    boolean cleanIncome(int planID) ;

    List<SitePlan> getAllSitePlan( );

    List<SitePlan> getPlanBySiteID(int siteID) ;

    SitePlan getPlanByID(int planID);

    List<SitePlan> getPlanByPage(int  siteID, int page) ;

}
