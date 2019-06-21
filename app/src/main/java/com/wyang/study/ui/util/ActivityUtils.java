package com.wyang.study.ui.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by weiyang on 2019/6/20.
 */
public class ActivityUtils {
    public static void startActivity(Context context, Class<?> clz) {
        startActivity(context, clz, null);
    }

    public static void startActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
