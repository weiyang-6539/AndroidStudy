package com.w6539.base_jetpack.net

/**
 * @author Yang
 * @since 2022/12/2 15:36
 * @desc 返回体抽象层
 */
interface IResponse<T> {
    fun code(): Int
    fun message(): String
    fun data(): T
}