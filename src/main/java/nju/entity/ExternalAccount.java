package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/10.
 */
@Entity
@Table(name="payment",schema="tickets")
public class ExternalAccount {

    private int accountID ;

    private String password ;

    private double balance = 0.00  ;

    private int userID  ;

    private boolean isSite = false ;

    public ExternalAccount() { }

    public ExternalAccount(int accountID , String pwd , int userID ) {
        this.accountID = accountID ;
        this.password = pwd ;
        this.userID = userID ;
    }


    @Id
    @Column(name = "account_id", nullable = false)
    public int getAccountID(){return this.accountID;}
    public void setAccountID(int id){this.accountID=id;}


    @Basic
    @Column(name = "password", nullable = false,length=20)
    public String getPassword(){
        return this.password ;
    }
    public void setPassword(String password){
        this.password = password ;
    }

    @Basic
    @Column(name = "balance", nullable = false)
    public double getBalance(){
        return this.balance ;
    }
    public void setBalance(double balance) { this.balance=balance ; }

    @Basic
    @Column(name = "user_id" )
    public int getUserID(){return this.userID ; }
    public void setUserID(int userID) { this.userID=userID;}

    @Basic
    @Column(name = "is_site" )
    public boolean getIsSite(){ return this.isSite ; }
    public void setIsSite(boolean b) { this.isSite=b;}
}
