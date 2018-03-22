package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/12.
 */

@Entity
@Table(name = "seat", schema = "tickets")
public class Seat {

    private int seatID ;
    private int planID ;
    private String seatNumber ;
    private int state = 0 ; // 0 = empty , 1 = locked , 2 = purchased
    private int userID = -1;
    private boolean isChecked = false ;
    public Seat() {}

    public Seat(int planID,String seatNumber) {
        this.planID = planID ;
        this.seatNumber = seatNumber ;
    }

    @GeneratedValue
    @Id
    @Column(name = "seat_id",nullable = false)
    public int getSeatID(){return this.seatID;}
    public void setSeatID(int seatID){this.seatID=seatID;}


    @Basic
    @Column(name = "plan_id", nullable = false)
    public int getPlanID(){
        return this.planID ;
    }
    public void setPlanID(int planID) { this.planID = planID ; }

    @Basic
    @Column(name = "seat_number")
    public String getSeatNumber(){return this.seatNumber;}
    public void setSeatNumber(String seatNumber){this.seatNumber=seatNumber;}

    @Basic
    @Column(name = "state",nullable = false)
    public int getState(){return this.state;}
    public void setState(int s){this.state=s;}

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserID(){
        return this.userID ;
    }
    public void setUserID(int userID) { this.userID = userID ; }

    @Basic
    @Column(name = "is_checked", nullable = false)
    public boolean getIsChecked(){return this.isChecked;}
    public void setIsChecked(boolean b){this.isChecked = b;}
}
