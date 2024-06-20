package com.w6539.base_jetpack.net

import androidx.annotation.StringRes

/**
 * @author Yang
 * @since 2022/12/5 9:24
 * @desc 网络 请求失败or请求错误 统一封装,
 */
class ApiException(
    val code: Int,
    val msg: String
) : Exception()