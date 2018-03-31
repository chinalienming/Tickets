package nju.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "settlement", schema = "tickets")
public class Settlement {

    private int settleID ;
    private int planID ;
    private int siteID ;
    private Timestamp createTime ;
    private double price ;

    public Settlement(){
        this.createTime = new Timestamp(System.currentTimeMillis()) ;
    }

    @GeneratedValue
    @Id
    @Column(name="settle_id",nullable = false)
    public int getSettleID(){return this.settleID; }
    public void setSettleID(int sID){this.settleID = sID;}

    @Basic
    @Column(name = "plan_id", nullable = false)
    public int getPlanID(){ return this.planID ; }
    public void setPlanID(int newID) { this.planID = newID ; }

    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID;
    }
    public void setSiteID(int siteID){
        this.siteID = siteID ;
    }

    @Basic
    @Column(name = "create_time",nullable = false)
    public Timestamp getCreateTime(){return this.createTime;}
    public void setCreateTime(Timestamp ts){this.createTime=ts;}

    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price){
        this.price = price ;
    }

}
