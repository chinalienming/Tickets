package nju.service;

import nju.entity.Seat;

import java.util.List;

/**
 * Created by lienming on 2018/3/12.
 */
public interface SeatService {

    /**
     * 确认需要的座位均可得
     * */
    boolean checkSeatExists(int planID,List<String> seatList) ;

    /**
     * 获得计划内各种类型座位可得数
     */
    int[] getSeatTypeNum(int planID) ;

    /**
     * 给定需要的座位号，锁定座位
     */
    boolean lockSeat(int userID , int planID, List<String> seatList) ;

    /**
     * 给定需要的座位数，锁定座位
     */
    List<Seat> lockSeat(int userID , int planID, int[] ticketNum ) ;

    /**
     * 根据支付结果和座位号,解锁座位
     * @return transferSuccess
     */
    boolean unlockSeat(int userID,int planID, List<String> seatList ,boolean transferSuccess) ;



    List<Seat> getAvailableSeatsByPlanID(int planID) ;

    int[] getTotalNum(int planID) ;

    List<String> getUnavailableSeatsByPlanID(int planID) ;

    String[] getSeatsMap(int planID) ;
}
