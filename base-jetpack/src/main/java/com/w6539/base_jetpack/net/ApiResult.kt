package com.w6539.base_jetpack.net

/**
 * @author Yang
 * @since 2022/12/2 15:55
 * @desc 接口请求返回密封类
 */
sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ApiResult<T>()
    data class Failed(val exception: Exception) : ApiResult<Nothing>()

    fun checkSuccess(success: (T?) -> Unit): ApiResult<T> {
        if (this is Success)
            success(data)
        return this
    }

    fun checkFailed(failed:(Exception) -> Unit): ApiResult<T> {
        if (this is Failed)
            failed(exception)
        return this
    }
}