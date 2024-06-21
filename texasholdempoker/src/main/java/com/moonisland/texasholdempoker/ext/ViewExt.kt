package com.moonisland.texasholdempoker.ext

import android.view.View
import android.widget.Toast
import com.moonisland.texasholdempoker.global.App

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
var viewLastClickTime = 0L
var clickCount = 0

fun View.click(
    interval: Long = 150,
    action: () -> Unit,
) {
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (viewLastClickTime != 0L && (currentTime - viewLastClickTime < interval)) {
            clickCount = 0
            return@setOnClickListener
        }
        viewLastClickTime = currentTime
        action()
    }
}

fun toast(message: String) {
    Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
}