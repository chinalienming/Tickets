package nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by lienming on 2018/3/19.
 */

@Controller
public class HelloController {

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
    public String doSiteRegister(HttpSession session,
                             @RequestParam("siteID") String siteStr,
                             @RequestParam("password") String password){
        int siteID = Integer.parseInt(siteStr) ;


        return "site/login";
    }

    @RequestMapping(value = "/site/goLogin")
    public String goSiteLogin(){
        return "site/login";
    }

    @PostMapping(value = "/site/login")
    public String doSiteLogin(HttpSession session,
                             @RequestParam("siteID") String siteStr,
                             @RequestParam("password") String password){
        int siteID = Integer.parseInt(siteStr) ;


        return "site/info";
    }

    /****
     *  Manager
     */

    @RequestMapping(value = "/manager")
    public String helloManager(){
        return "manager/login";
    }


    @PostMapping(value = "/manager/login")
    public String doManagerLogin(HttpSession session,
                              @RequestParam("managerID") String mStr,
                              @RequestParam("password") String password){

        int managerID = Integer.parseInt(mStr) ;
        return "manager/index";
    }

}

