package nju.service;

import nju.entity.Seat;
import nju.entity.TicketRecord;

import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
public interface TicketService {


    /**
     * ticket transaction WITH seat position demand
     */
    boolean buyTicket(int userID , int planID , List<String> seatList );

    /**
     * ticket transaction WITHOUT seat position demand
     */
    boolean buyTicket(int userID , int planID , int[] ticketNum);

    /**
     * Match ticket 2 weeks before performance.
     */
    List<Seat> matchTicket(int planID,int userID) ;

    /**
     * @return Code of result
     */
    int cancelOrder(int recordID) ;

    boolean restoreSeat(int recordID);

    boolean buyTicketOffline(int planID,int userID,List<String> seats);

    void checkTicket(int planID,List<String> seats);

}
