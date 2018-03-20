package nju.controller;

import nju.service.TicketService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/11.
 */

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private TicketService ticketService ;

    @PostMapping("/buyTicketWithSeats")
    @ResponseBody
    public Map<String, Object> buyTicketWithSeats
            (HttpSession session,
             @SessionAttribute(SystemDefault.USER_ID) int userID) {
        Map<String, Object> result = new TreeMap<>();
        int planID = (int)session.getAttribute(SystemDefault.PLAN_ID) ;
        List<String> seatList = (List<String>)session.getAttribute(SystemDefault.SEAT_LIST) ;
        boolean buySuccess = ticketService.buyTicket(userID,planID,seatList) ;
        if(buySuccess) {
            result.put(SystemDefault.HTTP_RESULT,true) ;
        } else {
            result.put(SystemDefault.HTTP_RESULT,false) ;
        }
        return result ;
    }

    @PostMapping("/buyTicketWithoutSeats")
    @ResponseBody
    public Map<String, Object> buyTicketWithoutSeats
            (HttpSession session,
             @SessionAttribute(SystemDefault.USER_ID) int userID ) {
        Map<String, Object> result = new TreeMap<>();
        int planID = (int)session.getAttribute(SystemDefault.PLAN_ID) ;
        int[] ticketNum = (int[])session.getAttribute(SystemDefault.TICKET_NUM) ;
        boolean buySuccess = ticketService.buyTicket(userID,planID,ticketNum) ;
        if(buySuccess) {
            result.put(SystemDefault.HTTP_RESULT,true) ;
        } else {
            result.put(SystemDefault.HTTP_RESULT,false) ;
        }
        return result ;
    }

}
