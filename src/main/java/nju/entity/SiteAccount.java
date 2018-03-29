package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/29.
 */

@Entity
@Table(name = "site_account", schema = "tickets")
public class SiteAccount {
    private int siteID ;
    private String name ;
    private String password ;
    private boolean active = false ;

    @GeneratedValue
    @Id
    @Column(name = "site_id",nullable = false)
    public int getSiteID(){return this.siteID;}
    public void setSiteID(int sid){this.siteID=sid;}

    @Basic
    @Column(name = "name", nullable = false)
    public String getName(){
        return this.name ;
    }
    public void setName(String n) { this.name = n ; }

    @Basic
    @Column(name = "password")
    public String getPassword(){return this.password ;}
    public void setPassword(String s){this.password=s;}

    @Basic
    @Column(name = "active",nullable = false)
    public boolean getActive(){return this.active;}
    public void setActive(boolean b){this.active = b ;}



}
