package com.example.kline.core;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author yang
 * @date 2025/3/20
 * @desc
 */
public class Formatter {
    private final DecimalFormat priceFormat = new DecimalFormat("0.00");
    private final SimpleDateFormat dateFormat;
    private final Date date = new Date();

    public Formatter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.SIMPLIFIED_CHINESE);
        dateFormat.setTimeZone(TimeZone.getDefault());
    }

    public String formatPrice(double obj) {
        return priceFormat.format(obj);
    }

    public String formatDate(long time) {
        date.setTime(time);
        return dateFormat.format(date);
    }
}
