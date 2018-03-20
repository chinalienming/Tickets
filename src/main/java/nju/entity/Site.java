package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/10.
 */

@Entity
@Table(name = "site", schema = "tickets" )
public class Site {

    private int siteID ;   // Notice :  length must be 7 digit
    private String siteName = "sitename";
    private String address = "Nanjing" ;
    private int seatNumber_A = 0 ;  // seat type for A,B,C ..
    private int seatNumber_B = 0  ;
    private int seatNumber_C = 0  ;
    private double income = 0.00 ;

    public Site(){
    }

    public Site(String siteName,String address,
                int seatNumber_A,int seatNumber_B,int seatNumber_C,double income){
        this.siteName = siteName ;
        this.address = address ;
        this.seatNumber_A = seatNumber_A ;
        this.seatNumber_B = seatNumber_B ;
        this.seatNumber_C = seatNumber_C ;
        this.income=income;
    }

    @GeneratedValue
    @Id
    @Column(name = "site_id", nullable = false)
    public int getSiteID(){
        return this.siteID;
    }
    public void setSiteID(int siteID){
        this.siteID = siteID ;
    }

    @Basic
    @Column(name = "site_name", nullable = false, length = 30)
    public String getSiteName() {
        return this.siteName;
    }
    public void setSiteName(String name){
        this.siteName = name ;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 50)
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address){
        this.address = address ;
    }

    @Basic
    @Column(name = "num_a", nullable = false)
    public int getSeatNumber_A(){
        return this.seatNumber_A;
    }
    public void setSeatNumber_A(int numa){
        this.seatNumber_A=numa;
    }

    @Basic
    @Column(name = "num_b", nullable = false)
    public int getSeatNumber_B(){
        return this.seatNumber_B;
    }
    public void setSeatNumber_B(int numb){
        this.seatNumber_B=numb;
    }

    @Basic
    @Column(name = "num_c", nullable = false)
    public int getSeatNumber_C(){
        return this.seatNumber_C;
    }
    public void setSeatNumber_C(int numc){
        this.seatNumber_A=numc;
    }

    @Basic
    @Column(name = "income", nullable = false)
    public double getIncome() {
        return this.income;
    }
    public void setIncome(double income){
        this.income = income ;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Site that = (Site) o;
//
//        if (seatNumber_A != that.seatNumber_A) return false;
//        if (seatNumber_B != that.seatNumber_B) return false;
//        if (seatNumber_C != that.seatNumber_C) return false;
//        if (income != income) return false;
//
////        if (siteID != null ? !siteID.equals(that.siteID) : that.siteID != null) return false;
//        if (siteName != null ? !siteName.equals(that.siteName) : that.siteName != null) return false;
//        if (address != null ? !address.equals(that.address) : that.address != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = siteID != null ? siteID.hashCode() : 0 ;
//        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
//        result = 31 * result + (address != null ? address.hashCode() : 0);
//        result = 31 * result + (int) income ;
//        result = 31 * result + seatNumber_A ;
//        result = 31 * result + seatNumber_B ;
//        result = 31 * result + seatNumber_C ;
//        return result;
//    }
}
