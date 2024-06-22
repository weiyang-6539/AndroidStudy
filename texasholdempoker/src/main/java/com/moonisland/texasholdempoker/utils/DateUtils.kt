package com.moonisland.texasholdempoker.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * @author Yang
 * @date 2024/5/23
 * @desc
 */
object DateUtils {
    fun format(
        pattern: String = "yyyy-MM-dd",
        time: Long = System.currentTimeMillis(),
        timeZone: TimeZone = TimeZone.getDefault()
    ): String {
        val format = SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE)
        format.timeZone = timeZone
        return format.format(Date(time))
    }

    fun parse(pattern: String, date: String, timeZone: TimeZone = TimeZone.getDefault()): Date {
        return kotlin.runCatching {
            val format = SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE)
            format.timeZone = timeZone
            format.parse(date)
        }.getOrElse {
            Date()
        }
    }
}