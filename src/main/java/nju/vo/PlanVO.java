package nju.vo;

import java.sql.Timestamp;

/**
 * Created by lienming on 2018/3/14.
 */
public class PlanVO {

    private int planID ;
    private String siteID ;
    private Timestamp beginTime ;
    private Timestamp endTime ;
    private String planType ;
    private String description = null ;
    private int seat_A ;
    private int seat_B ;
    private int seat_C ;
    private double original_price_A ;
    private double original_price_B ;
    private double original_price_C ;
    private double planIncome ;


}
