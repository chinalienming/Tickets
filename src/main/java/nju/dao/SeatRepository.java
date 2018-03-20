package nju.dao;

import nju.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lienming on 2018/3/12.
 */
public interface SeatRepository extends JpaRepository<Seat,Integer> {

    Seat findByPlanIDAndSeatNumber(int planID,String seatNumber) ;

    List<Seat> findByPlanIDAndUserID(int planID,int userID) ;

    List<Seat> findByPlanIDAndState(int planID,int state) ;

}
