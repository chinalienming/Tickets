package nju.controller;

import nju.entity.PlanApply;
import nju.service.PlanService;
import nju.service.SiteService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/site")
public class SiteController {

    @Autowired
    private SiteService siteService ;
    @Autowired
    private PlanService planService ;

    @RequestMapping("/index")
    public String sites(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page) {
        model.addAttribute(SystemDefault.SITES, siteService.getSiteByPage(page));
        model.addAttribute(SystemDefault.CURRENT_PAGE, page);
        return "site/index";
    }


    @RequestMapping("/plans")
    public String plans(Model model,
                        @RequestParam(value = "siteID")int siteID,
                        @RequestParam(value = "page", defaultValue = "0") int page) {
        model.addAttribute( "site" , siteService.getSiteInfo(siteID)) ;
        model.addAttribute( SystemDefault.PLANS , planService.getPlanByPage(siteID,page) );
        model.addAttribute( SystemDefault.CURRENT_PAGE, page);
        return "site/plans";
    }


    @RequestMapping("/info")
    public String info(Model model, @SessionAttribute("siteID")int siteID) {

        model.addAttribute( "site" , siteService.getSiteInfo(siteID) );


        if (siteService.isApplyingForEdit(siteID)) {
            model.addAttribute("edit", true);
        }
        if (siteService.isApplyingForOpen(siteID)) {
            model.addAttribute("open", true);
        }


        return "site/info";
    }

    /**
     * @Responsebody 注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，
     * 一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，返回值通常解析为跳转路径，
     * 加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
     */
//    @RequestMapping("/plan_detail")
//    public String plan_detail(Model model,
//                              @RequestParam(value = "planID") int planID ) {
//        model.addAttribute( SystemDefault.PLAN_DETAIL, planService.getPlanByID(planID) ) ;
//        return "redirect:/plan/detail";
//    }


    @PostMapping(value = "/edit" )
    public String editApplication(Model model,@SessionAttribute("siteID") int siteID,
                                  String name, String address , int num_a , int num_b , int num_c  ) {

        siteService.saveModifyApplication(siteID,name,address,num_a,num_b,num_c);

        return info(model, siteID);
    }


    @PostMapping(value = "/open")
    public String openApplication(Model model, @SessionAttribute("siteID") int siteID, String reason) {
        siteService.saveOpenApplication(siteID,reason);
        return info(model, siteID);
    }

    @RequestMapping("/plansShow")
    public String plansShow(Model model,@SessionAttribute("siteID") int siteID ,
                 @RequestParam(value = "page", defaultValue = "0") int page) {

        model.addAttribute("siteID",siteID);
        model.addAttribute( "site" , siteService.getSiteInfo(siteID)) ;
        model.addAttribute( SystemDefault.PLANS , planService.getPlanByPage(siteID,page) );
        model.addAttribute( SystemDefault.CURRENT_PAGE, page);
        return "site/plansShow";
    }

    @RequestMapping("/addPlan")
    public String addPlan(Model model, @SessionAttribute("siteID")int siteID) {

        List<PlanApply> list = planService.getPlanApplyBySiteID(siteID)  ;
        model.addAttribute("siteID",siteID);
        model.addAttribute("plans",list) ;

        return "site/addPlan" ;
    }

    @PostMapping("/applyPlan")
    @ResponseBody
    public Map<String,Object> applyPlan(@SessionAttribute("siteID")int siteID, String description, String planType ,
                                        String beginTime , String endTime , double price_a , double price_b , double price_c) {

        siteService.applyPlan(siteID,description,planType,beginTime,endTime,price_a,price_b,price_c);

        Map<String,Object> result = new TreeMap<>() ;
        result.put("result",true) ;

        return result;
    }


    @RequestMapping("/buyOffline")
    public String buyOffline(@SessionAttribute("siteID")int siteID,Model model){
        model.addAttribute("siteID", siteID);
        model.addAttribute("siteTR", siteService.getSiteTRList(siteID));
        return "site/buyOffline" ;
    }


    @PostMapping("/doBuyOffline")
    @ResponseBody
    public Map<String,Object> doBuyOffline(int planID,int memberID, int seatA,int seatB,int seatC ) {

        int code = siteService.doBuyOffline(planID,memberID,seatA,seatB,seatC) ;

        Map<String,Object> result = new TreeMap<>() ;
        if(code>=0) {
            result.put("result",true) ;
        } else {
            result.put("result",false) ;
            result.put("reason",code) ;
        }
        return result;
    }

    @RequestMapping("/statistics")
    public String statistics(@SessionAttribute("siteID")int siteID,Model model){
        model.addAttribute("siteID", siteID);
        return "site/statistics" ;
    }

    @RequestMapping("/recentIncome")
    @ResponseBody
    public Map<String,Object> recentIncome(@SessionAttribute("siteID")int siteID) {
        return siteService.getRecentIncome(siteID);
    }

    @RequestMapping("/cancelStatistics")
    @ResponseBody
    public Map<String,Object> cancelStatistics(@SessionAttribute("siteID")int siteID) {
        return siteService.getCancelStatistics(siteID);
    }

    @RequestMapping("/consumption")
    @ResponseBody
    public Map<String,Object> consumption(@SessionAttribute("siteID")int siteID) {
        return siteService.getConsumption(siteID);
    }


}
