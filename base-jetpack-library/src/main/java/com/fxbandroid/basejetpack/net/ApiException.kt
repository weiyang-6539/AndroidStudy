package com.fxbandroid.basejetpack.net

/**
 * @author Yang
 * @date 2024/5/21
 * @desc
 */
open class ApiException(
    val code: Int,
    val msg: String
) : Exception(msg)