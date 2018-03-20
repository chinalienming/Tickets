package nju.controller;

import nju.util.SystemDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/plan")
public class PlanController {

    @PostMapping("/goBuyTicketWithSeats")
    public String buyTicketWithSeats(HttpSession session,
                                     @RequestParam(value = "planID") int planID,
                                     @RequestParam(value = "list")List<String> seatList) {
        session.setAttribute(SystemDefault.PLAN_ID,planID);
        session.setAttribute(SystemDefault.SEAT_LIST,seatList);
        return "redirect:/payment/buyTicketWithSeats" ;
    }

    @PostMapping("/goBuyTicketWithoutSeats")
    public String buyTicketWithoutSeats(HttpSession session,
                                        @RequestParam(value = "planID") int planID,
                                        @RequestParam(value = "ticketNum")int[] ticketNum) {
        session.setAttribute(SystemDefault.PLAN_ID,planID);
        session.setAttribute(SystemDefault.TICKET_NUM,ticketNum);
        return "redirect:/payment/buyTicketWithoutSeats" ;
    }
}
