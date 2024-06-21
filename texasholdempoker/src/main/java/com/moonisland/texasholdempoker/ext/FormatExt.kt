package com.moonisland.texasholdempoker.ext

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */

fun formatRecordStatus(value: Int) = when (value) {
    0 -> "未开始"
    1 -> "进行中"
    2 -> "已结束"
    else -> ""
}