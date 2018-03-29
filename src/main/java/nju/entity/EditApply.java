package nju.entity;


import javax.persistence.*;

@Entity
@Table(name="edit_apply",schema="tickets")
public class EditApply {
    private int eaID ;
    private int siteID ;
    private String newName ;
    private String newAdd ;
    private int state = 0 ;
    private int num_a ;
    private int num_b ;
    private int num_c ;

    @GeneratedValue
    @Id
    @Column(name = "ea_id",nullable = false)
    public int getEaID(){return this.eaID;}
    public void setEaID(int eaid){this.eaID=eaid;}

    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID ;
    }
    public void setSiteID(int sid) { this.siteID = sid ; }

    @Basic
    @Column(name = "new_name")
    public String getNewName(){return this.newName;}
    public void setNewName(String s){this.newName=s;}

    @Basic
    @Column(name = "new_add",nullable = false)
    public String getNewAdd(){return this.newAdd;}
    public void setNewAdd(String d){this.newAdd=d;}


    @Basic
    @Column(name = "state", nullable = false)
    public int getState(){
        return this.state ;
    }
    public void setState(int s) { this.state = s ; }

    @Basic
    @Column(name = "num_a" , nullable = false)
    public int getNum_a(){return this.num_a;}
    public void setNum_a(int d){this.num_a=d;}

    @Basic
    @Column(name = "num_b" , nullable = false)
    public int getNum_b(){return this.num_b;}
    public void setNum_b(int d){this.num_b=d;}

    @Basic
    @Column(name = "num_c" , nullable = false)
    public int getNum_c(){return this.num_c;}
    public void setNum_c(int d){this.num_c=d;}

}
