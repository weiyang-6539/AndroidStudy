package com.w6539android.baselib.net

import java.lang.Exception

/**
 * @author Yang
 * @since 2022/10/26 10:29
 * @desc
 */
open class ApiException(
    val code: Int,
    private val msg: String
) : Exception(msg)