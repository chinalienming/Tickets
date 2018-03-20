package nju.controller;

import nju.service.UserService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService ;


    @PostMapping(value = "/login")
    public String login(HttpSession session,
                        @RequestParam("username") String email,
                        @RequestParam("password") String password) {

        int query_userid = userService.login(email,password) ;
        if(query_userid > 0 ) {
            session.setAttribute(SystemDefault.USER_ID,query_userid);
            System.out.println("login!?!!");
            return "member/index" ;
//            return "redirect:/member/"+"index" ;
        }

        else
            return "user/index";

    }


    @PostMapping(value = "/register" )
    public String register(Model model,
                           @RequestParam(value = "mail") String email,
                           @RequestParam(value = "password") String password){
        int query_userid = userService.register(email,password) ;

        switch ( query_userid ) {
            case -1:
                model.addAttribute("error", "email illegal");
                return "user/register";
            case -2:
                model.addAttribute("error", "email exist");
                return "user/register";
            case -3:
                model.addAttribute("error", "password illegal");
                return "user/register";
            default:
                return "user/index";
        }

    }

    @RequestMapping("/goRegister")
    public String register(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "user/register";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "user/index";
    }



}

