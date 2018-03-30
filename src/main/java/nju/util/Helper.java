package nju.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by lienming on 2018/3/30.
 */
public class Helper {


    public static Timestamp getTimeStamp(String time) {
        return Timestamp.valueOf(time + " 00:00:00.0");
    }


    public static String timeToDateString(Timestamp time) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(time);
    }
}
