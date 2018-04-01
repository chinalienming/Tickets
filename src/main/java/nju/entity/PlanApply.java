//package nju.entity;
//
//import javax.persistence.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by lienming on 2018/3/30.
// */
//
//@Entity
//@Table(name = "plan_apply", schema = "tickets")
//public class PlanApply {
//
//    private int planID ;
//    private int siteID ;
//    private Date beginTime ;
//    private Date endTime ;
//    private String planType ;
//    private String description = null ;
//    private double original_price_A ;
//    private double original_price_B ;
//    private double original_price_C ;
//    private int state ;
//
//    public PlanApply() {}
//
//    public PlanApply(int siteID,String planType,  String description,
//                    String beginTime , String endTime ,
//                    double price_A,double price_B,double price_C ) {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
//
//        this.siteID = siteID ;
//        this.planType = planType ;
//        this.description = description ;
//        try {
//            this.beginTime = sdf.parse(beginTime) ;
//            this.endTime = sdf.parse(endTime) ;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        this.original_price_A = price_A ;
//        this.original_price_B = price_B ;
//        this.original_price_C = price_C ;
//    }
//
//
//
//    @GeneratedValue
//    @Id
//    @Column(name="plan_id",nullable = false)
//    public int getPlanID() {
//        return this.planID ;
//    }
//    public void setPlanID(int planID){ this.planID = planID; }
//
//    @Basic
//    @Column(name = "site_id", nullable = false)
//    public int getSiteID(){
//        return this.siteID;
//    }
//    public void setSiteID(int siteID){
//        this.siteID = siteID ;
//    }
//
//    @Basic
//    @Column(name = "begin_time",nullable = false)
//    public Date getBeginTime(){return this.beginTime;}
//    public void setBeginTime(Date ts){this.beginTime=ts;}
//
//    @Basic
//    @Column(name = "end_time",nullable = false)
//    public Date getEndTime(){return this.endTime;}
//    public void setEndTime(Date ts){this.endTime=ts;}
//
//    @Basic
//    @Column(name = "plan_Type",nullable = false)
//    public String getPlanType(){
//        return this.planType;
//    }
//    public void setPlanType(String planType){
//        this.planType = planType ;
//    }
//
//    @Basic
//    @Column(name = "description")
//    public String getDescription(){
//        return this.description;
//    }
//    public void setDescription(String description){
//        this.description = description;
//    }
//
//    @Basic
//    @Column(name = "price_a", nullable = false)
//    public double getOriginal_price_A() {
//        return this.original_price_A;
//    }
//    public void setOriginal_price_A(double price){
//        this.original_price_A = price ;
//    }
//
//    @Basic
//    @Column(name = "price_b", nullable = false)
//    public double getOriginal_price_B() {
//        return this.original_price_B;
//    }
//    public void setOriginal_price_B(double price){
//        this.original_price_B = price ;
//    }
//
//    @Basic
//    @Column(name = "price_c", nullable = false)
//    public double getOriginal_price_C() {
//        return this.original_price_C;
//    }
//    public void setOriginal_price_C(double price){
//        this.original_price_C = price ;
//    }
//
//    @Basic
//    @Column(name = "state",nullable = false )
//    public int getState(){return this.state;}
//    public void setState(int s){this.state=s;}
//}
