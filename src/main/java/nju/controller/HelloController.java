package nju.controller;

import nju.service.ManagerService;
import nju.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/19.
 */

@Controller
public class HelloController {

    @Autowired
    private SiteService siteService ;
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/")
    public String helloUser(){
        return "user/index";        //跳转到user/index.html
    }


    /****
     *  Site
     */

    @RequestMapping(value = "/site")
    public String helloSite(){
        return "site/login";
    }

    @RequestMapping(value = "/site/goRegister")
    public String goSiteRegister(){
        return "site/register";
    }

    @PostMapping(value = "/site/register")
    @ResponseBody
    public Map<String,Object> doSiteRegister(HttpSession session,
                             @RequestParam("siteName") String siteStr,
                             @RequestParam("password") String password){
        int id = siteService.register(siteStr,password) ;
        Map<String,Object> result = new TreeMap<>() ;

        String str_id = String.format("%07d", id);

        result.put("result_id",str_id) ;

        return result;
    }

    @RequestMapping(value = "/site/goLogin")
    public String goSiteLogin(){
        return "site/login";
    }

    @PostMapping(value = "/site/login")
    public String doSiteLogin(HttpSession session,
                              Model model ,
                             @RequestParam("siteID") String siteStr,
                             @RequestParam("password") String password){
        int siteID = Integer.parseInt(siteStr) ;

        int result_code = siteService.login(siteID,password) ;
        Map<String,Object> result = new TreeMap<>() ;

        if(result_code<0) {
            return "site/goLogin" ;
        }

        model.addAttribute("siteID",siteID) ;
        model.addAttribute("siteState",siteService.getSiteState(siteID));
        model.addAttribute("site",siteService.getSiteInfo(siteID)) ;
        session.setAttribute("siteID",siteID);
        return "site/info";
    }

    @RequestMapping(value = "/site/logout", method = RequestMethod.GET)
    public String doSiteLogout(HttpSession session) {
        session.removeAttribute("siteID");
        session.removeAttribute("site");
        return "site/login";
    }

    /****
     *  Manager
     */
    @RequestMapping(value = "/manager")
    public String helloManager(){
        return "manager/login";
    }


    @PostMapping(value = "/manager/login")
    public String doManagerLogin(HttpSession session,Model model,
                              @RequestParam("managerID") String mStr,
                              @RequestParam("password") String password){
        int managerID = Integer.parseInt(mStr) ;


        int result_code = managerService.login(managerID,password) ;
        if(result_code<0) {
            return "manager/";
        }
        session.setAttribute("managerID",managerID);
        model.addAttribute("managerID",managerID) ;
        model.addAttribute("num",managerService.getAllApplyNum()) ;
        model.addAttribute("opens", managerService.getAllOpenApply(0, -1));
        model.addAttribute("edits", managerService.getAllEditApply(0, -1));
        return "manager/index";
    }


    @RequestMapping(value = "/manager/logout", method = RequestMethod.GET)
    public String doManagerLogout(HttpSession session) {
        session.removeAttribute("managerID");
        return "manager/login";
    }

}

