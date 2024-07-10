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

fun toast(message: String) {
    Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
}

fun View.applyGone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}