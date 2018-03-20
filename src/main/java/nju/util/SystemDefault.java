package nju.util;

/**
 * Created by lienming on 2018/3/10.
 */
public class SystemDefault {

    public static final String USER_ID = "userID" ;
    public static final String PLAN_ID = "planID" ;


    public static final String SITES = "sites" ;
    public static final String PLANS = "plans" ;

    public static final String PLAN_DETAIL = "plan_detail" ;

    public static final String SEAT_LIST = "seatList" ;
    public static final String TICKET_NUM = "ticketNum" ;

    public static final String TICKET_RECORDS = "ticket_records" ;


    public static final String CURRENT_PAGE = "current" ;

    public static final int SIZE_PER_PAGE_OF_SITE = 6 ;


    public static final String HTTP_RESULT = "result" ;
    public static final String HTTP_REASON = "reason" ;


    /**
     * Credite Rule
     */
    public static final double CREDIT_RATE = 0.1 ; //consuming amount to credit

    public static final int CREDIT_EXP = 100 ; //credit to level

    /**
     * Discount Rule
     */
    public final static double[] INIT_DISCOUNT = { 0.90 , 0.95 , 1.00 } ; //discount for level 1 ;

    public static double[] switchDiscount(int userLevel) {
        double[] result = INIT_DISCOUNT ;

        // NOTE : Highest level is 10 .
        if( userLevel > 10 )
            return null ;

        for(int cycle_time = userLevel ; cycle_time > 1  ; cycle_time-- ) {
            for(int position = 0 ; position < result.length ; position++ ) {
                result[position] -= 0.05 ;
            }
        }

        return result ;
    }

    /**
     * Order Cancel Rule
     */
    public static double returnRate(int hours) {
        if( 0 <= hours && hours < 72 )   // in a day
            return 0.5 ;
        else if( 72 <= hours && hours < 168 )    // in 3 days
            return 0.7 ;
        else if( 168 <= hours && hours < 336 )       // in 2 weeks
            return 0.9 ;
        else
            return 1.0 ;
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
        char[] array = str.toCharArray();
        for(Character ch : array){
            if(ch<'0' || ch > 'z') return false ;
            else if(ch >'9' && ch <'A') return false ;
            else if(ch >'Z' && ch <'a') return false ;
        }
        return true ;
    }

}
