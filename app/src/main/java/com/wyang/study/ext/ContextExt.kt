package com.wyang.study.ext

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 * @author Yang
 * @since 2022/12/9 15:14
 * @desc
 */
fun Context.openAsset(filename: String): String {
    val sb = StringBuilder()
    try {
        val `is`: InputStream = assets.open(filename)
        val br = BufferedReader(InputStreamReader(`is`))
        var line: String?
        while (br.readLine().also { line = it } != null) {
            sb.append(line)
        }
        `is`.close()
        br.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return sb.toString()
}