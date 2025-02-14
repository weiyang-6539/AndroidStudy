package com.moonisland.texasholdempoker.ext

import android.annotation.SuppressLint

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */

fun formatRecordStatus(value: Int) = when (value) {
    0 -> "未开始"
    1 -> "进行中"
    2 -> "已结束"
    else -> " "
}

@SuppressLint("DefaultLocale")
fun Float?.formatFloat() = String.format("%.2f", this ?: 0f)