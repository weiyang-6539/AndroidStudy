package com.fxbandroid.basejetpack.net

import androidx.annotation.StringRes

/**
 * @author Yang
 * @since 2022/10/25 10:22
 * @desc api请求状态
 */
data class LoadState(
    val state: Int = NORMAL,
    @StringRes
    val strId: Int = 0,// 加载中, 请求成功的文本id
    val throwable: Throwable? = null,
) {
    companion object {
        const val NORMAL = 0//默认
        const val LOADING = 1//请求开始
        const val SUCCESS = 2//成功返回
        const val EMPTY = 3//无数据
        const val FAILED = 4//请求失败, http异常 api异常
        const val NO_NETWORK = -1//无网络, ui上可能需要显示无网络页面, 可能需要点击跳转至wifi界面
    }

    override fun toString(): String {
        return "state: $state throwable: ${throwable?.toString()}"
    }
}