package nju.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pay_message", schema = "tickets")
public class PayMessage {

    private int payID ;
    private int userID ;
    private String msg ;
    private double money ;
    private Timestamp time ;


    @GeneratedValue
    @Id
    @Column(name = "pay_id",nullable = false)
    public int getPayID(){return this.payID;}
    public void setPayID(int pid){this.payID=pid;}

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserID(){
        return this.userID ;
    }
    public void setUserID(int uid) { this.userID = uid ; }

    @Basic
    @Column(name = "msg")
    public String getMsg(){return this.msg;}
    public void setMsg(String s){this.msg=s;}

    @Basic
    @Column(name = "money",nullable = false)
    public double getMoney(){return this.money;}
    public void setMoney(double d){this.money=d;}

    @Basic
    @Column(name = "time",nullable = false)
    public Timestamp getTime(){return this.time;}
    public void setTime(Timestamp ts){this.time=ts;}


}
