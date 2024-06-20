package com.w6539.base_jetpack.utils

import java.security.MessageDigest

/**
 * @author WeiYang
 * @date 2024/6/19
 * @desc
 */
object MessageDigestUtils {
    /**

     * 转为16进制

     */

    fun toHex(byteArray: ByteArray): String {
        with(StringBuilder()) {
            //转为字符串
            byteArray.forEach {
                val value = it
                val hex = value.toInt() and (0xFF)
                val toHexString = Integer.toHexString(hex)
                if (toHexString.length == 1) {
                    this.append("0$toHexString")
                } else {
                    this.append(toHexString)
                }
            }
            return this.toString()
        }
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return toHex(digest)
    }

    fun sha1(intput: String): String {
        val instance = MessageDigest.getInstance("SHA-1")
        val digest = instance.digest(intput.toByteArray())
        return toHex(digest)
    }

    fun sha256(intput: String): String {
        val instance = MessageDigest.getInstance("SHA-256")
        val digest = instance.digest(intput.toByteArray())
        return toHex(digest)
    }
}