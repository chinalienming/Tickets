package nju.controller;

import nju.service.TicketService;
import nju.util.SystemDefault;
import net.sf.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public Map<String,Object> buyTicketWithSeats
            (@RequestParam("planID") int planID,
             @RequestParam("list")String list,
             @SessionAttribute(SystemDefault.USER_ID) int userID) {

        Map<String, Object> result = new TreeMap<>();

        JSONArray jsonArray = JSONArray.fromObject(list) ;
        List<String> seatList = new ArrayList<>();

        for(int i=0;i<jsonArray.size();i++) {
//            System.out.print(jsonArray.getString(i));
            seatList.add(jsonArray.getString(i));
        }



        boolean buySuccess = ticketService.buyTicket(userID,planID,seatList) ;
        System.out.println("controller buySuccess: " +buySuccess);

        result.put("result",buySuccess);
//        if(buySuccess) {
//            result.put(SystemDefault.HTTP_RESULT,true) ;
//        } else {
//            result.put(SystemDefault.HTTP_RESULT,false) ;
//        }

        return  result ;
    }

    @PostMapping("/buyTicketWithoutSeats")
    @ResponseBody
    public Map<String,Object> buyTicketWithoutSeats
            (@RequestParam("planID") int planID,
             @RequestParam("num_a") int a,
             @RequestParam("num_b") int b,
             @RequestParam("num_c") int c,
             @SessionAttribute(SystemDefault.USER_ID) int userID ) {

        Map<String, Object> result = new TreeMap<>();

        int[] ticketNum = {a,b,c} ;
//        for(int j:ticketNum)
//            System.out.println("controller :ticket num"+j);

        boolean buySuccess = ticketService.buyTicket(userID,planID,ticketNum) ;
        System.out.println("controller buySuccess: " +buySuccess);
        result.put("result",buySuccess);
        result.put("url","member/index") ;
//        if(buySuccess) {
//            result.put(SystemDefault.HTTP_RESULT,true) ;
//        } else {
//            result.put(SystemDefault.HTTP_RESULT,false) ;
//        }

        return result ;
    }

}
