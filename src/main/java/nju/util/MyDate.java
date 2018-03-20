package nju.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by lienming on 2018/3/10.
 */
public class MyDate {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd") ;

    public static int hoursBetweenDate(Date startDate,Date endDate) {
        Calendar calendar = Calendar.getInstance() ;

        calendar.setTime(startDate);
        long startMillis = calendar.getTimeInMillis() ;

        calendar.setTime(endDate);
        long endMillis = calendar.getTimeInMillis() ;

        long result = (endMillis-startMillis)/(1000*60*60) ;
        return Integer.parseInt(String.valueOf(result)) ;
    }

    public static Date getPresentTime(){
        Calendar calendar = Calendar.getInstance() ;
        return calendar.getTime() ;
    }
}
