package nju.service.impl;

import nju.dao.SeatRepository;
import nju.dao.TicketRecordRepository;
import nju.dao.UserInfoRepository;
import nju.entity.Seat;
import nju.entity.SitePlan;
import nju.entity.TicketRecord;
import nju.entity.UserInfo;
import nju.service.*;
import nju.util.MyDate;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private SeatService seatService ;
    @Autowired
    private FinanceService financeService ;
    @Autowired
    private UserService userService ;
    @Autowired
    private PlanService planService ;

    @Autowired
    private TicketRecordRepository ticketRecordRepository ;
    @Autowired
    private UserInfoRepository userInfoRepository ;
    @Autowired
    private SeatRepository seatRepository ;

    public boolean setPayed(int recordID,int payType) {

        TicketRecord tr = ticketRecordRepository.findById(recordID).get() ;
        tr.setPayType(payType);
        tr.setIsValid(SystemDefault.RECORD_STATE_PAYED) ;
        userService.addCredit(tr) ;
        List<String> list = new ArrayList<>();
        list.add(tr.getSeatNumber()) ;
        //unlocked tickets
        boolean unlockSeatSuccess =
                seatService.unlockSeat(tr.getUserID(),tr.getPlanID(),list,true);
        if(!unlockSeatSuccess) {
            return false ;
        }

        return true ;

    }

    public boolean setTimeOut(int recordID) {
        TicketRecord tr = ticketRecordRepository.findById(recordID).get() ;
        tr.setIsValid(SystemDefault.RECORD_STATE_TIMEOUT) ;

        List<String> list = new ArrayList<>();
        list.add(tr.getSeatNumber()) ;
        //unlocked tickets
        boolean unlockSeatSuccess =
                seatService.unlockSeat(tr.getUserID(),tr.getPlanID(),list,false);
        if(!unlockSeatSuccess) {
            return false ;
        }

        return true ;
    }

    /**
     * ticket transaction WITH seat position demand
     */
    public boolean buyTicket(int userID , int planID , List<String> seatList ){

        //limit amount
        if( seatList.size() > SystemDefault.SEAT_SELECTED_MAX ) {
            return false ;
        }

        boolean seatExists = seatService.checkSeatExists(planID,seatList) ;
        if(!seatExists) return false ;

        boolean lockSeatSuccess = seatService.lockSeat(userID, planID, seatList) ;

//        System.out.println("TicketService lockSeat:"+lockSeatSuccess);

        if(!lockSeatSuccess)
            return false ;

        SitePlan sitePlan = planService.getPlanByID(planID) ;
//        UserInfo userInfo = userInfoRepository.findById(userID).get() ;

        int[] ticketNum = new int[SystemDefault.SEAT_TYPE_NUM];
        for (String seatString : seatList ) {
            //judge seat type of seatNumber
            char seatType = seatString.charAt(0) ;
            int pos = seatType - 'A' ;
            ticketNum[pos]++;
        }

        /* used for single record amount calculate */
        double original_price_A = sitePlan.getOriginal_price_A() ;
        double original_price_B = sitePlan.getOriginal_price_B() ;
        double original_price_C = sitePlan.getOriginal_price_C() ;
        double[] original_price = {original_price_A,original_price_B,original_price_C} ;
        double[] discountDetail = {1,1,1}; //SystemDefault.switchDiscount(userInfo.getLevel());

//        double transfer_amount = financeService.transfer2plan(userID, planID, ticketNum);
//
//
//        boolean transferSuccess = transfer_amount > 0 ? true : false ;
//
//        System.out.println("TicketService transferSuccess:"+transferSuccess);
//
//        if( transferSuccess ) {
            //create new TicketRecord
        TicketRecord tr;

        for(String seatNumber : seatList ) {
            int type = seatNumber.charAt(0) - 'A';
            tr = new TicketRecord(userID, sitePlan.getSiteID(), planID,
                    seatNumber, discountDetail[type] * original_price[type],SystemDefault.RECORD_PAYTYPE_NOTPAY);
            ticketRecordRepository.save(tr);
        }

        return lockSeatSuccess;

//                // add consume record to UserInfo..
//                userService.addCredit(userID, transfer_amount, tr) ;
//            }
//
//        }
//
//        //unlocked tickets
//        boolean unlockSeatSuccess =
//                seatService.unlockSeat(userID,planID,seatList,transferSuccess);
//        System.out.println("TicketService unlock seat Success:"+unlockSeatSuccess);
//        if(!unlockSeatSuccess) {
//
//            return false ;
//        }



    }

    /**
     * ticket transaction WITHOUT seat position demand
     */
    public boolean buyTicket(int userID , int planID , int[] ticketNum) {

        //limit amount
        int total = 0 ;
        for(int i : ticketNum )
            total += i ;

        if( total > SystemDefault.SEAT_NOT_SELECTED_MAX ) {     //limitation
            return false ;
        }

//        for(int j:ticketNum)
//            System.out.println("ticketService 1 :ticket num"+j);

        List<Seat> seats = seatService.lockSeat(userID, planID, ticketNum) ;
//        for(int j:ticketNum)
//            System.out.println("ticketService 2 :ticket num"+j);

        boolean lockSeatSuccess = true ;
        if(seats==null)
            lockSeatSuccess = false  ;
        if(seats.size()<total)
            lockSeatSuccess = false ;

//        System.out.println("TicketService lockSeat:"+lockSeatSuccess);
        if(!lockSeatSuccess) {
            return false ;  //maybe seat is not enough
        }

        SitePlan sitePlan = planService.getPlanByID(planID);
        UserInfo userInfo = userInfoRepository.findById(userID).get();

        double original_price_A = sitePlan.getOriginal_price_A() ;
        double original_price_B = sitePlan.getOriginal_price_B() ;
        double original_price_C = sitePlan.getOriginal_price_C() ;
        double[] original_price = {original_price_A,original_price_B,original_price_C} ;

        //折扣力度
//        double[] discountDetail = SystemDefault.switchDiscount(userInfo.getLevel());
        double[] discountDetail = {1,1,1} ;

//        double transfer_amount = financeService.transfer2plan(userID, planID, ticketNum);
//
//        boolean transferSuccess = transfer_amount > 0 ? true : false ;
//
//        System.out.println("TicketService transferSuccess:"+transferSuccess);
//
//        if( transferSuccess ) {
            //create new TicketRecord
            TicketRecord tr;
            for(Seat seat : seats ) {
                String seatNumber = seat.getSeatNumber() ;
                int type = seatNumber.charAt(0) - 'A' ;
                tr = new TicketRecord(userID, sitePlan.getSiteID(), planID,
                        seatNumber, discountDetail[type] * original_price[type] ,SystemDefault.RECORD_PAYTYPE_NOTPAY) ;
//                System.out.println("tr :"+tr.getPlanID()+" "+tr.getSeatNumber()+" "+tr.getPrice()+" "+tr.getRecordID()) ;
                System.out.println("save :"+ ticketRecordRepository.save(tr) );
//                System.out.println(tr.getRecordID() );
                // add consume record to UserInfo..
//                userService.addCredit(userID, transfer_amount, tr) ;
            }
//        }
//
//        List<String> seatNameList = new ArrayList<>() ;
//        for(Seat seat : seats ) {
//            seatNameList.add(seat.getSeatNumber()) ;
//        }
//
//        //unlocked tickets
//        boolean unlockSeatSuccess =
//                seatService.unlockSeat(userID,planID,seatNameList,transferSuccess);
//        System.out.println("TicketService unlock seat Success:"+unlockSeatSuccess);
//        if(!unlockSeatSuccess) {
//            return false ;
//        }

        return lockSeatSuccess;
    }

    /**
     * mysql
     * 配票：未选座的顾客已经在购票时配好
     *  只需要公布
     */
    public List<Seat> matchTicket(int planID,int userID) {
        return seatRepository.findByPlanIDAndUserID(planID,userID) ;
    }

    public int cancelOrder(int recordID) {

        TicketRecord tr = findRecord(recordID) ;

        if(tr.getIsValid()!=SystemDefault.RECORD_STATE_PAYED) {
            return -1  ;
        }

        Date present_time = Calendar.getInstance().getTime() ;

        SitePlan sitePlan = planService.getPlanByID(tr.getPlanID()) ;

        Date perform_time = sitePlan.getEndTime();

        int hours = MyDate.hoursBetweenDate(present_time,perform_time) ;

        if( hours <= 0 ) {
            //error
        }

        // rule ?
        double rate = 1.0  ; //SystemDefault.returnRate(hours) ;

        double return_amount = tr.getPrice() * rate ;

        boolean transferSuccess = financeService.cancelFromSite(tr,return_amount);
        if(!transferSuccess)
            return -2 ;

        boolean restoreSeatSuccess = restoreSeat(recordID) ;
        if(!restoreSeatSuccess)
            return -3 ;

        return 0 ;
    }

    public boolean restoreSeat(int recordID) {

        TicketRecord tr = findRecord(recordID) ;
        int planID = tr.getPlanID() ;
        String seatNumber = tr.getSeatNumber() ;
        Seat seat = seatRepository.findByPlanIDAndSeatNumber(planID,seatNumber) ;
        seat.setState(SystemDefault.SEAT_STATE_EMPTY);
        seat.setUserID(SystemDefault.SEAT_FREE);
        seatRepository.save(seat) ;
        return true ;
    }

    //only record and get seat ?
    public int buyTicketOffline(int planID,int userID, int seatA,int seatB,int seatC ){
        //limit amount
        int total = seatA+seatB+seatC ;
        if( total > SystemDefault.SEAT_NOT_SELECTED_MAX ) {     //limitation
            return -1 ;
        }
        int[] ticketNum = {seatA,seatB,seatC} ;

        List<Seat> seats = seatService.lockSeat(userID, planID, ticketNum) ;
//        for(int j:ticketNum)
//            System.out.println("ticketService 2 :ticket num"+j);

        boolean lockSeatSuccess = true ;
        if(seats==null)
            lockSeatSuccess = false  ;
        if(seats.size()<total) {
            lockSeatSuccess = false;
            //TODO 解锁
        }
//        System.out.println("TicketService lockSeat:"+lockSeatSuccess);
        if(!lockSeatSuccess) {
            return -2 ;  //maybe seat is not enough
        }

        SitePlan sitePlan = planService.getPlanByID(planID);

        double original_price_A = sitePlan.getOriginal_price_A() ;
        double original_price_B = sitePlan.getOriginal_price_B() ;
        double original_price_C = sitePlan.getOriginal_price_C() ;
        double[] original_price = {original_price_A,original_price_B,original_price_C} ;

        //折扣力度
//        double[] discountDetail = SystemDefault.switchDiscount(userInfo.getLevel());
        double[] discountDetail = {1,1,1} ;


        //create new TicketRecord
        TicketRecord tr;
        for(Seat seat : seats ) {
            String seatNumber = seat.getSeatNumber() ;
            int type = seatNumber.charAt(0) - 'A' ;
            double price = discountDetail[type] * original_price[type];
            tr = new TicketRecord(userID, sitePlan.getSiteID(), planID,
                    seatNumber,price,SystemDefault.RECORD_PAYTYPE_CASH) ;
            tr.setIsValid(SystemDefault.RECORD_STATE_PAYED) ;
            ticketRecordRepository.save(tr) ;
            // add consume record to UserInfo..
            if(userID>0) {
                userService.addCredit(tr) ;
            }
        }

        //unlocked tickets
        List<String> seatNameList = new ArrayList<>() ;
        for(Seat seat : seats ) {
            seatNameList.add(seat.getSeatNumber()) ;
        }
        boolean unlockSeatSuccess = seatService.unlockSeat(userID,planID,seatNameList,true);
        if(!unlockSeatSuccess)
            return -3 ;
        return 0 ;
    }

//    //检票
//    public void checkTicket(int planID,List<String> seats){
//        for(String seatNumber : seats) {
//            Seat that = seatRepository.findByPlanIDAndSeatNumber(planID,seatNumber) ;
//            if( null != that) {
//                that.setIsChecked(true);
//                seatRepository.save(that);
//            }
//        }
//    }

    TicketRecord findRecord(int recordID) {
        return ticketRecordRepository.findById(recordID).get() ;
    }


}
