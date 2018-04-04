package nju.controller;

import nju.entity.PayMessage;
import nju.entity.TicketRecord;
import nju.service.SiteService;
import nju.service.TicketService;
import nju.service.UserService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/14.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private  static Locale locale = new Locale("zh");

    @Autowired
    private UserService userService ;
    @Autowired
    private SiteService siteService ;
    @Autowired
    private TicketService ticketService ;


    //ticket record
    @RequestMapping("/index")
    public String index(Model model,
                        @SessionAttribute(SystemDefault.USER_ID) int id,
                        @SessionAttribute("userName") String userName,
                        @RequestParam(value = "page", defaultValue = "0") int page) {
                       /* ,@RequestParam(value = "page", defaultValue = "0") int page*/
        model.addAttribute("locale"  ,locale);
        List<TicketRecord> ls = userService.getValidTicketRecord(id) ;

        List<TicketRecord> ils = userService.getInvalidTicketRecord(id) ;

        List<PayMessage> pmls = userService.getPayMessage(id) ;

        model.addAttribute("userID",id) ;
        model.addAttribute("userName",userName);
        model.addAttribute("userInfo",userService.getUserInfo(id));
        model.addAttribute("ticketrecords",ls);
        model.addAttribute("invalidtr",ils) ;
        model.addAttribute("paymessage",pmls) ;
        model.addAttribute(SystemDefault.CURRENT_PAGE, page);
//        model.addAttribute(SystemDefault.CURRENT_PAGE,page) ;
        return "member/index";
    }

    //personal message
    @RequestMapping("/profile")
    public String profile(Model model,
                          @SessionAttribute("userName")String userName,
                          @SessionAttribute(SystemDefault.USER_ID) int id ) {
        model.addAttribute("userID",id) ;
        model.addAttribute("userName",userName);
        model.addAttribute("member", userService.getUserVO(id));
        return "member/profile";
    }


    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(@SessionAttribute(SystemDefault.USER_ID) int id,
                                    String name,
                                    String aID, String aPwd){
        Map<String,Object> map = new TreeMap<>() ;
        userService.edit(id, name, Integer.parseInt(aID),aPwd);
        map.put("result",true) ;
        return map ;
    }

    @PostMapping("/recharge")
    @ResponseBody
    public Map<String, Object> recharge(@SessionAttribute(SystemDefault.USER_ID) int id,
                                    int amount){
        Map<String,Object> map = new TreeMap<>() ;
        int code = userService.recharge(id, amount);
        boolean result = code>=0 ?true :false;
        map.put("result",result) ;
        return map ;
    }

    @PostMapping("/convert")
    @ResponseBody
    public Map<String, Object> convert(@SessionAttribute(SystemDefault.USER_ID) int id,
                                        int credit){
        Map<String,Object> map = new TreeMap<>() ;
        int code = userService.convert(id, credit);
        boolean result = code>=0 ?true :false;
        map.put("result",result) ;
        return map ;
    }

    @PostMapping("/froze")
    @ResponseBody
    public Map<String, Object> froze(@SessionAttribute(SystemDefault.USER_ID) int id,
                                       String password){
        Map<String,Object> map = new TreeMap<>() ;
        int code = userService.froze(id, password);
        System.out.println(code) ;
        boolean result = code>=0 ?true :false;
        map.put("result",result) ;
        return map ;
    }


    //go see all sites
    @RequestMapping("/site")
    public String sites(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page) {
        model.addAttribute(SystemDefault.SITES, siteService.getSiteByPage(-1));
        model.addAttribute(SystemDefault.CURRENT_PAGE, page);
        return "redirect:/site/"+"index";
    }


    @PostMapping("/cancel")
    @ResponseBody
    public Map<String,Object> cancel(@RequestParam(value = "recordToCancel")String record){
        int recordID = Integer.parseInt(record) ;
        int resultCode = ticketService.cancelOrder(recordID) ;
        System.out.println("取消结果 : "+ resultCode) ;
        Map<String,Object> map = new TreeMap<>() ;
        map.put("result",resultCode) ;
        return map ;
    }

}
