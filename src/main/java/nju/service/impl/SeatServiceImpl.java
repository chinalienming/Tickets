package nju.service.impl;

import nju.dao.SeatRepository;
import nju.dao.SitePlanRepository;
import nju.entity.Seat;
import nju.service.PlanService;
import nju.service.SeatService;
import nju.util.SystemDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lienming on 2018/3/12.
 */
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private PlanService planService ;

    @Autowired
    private SitePlanRepository sitePlanRepository ;
    @Autowired
    private SeatRepository seatRepository ;

    public boolean lockSeat(int userID , int planID, List<String> seatList) {

        for(String seatNumber:seatList) {
            Seat seat = seatRepository.findByPlanIDAndSeatNumber(planID,seatNumber) ;
            seat.setState(SystemDefault.SEAT_STATE_LOCK);
            seat.setUserID(userID);
            seatRepository.save(seat);
        }
        return true ;
    }

    /**
     * 为未选择座位号的顾客买票
     */
    public List<Seat> lockSeat(int userID , int planID , int[] ticketNum ) {

        int[] remainingSeatNum = getSeatTypeNum(planID) ;

        for(int i=0; i<remainingSeatNum.length;i++) { //check enough
            if(remainingSeatNum[i]<ticketNum[i]) {
                return null ;
            }
        }
        return allocateSeats(userID,planID,ticketNum) ;
    }

    /**
     * 根据支付结果和座位号,解锁座位
     * @return transferSuccess
     */
    public boolean unlockSeat(int userID,int planID,List<String> seatList,boolean transferSuccess) {
        if(transferSuccess) {
            for(String seatNumber : seatList ){
                Seat seat = seatRepository.findByPlanIDAndSeatNumber(planID,seatNumber) ;
                seat.setState(SystemDefault.SEAT_STATE_PURCHASED);
                seatRepository.save(seat);
            }
        } else {
            for(String seatNumber : seatList ){
                Seat seat = seatRepository.findByPlanIDAndSeatNumber(planID,seatNumber) ;
                seat.setState(SystemDefault.SEAT_STATE_EMPTY);
                seat.setUserID(-1);
                seatRepository.save(seat);
            }
        }

        return transferSuccess ;
    }

    /**
     * 为未选择座位号的顾客提前分配座位号
     */
    List<Seat> allocateSeats(int userID , int planID , int[] ticketNum ) {
        List<Seat> resultList = new ArrayList<>() ;
        List<Seat> seats = getAvailableSeatsByPlanID(planID) ;
        int[] total_need = ticketNum;
        boolean[] still_need = new boolean[total_need.length] ;
        for(int i=0 ; i<total_need.length; i++ ) {
            if(total_need[i]>0)
                still_need[i]=true ;
            else
                still_need[i]=false;
        }

        for(int i=0 ; i<seats.size() ; i++) {
            //check
            boolean unfinished = false ;
            for(int j=0 ; j<still_need.length ; j++) {
                unfinished = unfinished | still_need[j] ;
            }

            if(unfinished) {
                Seat seat = seats.get(i);
                int type = seat.getSeatNumber().charAt(0) - 'A';
                if (still_need[type]) {
                    seat.setUserID(userID);
                    seat.setState(SystemDefault.SEAT_STATE_LOCK);
                    seatRepository.save(seat);
                    resultList.add(seat);
                    total_need[type]--;
                    if (total_need[type] == 0) {
                        still_need[type] = false;
                    }
                }
            } else {
                break ;
            }
        }

        return resultList ;
    }

    //mysql
    public boolean checkSeatExists(int planID,List<String> seatList) {
        for(String seatName : seatList) {
            Seat seat = seatRepository.findByPlanIDAndSeatNumber(planID,seatName) ;
            if( null == seat ) return false ;
            if( seat.getState() != SystemDefault.SEAT_STATE_EMPTY ) return false ;
        }
        return true ;
    }

    //mysql
    public List<Seat> getAvailableSeatsByPlanID(int planID) {
        return seatRepository.findByPlanIDAndState(planID,0) ;
    }

    //mysql
    public int[] getSeatTypeNum(int planID) {
        List<Seat> list = getAvailableSeatsByPlanID(planID) ;
        int[] result = new int[SystemDefault.SEAT_TYPE_NUM] ;
        for(Seat seat : list ) {
            result[seat.getSeatNumber().charAt(0)-'A'] ++ ;
        }
        return result ;
    }

}
