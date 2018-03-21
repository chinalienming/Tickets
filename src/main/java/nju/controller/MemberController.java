package nju.controller;

import nju.entity.TicketRecord;
import nju.service.SiteService;
import nju.service.UserService;
import nju.util.SystemDefault;
import nju.vo.TicketRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Locale;

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

    //ticket record
    @RequestMapping("/index")
    public String index(Model model,
                        @SessionAttribute(SystemDefault.USER_ID) int id) {
                       /* ,@RequestParam(value = "page", defaultValue = "0") int page*/
        model.addAttribute("locale"  ,locale);
        List<TicketRecord> ls = userService.getTicketRecord(id);
//        System.out.println("size:"+ls.size());
//        for(TicketRecord tr:ls) {
//            System.out.println("ID: "+tr.getRecordID());
//        }
        model.addAttribute(SystemDefault.TICKET_RECORDS,ls);
//        model.addAttribute(SystemDefault.CURRENT_PAGE,page) ;
        return "member/index";
    }

    //personal message
    @RequestMapping("/profile")
    public String profile(Model model,
                          @SessionAttribute(SystemDefault.USER_ID) int id ) {
        model.addAttribute("member", userService.getUserVO(id));
        return "member/profile";
    }


    //go see all sites
    @RequestMapping("/site")
    public String sites(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page) {
        model.addAttribute(SystemDefault.SITES, siteService.getSiteByPage(page));
        model.addAttribute(SystemDefault.CURRENT_PAGE, page);
        return "redirect:/site/"+"index";
    }

}
