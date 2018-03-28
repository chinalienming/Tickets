package nju.entity;

import nju.util.SystemDefault;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/10.
 */

@Entity
@Table(name = "user_info", schema = "tickets" )
public class UserInfo {

    private int userID ;
    private String userName = "name";
    private double balance = 0.00 ;
    private int level = 1 ;
    private double consumption = 0.00 ;
    private int credit = 0 ;

    public UserInfo() {

    }

    @Id
    @Column(name= "user_id",nullable = false)
    public int getUserID(){
        return this.userID ;
    }
    public void setUserID(int newID) {
        this.userID = newID ;
    }

    @Basic
    @Column(name = "user_name",nullable = false, length = 20)
    public String getUserName(){
        return this.userName ;
    }
    public void setUserName(String name) {
        this.userName = name ;
    }


    @Basic
    @Column(name = "balance", nullable = false)
    public double getBalance(){
        return this.balance ;
    }
    public void setBalance(double balance) { this.balance=balance ; }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return this.level ;
    }
    public void setLevel(int level) { this.level = level; }

    @Basic
    @Column(name = "consumption", nullable = false)
    public double getConsumption() {
        return this.consumption ;
    }
    public void setConsumption(double consumption) { this.consumption = consumption; }

    @Basic
    @Column(name = "credit", nullable = false)
    public int getCredit() {
        return this.credit ;
    }
    public void setCredit(int credit) { this.credit = credit; }



    public boolean removeBalance( double amount ) {
        if ( this.balance < amount )
            return false ;
        else {
            this.balance -= amount ;
            return true ;
        }
    }

    public void addCredit(double amount,TicketRecord tr) {
        this.consumption += amount ;
        int creditAdd = (int)(amount * SystemDefault.CREDIT_RATE) ;
        this.credit += creditAdd ;

        tr.setCreditAdd(creditAdd);     //need update !

        //check level up.
        while(this.consumption>= level * SystemDefault.USER_EXP) {
            this.level ++ ;
            levelUpTips();
        }

    }

    private void levelUpTips(){
        System.out.println("User "+userID+" Level Up!");
    }

    public void drawback(double return_amount, TicketRecord tr) {// need credit punishment ?

        this.balance += return_amount ;
        this.consumption -= return_amount ;

        this.credit -= tr.getCreditAdd() ;

        tr.setCreditAdd(0);// clear.
        tr.setIsValid(SystemDefault.RECORD_STATE_CANCEL);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo that = (UserInfo) o;

        if (userID != that.userID) return false;
        if (level != that.level) return false;
        if (credit != that.credit) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + credit;
        result = 31 * result + (int) consumption;
        result = 31 * result + (int) balance ;
        return result;
    }
}
