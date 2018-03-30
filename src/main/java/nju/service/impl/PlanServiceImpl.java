package nju.service.impl;

import nju.dao.PlanApplyRepository;
import nju.dao.SitePlanRepository;
import nju.dao.SiteRepository;
import nju.dao.TicketRecordRepository;
import nju.entity.PlanApply;
import nju.entity.SitePlan;
import nju.entity.TicketRecord;
import nju.service.PlanService;
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
public class PlanServiceImpl implements PlanService {

    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private SiteRepository siteRepository ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;
    @Autowired
    private PlanApplyRepository planApplyRepository ;

    public boolean addIncome(int planID , int userID , double amount) {
        SitePlan sitePlan = getPlanByID(planID);
        double original_income = sitePlan.getPlanIncome() ;
        sitePlan.setPlanIncome(original_income += amount);
        sitePlanRepository.save(sitePlan) ;
        return true ;
    }

    public boolean removeIncome(TicketRecord tr, double return_amount) {
        SitePlan sitePlan = getPlanByID(tr.getPlanID());
        double original_income = sitePlan.getPlanIncome() ;
        sitePlan.setPlanIncome(original_income-return_amount);
        tr.setIsValid(SystemDefault.RECORD_STATE_CANCEL);
        ticketRecordRepository.save(tr) ;
        sitePlanRepository.save(sitePlan);
        return true ;
    }

    public boolean cleanIncome(int planID){
        SitePlan sitePlan = getPlanByID(planID);
        sitePlan.setPlanIncome(0.00);
        sitePlanRepository.save(sitePlan);
        return true ;
    }

    public List<SitePlan> getPlanBySiteID(int siteID) {
        return sitePlanRepository.findBySiteID(siteID);
    }

    public List<SitePlan> getAllSitePlan( ) {
        return sitePlanRepository.findAll();
    }

    public SitePlan getPlanByID(int planID) {
        return sitePlanRepository.findById(planID).get();
    }

    public List<SitePlan> getPlanByPage(int siteID, int page) {
        List<SitePlan> result = new ArrayList<>() ;
        Page<SitePlan> pages ;
        if (page >= 0) {
            pages = sitePlanRepository.findBySiteID
                    (siteID, new PageRequest(page, SystemDefault.SIZE_PER_PAGE_OF_SITE));
            pages.forEach(result::add);
        } else {
            sitePlanRepository.findBySiteID(siteID).forEach(result::add);
        }
        return result;
    }

    public double[] getPriceByPlanID(int planID)  {
        SitePlan sitePlan = sitePlanRepository.getOne(planID) ;
        double[] result = { 0,0,0 };
        result[0] = sitePlan.getOriginal_price_A() ;
        result[1] = sitePlan.getOriginal_price_B() ;
        result[2] = sitePlan.getOriginal_price_C() ;
        return result ;
    }

    public List<PlanApply> getPlanApplyBySiteID(int siteID) {
        return planApplyRepository.findBySiteID(siteID) ;
    }

}
