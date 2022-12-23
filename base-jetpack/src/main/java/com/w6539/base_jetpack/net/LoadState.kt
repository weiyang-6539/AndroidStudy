package com.w6539.base_jetpack.net

import androidx.annotation.StringRes

/**
 * @author Yang
 * @since 2022/10/25 10:22
 * @desc api请求状态
 */
data class LoadState(
    val state: Int = NORMAL,
    @StringRes
    val strId: Int = 0,
) {
    companion object {
        const val NORMAL = 0//默认
        const val LOADING = 1//请求开始
        const val SUCCESS = 2//成功返回
        const val EMPTY = 3//无数据
        const val FAILED = 4//请求失败, http异常 api异常
        const val NO_NETWORK = 5//无网络, ui上可能需要显示无网络页面, 可能需要点击跳转至wifi界面
    }
}