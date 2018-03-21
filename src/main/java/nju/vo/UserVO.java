package nju.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lienming on 2018/3/14.
 *
 * NOTICE:
 * 如果使用方法BeanUtils.copyProperties(xx),必须在vo内有getter setter.
 */
public class UserVO {

    public int userID ;
    public String email ;
    public String password ;
    public boolean isActivated ;
    public boolean hasLoginQualification ;


    public String userName  ;
    public double balance  ;
    public int level ;
    public double consumption;
    public int credit ;

    public int accountID ;
    public double accountBalance ;


    public int getUserID(){
        return this.userID ;
    }
    public void setUserID(int newID) { this.userID = newID ; }

    public String getEmail(){
        return this.email ;
    }
    public void setEmail(String email) {
        this.email = email ;
    }

    public String getPassword(){
        return this.password ;
    }
    public void setPassword(String password){
        this.password = password ;
    }

    public boolean getHasLoginQualification(){
        return  this.hasLoginQualification ;
    }
    public void setHasLoginQualification(boolean b){
        this.hasLoginQualification = b ;
    }

    public boolean getActivated(){
        return this.isActivated ;
    }
    public void setActivated(boolean b){
        this.isActivated = b ;
    }

    public String getUserName(){
        return this.userName ;
    }
    public void setUserName(String name) {
        this.userName = name ;
    }

    public double getBalance(){
        return this.balance ;
    }
    public void setBalance(double balance) { this.balance=balance ; }

    public int getLevel() {
        return this.level ;
    }
    public void setLevel(int level) { this.level = level; }

    public double getConsumption() {
        return this.consumption ;
    }
    public void setConsumption(double consumption) { this.consumption = consumption; }

    public int getCredit() {
        return this.credit ;
    }
    public void setCredit(int credit) { this.credit = credit; }











}
