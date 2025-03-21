package com.fxbandroid.basejetpack.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * @author Yang
 * @date 2025/2/22
 * @desc
 */
object DateUtils {
    fun format(
        pattern: String = "yyyy/MM/dd",
        time: Long = System.currentTimeMillis(),
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.SIMPLIFIED_CHINESE
    ): String =
        runCatching {
            SimpleDateFormat(pattern, locale)
                .also {
                    it.timeZone = timeZone
                }.format(Date(time))
        }.getOrElse {
            "-"
        }

    fun parse(
        pattern: String,
        date: String,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.SIMPLIFIED_CHINESE
    ): Date =
        runCatching {
            SimpleDateFormat(pattern, locale)
                .also {
                    it.timeZone = timeZone
                }.parse(date)
        }.getOrElse {
            Date()
        }
}