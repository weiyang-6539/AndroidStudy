package com.wyang.study.ui.util

import android.content.Context
import android.content.Intent
import android.os.Bundle

class ActivityUtils {
    companion object{
        fun startActivity(context: Context, clz: Class<*>?) {
            startActivity(context, clz, null)
        }

        fun startActivity(context: Context, clz: Class<*>?, bundle: Bundle?) {
            val intent = Intent()
            intent.setClass(context, clz!!)
            if (bundle != null) intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}