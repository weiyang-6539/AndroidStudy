package com.wyang.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by *** on 2019-08-
 * 日期格式化工具类
 */
public class DateUtils {

    private static String format(String pattern, long time, TimeZone timeZone) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
        format.setTimeZone(timeZone);
        return format.format(new Date(time));
    }

    private static Date parse(String pattern, String date, TimeZone timeZone) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
            format.setTimeZone(timeZone);
            return format.parse(date);
        } catch (ParseException ignored) {
        }
        return new Date();
    }

    public static String formatGMT8Hms(long time) {
        return format("HH:mm:ss", time, TimeZone.getTimeZone("GMT+8"));
    }

    public static String formatGMT8Ym(long time) {
        return format("yyyy-MM", time, TimeZone.getTimeZone("GMT+8"));
    }

    public static String formatGMT8Ymd(long time) {
        return format("yyyy-MM-dd", time, TimeZone.getTimeZone("GMT+8"));
    }

    public static String formatGMT8YmdHm(long time) {
        return format("yyyy-MM-dd HH:mm", time, TimeZone.getTimeZone("GMT+8"));
    }

    public static String formatGMT8YmdHms(long time) {
        return format("yyyy-MM-dd HH:mm:ss", time, TimeZone.getTimeZone("GMT+8"));
    }

    public static String formatDefaultYmd(long time) {
        return format("yyyy/MM/dd", time, TimeZone.getDefault());
    }

    public static Date parseDefaultYmd(String date) {
        return parse("yyyy-MM-dd", date, TimeZone.getDefault());
    }

    public static Date parseGMT8Ymd(String date) {
        return parse("yyyy-MM-dd", date, TimeZone.getTimeZone("GMT+8"));
    }
}
