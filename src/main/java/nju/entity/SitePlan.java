package nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lienming on 2018/3/10.
 */
@Entity
@Table(name = "site_plan", schema = "tickets")
public class SitePlan {

    private int planID ;
    private int siteID ;
    private Timestamp beginTime ;
    private Timestamp endTime ;
    private String planType ;
    private String description = null ;
    private int seat_A = 10 ;
    private int seat_B = 10 ;
    private int seat_C = 10 ;
    private double original_price_A ;
    private double original_price_B ;
    private double original_price_C ;
    private double planIncome = 0.00 ;

    public SitePlan() {}

    public SitePlan(int siteID,String planType,  String description,
                    Timestamp beginTime , Timestamp endTime ,
                    double price_A,double price_B,double price_C ) {

        this.siteID = siteID ;
        this.planType = planType ;
        this.description = description ;
        this.beginTime = beginTime ;
        this.endTime = endTime ;
        this.original_price_A = price_A ;
        this.original_price_B = price_B ;
        this.original_price_C = price_C ;
    }



    @GeneratedValue
    @Id
    @Column(name="plan_id",nullable = false)
    public int getPlanID() {
        return this.planID ;
    }
    public void setPlanID(int planID){ this.planID = planID; }

    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID;
    }
    public void setSiteID(int siteID){
        this.siteID = siteID ;
    }

    @Basic
    @Column(name = "begin_time",nullable = false)
    public Timestamp getBeginTime(){return this.beginTime;}
    public void setBeginTime(Timestamp ts){this.beginTime=ts;}

    @Basic
    @Column(name = "end_time",nullable = false)
    public Timestamp getEndTime(){return this.endTime;}
    public void setEndTime(Timestamp ts){this.endTime=ts;}

    @Basic
    @Column(name = "plan_Type",nullable = false)
    public String getPlanType(){
        return this.planType;
    }
    public void setPlanType(String planType){
        this.planType = planType ;
    }

    @Basic
    @Column(name = "description")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Basic
    @Column(name = "seat_a",nullable = false)
    public int getSeat_A(){return this.seat_A;}
    public void setSeat_A(int num){this.seat_A=num;}

    @Basic
    @Column(name = "seat_b",nullable = false)
    public int getSeat_B(){return this.seat_B;}
    public void setSeat_B(int num){this.seat_B=num;}

    @Basic
    @Column(name = "seat_c",nullable = false)
    public int getSeat_C(){return this.seat_C;}
    public void setSeat_C(int num){this.seat_C=num;}

    @Basic
    @Column(name = "price_a", nullable = false)
    public double getOriginal_price_A() {
        return this.original_price_A;
    }
    public void setOriginal_price_A(double price){
        this.original_price_A = price ;
    }

    @Basic
    @Column(name = "price_b", nullable = false)
    public double getOriginal_price_B() {
        return this.original_price_B;
    }
    public void setOriginal_price_B(double price){
        this.original_price_B = price ;
    }

    @Basic
    @Column(name = "price_c", nullable = false)
    public double getOriginal_price_C() {
        return this.original_price_C;
    }
    public void setOriginal_price_C(double price){
        this.original_price_C = price ;
    }

    @Basic
    @Column(name = "plan_income", nullable = false)
    public double getPlanIncome(){return this.planIncome;}
    public void setPlanIncome(double income){this.planIncome=income;}
}


//    public List<String> remainingSeat ;   //
//    private Map<String,Integer> selected_lockedSeat ;      //Map from <seatString> to <userID>
//    private Map<String,Integer> selected_purchasedSeat ;   //Map from <seatString> to <userID>
//    private Map<Integer,Integer[]> unselected_lockedSeat ;     //Map from <userID> to <TicketNum[3]>
//    private Map<Integer,Integer[]> unselected_purchasedSeat ;  //Map from <userID> to <TicketNum[3]>
//    public int[] remainingSeatNum ;
//    private int[] lockedSeatNum ;
//    private int[] purchasedSeatNum ;
//    private List<String> checkedSeat ; //检票登记
//    private Map<Integer,Double> incomeRecord ;   //= new HashMap<>();
//    private double planIncome ;
//    public Map<String,Integer> getSelected_lockedSeat(){
//        return this.selected_lockedSeat ;
//    }
//    public Map<String,Integer> getSelected_purchasedSeat(){
//        return this.selected_purchasedSeat ;
//    }
//    public Map<Integer,Integer[]> getUnselected_lockedSeat(){
//        return this.unselected_lockedSeat ;
//    }
//    public void setUnselected_lockedSeat(Map<Integer,Integer[]> uls) {
//        this.unselected_lockedSeat = uls;
//    }
//    public Map<Integer,Integer[]> getUnselected_purchasedSeat(){
//        return this.unselected_purchasedSeat ;
//    }
//    public void setUnselected_purchasedSeat(Map<Integer,Integer[]> ups){
//        this.unselected_purchasedSeat = ups ;
//    }
//    public int[] getLockedSeatNum() {
//        return this.lockedSeatNum ;
//    }
//    public void addLockedSeatNum(int i,int amount){
//        this.lockedSeatNum[i] += amount ;
//    }
//    public int[] getPurchasedSeatNum() {
//        return this.purchasedSeatNum ;
//    }
//    public void addPurchasedSeatNum(int i,int amount){
//        this.purchasedSeatNum[i] += amount ;
//    }