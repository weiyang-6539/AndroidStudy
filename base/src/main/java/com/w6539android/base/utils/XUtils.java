package com.w6539android.base.utils;

import android.content.res.Resources;

/*
 * @author Yang
 * @since 2023/1/14 16:54
 * @desc
 */
public class XUtils {

    private static Resources getResources() {
        return Resources.getSystem();
    }

    public static int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }
}
