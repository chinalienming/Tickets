package nju.entity;


import javax.persistence.*;

@Entity
@Table(name="open_apply",schema="tickets")
public class OpenApply {

    private int oaID ;
    private int siteID ;
    private String reason ;
    private int state = 0 ;

    @GeneratedValue
    @Id
    @Column(name = "oa_id",nullable = false)
    public int getOaID(){return this.oaID;}
    public void setOaID(int oaid){this.oaID=oaid;}

    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID ;
    }
    public void setSiteID(int sid) { this.siteID = sid ; }

    @Basic
    @Column(name = "reason")
    public String getReason(){return this.reason;}
    public void setReason(String s){this.reason=s;}


    @Basic
    @Column(name = "state", nullable = false)
    public int getState(){
        return this.state ;
    }
    public void setState(int s) { this.state = s ; }

}
