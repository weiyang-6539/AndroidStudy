package com.moonisland.texasholdempoker.ext

import android.view.View
import android.widget.Toast
import com.moonisland.texasholdempoker.global.App

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */

fun View.click(
    interval: Long = 800,
    action: () -> Unit,
) {
    var viewLastClickTime = 0L
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (viewLastClickTime != 0L && (currentTime - viewLastClickTime < interval)) {
            return@setOnClickListener
        }
        viewLastClickTime = currentTime
        action()
    }
}

/**
 * 连击的拓展函数
 */
fun View.clicks(num: Int, action: () -> Unit) {
    var viewLastClickTime = 0L
    var clickCount = 0
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - viewLastClickTime < 1000) {
            clickCount++
        } else {
            clickCount = 0
        }
        viewLastClickTime = currentTime

        // 当clickCount=1时实际已经连续点击2次了
        if (clickCount + 1 >= num) {
            action()
            clickCount = 0
        }
    }
}

fun toast(message: String) {
    Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
}

fun View.applyGone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}