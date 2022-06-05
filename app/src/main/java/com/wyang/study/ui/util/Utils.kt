package com.wyang.study.ui.util

import android.app.ActivityManager
import android.content.Context

class Utils {

    fun detectOpenGLES20(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return am.deviceConfigurationInfo.glEsVersion > 0x20000.toString()
    }
}