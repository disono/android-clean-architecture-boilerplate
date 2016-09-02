package disono.webmons.com.clean_architecture.utilities.helpers;

import java.text.DateFormatSymbols;
import java.util.Date;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/1/2016 10:22 PM
 */
public class WBTime {
    public static long unix() {
        return System.currentTimeMillis();
    }

    public static long unixTimeStamp() {
        return unix() / 1000L;
    }

    public static long addMinuteUnix(int minute) {
        return unix() + 5 * minute * 1000;
    }

    public static Date currentDate() {
        return new Date(unix());
    }

    public static Date addMinuteDate(int minute) {
        return new Date(addMinuteUnix(minute));
    }

    public static Date minusMinuteDate(int minute) {
        return new Date(unix() - (60 * minute) * 1000);
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();

        if (num >= 0 && num <= 11) {
            month = months[num];
        }

        return month;
    }
}
