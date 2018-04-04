package nju.controller;

import net.sf.json.JSONArray;
import nju.service.FinanceService;
import nju.service.TicketService;
import nju.util.SystemDefault;
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

    @Autowired
    private FinanceService financeService ;


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

        return  result ;
    }

    @PostMapping("/buyTicketWithoutSeats")
    @ResponseBody
    public Map<String,Object> buyTicketWithoutSeats
            (@RequestParam("planID") int planID,
             @RequestParam(value = "num_a", defaultValue = "0") int a,
             @RequestParam(value = "num_b", defaultValue = "0") int b,
             @RequestParam(value = "num_c", defaultValue = "0") int c,
             @SessionAttribute(SystemDefault.USER_ID) int userID ) {

        Map<String, Object> result = new TreeMap<>();

        int[] ticketNum = {a,b,c} ;
//        for(int j:ticketNum)
//            System.out.println("controller :ticket num"+j);

        boolean buySuccess = ticketService.buyTicket(userID,planID,ticketNum) ;
        System.out.println("controller buySuccess: " +buySuccess);
        result.put("result",buySuccess);
        result.put("url","member/index") ;

        return result ;
    }

    @RequestMapping("/goPay")
    public String goPay(){

        return "" ;
    }

    @RequestMapping("/payByBalance")
    @ResponseBody
    public Map<String,Object> payByBalance(@RequestParam(value = "recordID") int recordID,
                                           @RequestParam(value = "benefit")int benefit){
        Map<String, Object> result = new TreeMap<>();
        double amount = financeService.payByBalance(recordID,benefit) ;
//        System.out.println("pay:"+amount);
        if(amount>0) {
            result.put("result",true) ;
            result.put("amount",amount) ;
        } else {
            result.put("result",false) ;
        }

        return result;
    }

    @RequestMapping("/goPayByExternalAccount")
    public String goPayByExternalAccount(HttpSession httpSession,
                                         @RequestParam(value = "recordID")int recordID,
                                         @RequestParam(value = "benefit")int benefit) {
//        System.out.println("session "+recordID);
        httpSession.setAttribute("recordID",recordID);
        httpSession.setAttribute("benefit",benefit);
        return "payment/pay" ;
    }

    @PostMapping("/payByExternalAccount")
    @ResponseBody
    public Map<String,Object> payByExternalAccount(@SessionAttribute(value = "recordID")int recordID,
                                                   @SessionAttribute(value = "userID") int userID,
                                                   @SessionAttribute(value = "benefit")int benefit,
                                                   @RequestParam(value = "accountID")String account,
                                                   @RequestParam(value = "password")String password){
        Map<String, Object> result = new TreeMap<>();
        int accountID = Integer.parseInt(account) ;
        double amount = financeService.payByExternalAccount(recordID,userID,accountID,password,benefit) ;
//        System.out.println(amount) ;
        if(amount>0) {

            result.put("result",true) ;
            result.put("amount","支付金额: "+amount) ;
        } else {
            result.put("result",false) ;
        }
        return result;
    }

}
