package com.w6539android.base.utils

import java.security.MessageDigest
import java.util.Locale

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
object MD5Util {
    /**
     * 字符串md5
     * @return
     */
    fun encode(str: String): String {
        return kotlin.runCatching {
            val md = MessageDigest.getInstance("MD5")
            var data = str.toByteArray(charset("UTF-16LE"))
            md.update(data)
            data = md.digest()
            val sb = StringBuilder()
            for (i in data.indices) {
                val d = Integer.toHexString(data[i].toInt() and 0xFF)
                if (i > 0) {
                    sb.append("-")
                }

                if (d.length == 1) {
                    sb.append("0")
                }
                sb.append(d)
            }
            sb.toString().uppercase(Locale.getDefault())
        }.getOrDefault("")
    }
}