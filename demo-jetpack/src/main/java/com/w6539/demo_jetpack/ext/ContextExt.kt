package com.w6539.demo_jetpack.ext

import android.app.Activity
import android.content.Intent

/**
 * @author Yang
 * @since 2022/12/7 9:27
 * @desc
 */
inline fun <reified T : Activity> Activity.navigate(): Activity {
    startActivity(Intent(this, T::class.java))
    return this
}