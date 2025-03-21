package com.fxbandroid.basejetpack.ext

/**
 * @author Yang
 * @date 2024/11/19
 * @desc
 */
fun String.isMobile86() = "^(1[3-9])\\d{9}$".toRegex()
    .matches(this)//手机校验

fun String.isMobile44() = "^(7)\\d{9}$".toRegex()
    .matches(this)//手机校验

fun String.checkPassword() = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$".toRegex()
    .matches(this)

fun String.isChinese() = this.matches(Regex("[\u4e00-\u9fa5]"))

