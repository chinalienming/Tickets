package nju.controller;

import nju.entity.TicketRecord;
import nju.service.TicketService;
import nju.service.UserService;
import nju.util.EmailUtility;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService ;
    @Autowired
    private TicketService ticketService ;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index" ;
    }

    @PostMapping(value = "/login")
    public String login(HttpSession session,
                        @RequestParam("username") String email,
                        @RequestParam("password") String password) {

        int query_userid = userService.login(email,password) ;
        if(query_userid > 0 ) {
            session.setAttribute(SystemDefault.USER_ID,query_userid);
            System.out.println("login!?!!");
//            return "member/index" ;
            return "redirect:/member/index" ;
        }

        else
            return "user/index";

    }


    @PostMapping(value = "/sendEmail")
    @ResponseBody
    public Map<String,Object> sendEmail(@RequestParam(value = "mail") String email,
                                        @RequestParam(value = "password") String password) {

        Map<String,Object> result = new TreeMap<>() ;

        int query_userid = userService.register(email,password) ;

        System.out.println("register result: " +query_userid) ;

        switch ( query_userid ) {
            case -1:
                result.put("result",false) ;
                result.put("message", "email illegal");
                return result ;
            case -2:
                result.put("result",false) ;
                result.put("message", "email exist");
                return result ;
            case -3:
                result.put("result",false) ;
                result.put("message", "password illegal");
                return result ;
            default:
                EmailUtility.sendAccountActivateEmail("javalem@163.com",query_userid+"");
//                EmailUtility.sendAccountActivateEmail(email,query_userid+"");
                result.put("result",true) ;
                result.put("message", "input key: " );
                return result;
        }


    }

    @PostMapping(value = "/activate" )
    @ResponseBody
    public Map<String,Object> activate(
                           @RequestParam(value = "mail") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "active") String key ){
        Map<String,Object> result = new TreeMap<>() ;

        int query_userid = userService.getUserIDByEmailAndPassword(email,password) ;

        if(query_userid==-1) {
            result.put("result",false) ;
            result.put("message", "account not exist");
            return result ;
        }

        if (query_userid!=Integer.parseInt(key)) {
            result.put("result",false) ;
            result.put("message", "激活码不对");
            return result ;
        } else {
            result.put("result",userService.activatAccount(query_userid)) ;
            result.put("message", "激活成功!");
            return result ;
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

