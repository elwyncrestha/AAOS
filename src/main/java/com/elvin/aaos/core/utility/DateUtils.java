package com.elvin.aaos.core.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";
    public static String yyyy_MM_dd_hh_MM_ss = "yyyy-MM-dd hh:MM:ss";
    public static String HH_mm = "HH:mm";

    private static SimpleDateFormat simpleDateFormat;

    public static Date convertDateTime(String dateTime, String pattern) {
        Date converted;
        simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            converted =  simpleDateFormat.parse(dateTime);
        } catch (Exception e) {
            converted = null;
        }
        return converted;
    }

}
