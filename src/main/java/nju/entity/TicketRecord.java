package nju.entity;


import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by lienming on 2018/3/10.
 */


@Entity
@Table(name="ticket_record",schema = "tickets")
public class TicketRecord {

    private int recordID ;
    private int userID ;
    private int siteID ;
    private int planID ;
    private char seatType ;
    private String seatNumber ;
    private double price ;
    private Timestamp createTime ;
    private boolean isValid = true  ;   // related to cancel order
    private int creditAdd = 0 ;

    public TicketRecord(){
    }

    public TicketRecord
            (int userID,int siteID,int planID,String seatNumber, double price ){

        this.userID = userID ;
        this.siteID = siteID ;
        this.planID = planID ;
        this.seatType = seatNumber.charAt(0)  ;
        this.seatNumber = seatNumber ;
        this.price = price ;
        this.createTime = new Timestamp(System.currentTimeMillis()) ;
    }

//    public TicketRecord
//            (int userID,String siteID,int planID,char seatType, double price ){
//        TicketRecord tr = new TicketRecord();
//        tr.userID = userID ;
//        tr.siteID = siteID ;
//        tr.planID = planID ;
//        tr.seatType = seatType ;
//        tr.seatNumber = null ;
//        tr.price = price ;
//        tr.createTime = new Timestamp(System.currentTimeMillis()) ;
//    }



    @GeneratedValue
    @Id
    @Column(name="record_id",nullable = false)
    public int getRecordID(){return this.recordID; }
    public void setRecordID(int recordID){this.recordID = recordID;}

    @Basic
    @Column(name="user_id",nullable = false)
    public int getUserID(){
        return this.userID ;
    }
    public void setUserID(int newID) { this.userID = newID ; }


    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID;
    }
    public void setSiteID(int siteID){
        this.siteID = siteID ;
    }

    @Basic
    @Column(name = "plan_id", nullable = false)
    public int getPlanID(){
        return this.planID ;
    }
    public void setPlanID(int newID) { this.planID = newID ; }

    @Basic
    @Column(name = "seat_type", nullable = false)
    public char getSeatType(){ return this.seatType;}
    public void setSeatType(char seatType){this.seatType=seatType;}

    @Basic
    @Column(name = "seat_number" )
    public String getSeatNumber(){ return this.seatNumber;}
    public void setSeatNumber(String seatNumber){this.seatNumber=seatNumber;}


    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price){
        this.price = price ;
    }

    @Basic
    @Column(name = "create_time",nullable = false)
    public Timestamp getCreateTime(){return this.createTime;}
    public void setCreateTime(Timestamp ts){this.createTime=ts;}

    @Basic
    @Column(name = "is_valid",nullable = false)
    public boolean getIsValid(){return this.isValid;}
    public void setIsValid(boolean b){this.isValid=b;}

    @Basic
    @Column(name = "credit_add" ,nullable = false)
    public int getCreditAdd(){
        return this.creditAdd ;
    }
    public void setCreditAdd(int add) { this.creditAdd = add ; }

}
