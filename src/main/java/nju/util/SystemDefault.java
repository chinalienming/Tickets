package nju.util;

/**
 * Created by lienming on 2018/3/10.
 */
public class SystemDefault {

    public static final String USER_ID = "userID" ;
    public static final String PLAN_ID = "planID" ;


    public static final String SITES = "sites" ;
    public static final String PLANS = "plans" ;

    public static final String PLAN_DETAIL = "plandetail" ;

    public static final String SEAT_LIST = "seatList" ;
    public static final String TICKET_NUM = "ticketNum" ;

    public static final String TICKET_RECORDS = "ticketrecords" ;


    public static final String CURRENT_PAGE = "current" ;

    public static final int SIZE_PER_PAGE_OF_SITE = 6 ;


    public static final String HTTP_RESULT = "result" ;
    public static final String HTTP_REASON = "reason" ;

    /**
     * PayMessage Rule
     */
    public static final String PM_INTERNAL_PAY = "余额支付" ;
    public static final String PM_EXTERNAL_PAY = "支付宝支付" ;
    public static final String PM_EX2IN = "充值" ;
    public static final String PM_DRAWBACK = "退款" ;

    /**
     * Ticket Record Rule
     */
    public static final int RECORD_STATE_WAITPAY = 0 ;
    public static final int RECORD_STATE_PAYED = 1 ;
    public static final int RECORD_STATE_CANCEL = 2;
    public static final int RECORD_STATE_TIMEOUT = 3 ;
    public static final int RECORD_STATE_CHECKED = 4 ;

    public static final int RECORD_PAYTYPE_NOTPAY = -1 ;
    public static final int RECORD_PAYTYPE_CASH = 0 ;
    public static final int RECORD_PAYTYPE_BALANCE = 1 ;
    public static final int RECORD_PAYTYPE_ALIPAY = 2;

    /**
     *  Settlement
     */
    public static final double SETTLEMENT_RATE = 0.1 ;

    /***
     * OpenApply , EditApply
     */
    public static final int APPLY_STATE_APPROVE = 1 ;
    public static final int APPLY_STATE_DENY = 2 ;
    public static final int APPLY_STATE_WAIT = 0 ;

    /**
     * Credit Rule
     */
    public static final double CREDIT_RATE = 0.01 ; //consuming amount to credit

    public static final int USER_EXP = 10000 ; //credit to level

    /**
     * Discount Rule
     */
    public final static double[] INIT_DISCOUNT = { 0.90 , 0.95 , 1.00 } ; //discount for level 1 ;

    public static double[] switchDiscount(int userLevel) {
        double[] result = INIT_DISCOUNT ;

        if(userLevel<10) {
            return result ;
        }

        for(int cycle_time = userLevel ; cycle_time > 1  ; cycle_time-- ) {
            for(int position = 0 ; position < result.length ; position++ ) {
                result[position] -= 0.01 ;
            }
        }

        return result ;
    }

    /**
     * Ticket Cancel Rule
     */
    public static double returnRate(int hours) {
        if( 0 <= hours && hours < 72 )   // in a day
            return 0.5 ;
        else if( 72 <= hours && hours < 168 )    // in 3 days
            return 0.6 ;
        else if( 168 <= hours && hours < 336 )       // in 2 weeks
            return 0.7 ;
        else
            return 0.8 ;
    }

    /**
     * Seat Rule
     */

    public static final int SEAT_STATE_EMPTY = 0 ;

    public static final int SEAT_STATE_LOCK = 1 ;

    public static final int SEAT_STATE_PURCHASED = 2 ;

    public static final int SEAT_TYPE_NUM = 3 ;

    public static final int SEAT_SELECTED_MAX = 6 ;

    public static final int SEAT_NOT_SELECTED_MAX = 20 ;

    public static final int SEAT_FREE = -1 ;





    /**
     * User Rule
     */
    private static String USER_REGEX = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";

    public static boolean checkEmailLegal(String email) {
        return true ;
        //return email.matches(USER_REGEX);
    }

    /**
     * is all words or numbers ?
     */
    public static boolean checkString(String str){
//        char[] array = str.toCharArray();
//        for(Character ch : array){
//            if(ch<'0' || ch > 'z') return false ;
//            else if(ch >'9' && ch <'A') return false ;
//            else if(ch >'Z' && ch <'a') return false ;
//        }
        return true ;
    }

}
