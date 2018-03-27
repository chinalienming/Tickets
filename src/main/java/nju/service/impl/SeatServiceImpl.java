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

        for(int j:ticketNum)
            System.out.println("seatService 1 :ticket num"+j);

        List<Seat> resultList = new ArrayList<>() ;
        List<Seat> seats = getAvailableSeatsByPlanID(planID) ;
        int[] total_need = new int[ticketNum.length];
        for(int i=0;i<ticketNum.length;i++){
            total_need[i] = ticketNum[i] ;
        }
        boolean[] still_need = new boolean[total_need.length] ;
        for(int i=0 ; i<total_need.length; i++ ) {
            if(total_need[i]>0)
                still_need[i]=true ;
            else
                still_need[i]=false;
        }

        for(int i=0 ; i<seats.size() ; i++) {
            //假设分配完成
            boolean unfinished = false ;
            //检查各种类型是否满足
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
        for(int j:ticketNum)
            System.out.println("seatService 1 :ticket num"+j);
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

    public int[] getTotalNum(int planID) {


        List<Seat> list =  seatRepository.findByPlanID(planID) ;

        int[] result = new int[SystemDefault.SEAT_TYPE_NUM] ;
        for(Seat seat : list ) {
            result[seat.getSeatNumber().charAt(0)-'A'] ++ ;
        }
        return result ;

    }

    public List<String> getUnavailableSeatsByPlanID(int planID)  {
        List<Seat> locklist = seatRepository.findByPlanIDAndState(planID,SystemDefault.SEAT_STATE_LOCK) ;
        List<Seat> unlist = seatRepository.findByPlanIDAndState(planID,SystemDefault.SEAT_STATE_PURCHASED) ;
        List<String> result = new ArrayList<>() ;
        for(Seat seat : locklist ) {
            result.add(seat.getSeatNumber()) ;
        }
        for(Seat seat : unlist ) {
            result.add(seat.getSeatNumber()) ;
        }
        return result;
    }

    public String[] getSeatsMap(int planID) {
        List<Seat> aList = getAvailableSeatsByPlanID(planID) ;
        int[] totalNum = getTotalNum(planID) ;

        // 默认A B C座位都是20 个 , 10*10座位
        char[][] resultChar = new char[10][10] ;
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                resultChar[i][j]='_' ;

        for(Seat seat :aList) {
            String number = seat.getSeatNumber() ; //"A01"

            char type = number.charAt(0) ;
            int row ;
            if(type=='A')
                row = 0 ;
            else if(type =='B')
                row = 2 ;
            else
                row = 4 ;

            int no = Integer.parseInt(number.substring(1)) ;
            if(no>10) {
                row++;
                no -= 10 ;
            }
            resultChar[row][no-1] = type ;
        }

        String[] result = new String[10] ;
        for(int i=0;i<10;i++) {
            String tmp = "";
            for(int j=0;j<10;j++) {
                tmp = tmp + resultChar[i][j] ;
            }
            result[i] = tmp ;
        }

        result[6] = "__________" ;
        result[7] = "__________" ;
        result[8] = "__________" ;
        result[9] = "__________" ;
        return result ;

    }
}
