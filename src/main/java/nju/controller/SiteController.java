package nju.controller;

import nju.service.PlanService;
import nju.service.SiteService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping({"/","/index"})
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
    public String plans(Model model,
                        @RequestParam(value = "siteID")int siteID) {
//        model.addAttribute( SystemDefault.PLANS , planService.getPlanBySiteID(siteID) );
//        model.addAttribute( SystemDefault.CURRENT_PAGE, page);
        return "site/info";
    }

    /**
     * @Responsebody 注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，
     * 一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，返回值通常解析为跳转路径，
     * 加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
     */
    @RequestMapping("/plan_detail")
    public String plan_detail(Model model,
                              @RequestParam(value = "planID") int planID ) {
        model.addAttribute( SystemDefault.PLAN_DETAIL, planService.getPlanByID(planID) ) ;
        return "redirect:/plan/detail";
    }

//



}
