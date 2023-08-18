package com.w6539android.base.net

/**
 * @author Yang
 * @since 2022/10/26 10:17
 * @desc
 */
interface HttpResponse<T> {
    fun code(): Int
    fun message(): String
    fun data(): T
}