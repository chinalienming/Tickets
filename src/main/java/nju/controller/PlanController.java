package nju.controller;

import nju.entity.Seat;
import nju.service.PlanService;
import nju.service.SeatService;
import nju.service.SiteService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller
@RequestMapping(value = "/plan")
public class PlanController {

    @Autowired
    private SeatService seatService ;

    @Autowired
    private PlanService planService ;

    @Autowired
    private SiteService siteService ;


    @RequestMapping("/selectSeat")
    public String selectSeat(Model model , @RequestParam(value = "planID") int planID) {
//        model.addAttribute("list",seatService.getAvailableSeatsByPlanID(planID));
//        model.addAttribute("totalNum",seatService.getTotalNum(planID));
        model.addAttribute("planID",planID) ;
        return "plan/selectSeat";
    }

    @RequestMapping("/unselectSeat")
    public String unselectSeat(Model model , @RequestParam(value = "planID") int planID) {
        model.addAttribute("site",siteService.getSiteByPlanID(planID)) ;
        model.addAttribute("restArr",seatService.getSeatTypeNum(planID));
        model.addAttribute("totalArr",seatService.getTotalNum(planID));
        model.addAttribute("priceArr",planService.getPriceByPlanID(planID)) ;
        model.addAttribute("planID",planID) ;
        return "plan/unselectSeat";
    }

    @ResponseBody
    @PostMapping(value = "/getDataOfSelectSeat" )
    public Map<String,Object> getDataOfSelectSeat( @RequestParam(value = "planID") int planID) {
        Map<String,Object> result = new TreeMap<>() ;
        result.put("planID",planID) ;

        result.put("unavailableList",seatService.getUnavailableSeatsByPlanID(planID));
//        result.put("seatsMap",seatService.getSeatsMap(planID) );
        result.put("priceArr",planService.getPriceByPlanID(planID)) ;
        return result;
    }

//    @ResponseBody
//    @PostMapping(value = "/getDataOfUnselectSeat" )
//    public Map<String,Object> getDataOfUnselectSeat( @RequestParam(value = "planID") int planID) {
//        Map<String,Object> result = new TreeMap<>() ;
//        result.put("planID",planID) ;
//        result.put("restArr",seatService.getSeatTypeNum(planID));
//        result.put("priceArr",planService.getPriceByPlanID(planID)) ;
//        return result;
//    }

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
