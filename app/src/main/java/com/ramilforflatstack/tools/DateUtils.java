package com.ramilforflatstack.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ramil on 15.06.2015.
 */
public class DateUtils {

    public static String getTextDate(long msec){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(msec * 1000));

        Calendar yesterday = (Calendar) calendar.clone();
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        String date;
        if(calendar.after(yesterday)) {
            date = calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + StringUtils.toLength(Integer.toString(calendar.get(Calendar.MINUTE)), 2);
        } else {
            date = StringUtils.toLength(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), 2) + "."
                    + StringUtils.toLength(Integer.toString(calendar.get(Calendar.MONTH)), 2) + "."
                    + calendar.get(Calendar.YEAR);
        }

        return date;
    }
}
