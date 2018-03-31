package nju.controller;

import nju.service.ManagerService;
import nju.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService ;
    @Autowired
    private PlanService planService ;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @SessionAttribute("managerID")int managerID) {
        model.addAttribute("managerID",managerID) ;
        model.addAttribute("num",managerService.getAllApplyNum()) ;
        model.addAttribute("opens", managerService.getAllOpenApply(0, -1));
        model.addAttribute("edits", managerService.getAllEditApply(0, -1));
        return "manager/index";
    }


    @RequestMapping(value = "/approve" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> approve(boolean isApprove, boolean openOrModify,
                                       @RequestParam("operationArray[]") List<Integer> operationArray){
        return managerService.approve(isApprove, openOrModify, operationArray);
    }

    @RequestMapping(value = "/settlement", method = RequestMethod.GET)
    public String settlement(Model model, @SessionAttribute("managerID")int managerID) {
        model.addAttribute("plans",planService.getSettlements());
        model.addAttribute("managerID",managerID) ;
        return "manager/settlement";
    }

    @RequestMapping(value = "/settlement" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doSettlement(@RequestParam("operationArray[]") List<Integer> operationArray){
        return managerService.doSettlement(operationArray);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model, @SessionAttribute("managerID")int managerID) {
//        model.addAttribute();
        model.addAttribute("managerID",managerID) ;
        return "manager/statistics";
    }

    @RequestMapping(value = "/siteStatus" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> siteStatus( ){
        return managerService.getSiteStatus();
    }

    @RequestMapping(value = "/memberStatus" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> memberStatus( ){
        return managerService.getMemberStatus();
    }

    @RequestMapping(value = "/financeStatus" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> financeStatus( ){
        return managerService.getFinanceStatus();
    }

}
