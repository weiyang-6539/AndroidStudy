package com.w6539android.baselib.net

/**
 * @author Yang
 * @since 2022/10/26 10:17
 * @desc
 */
abstract class HttpResponse<T> {
    abstract fun code(): Int
    abstract fun message(): String
    abstract fun data(): T
}