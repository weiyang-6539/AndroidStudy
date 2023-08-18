package com.w6539android.base.net

import java.lang.Exception

/**
 * @author Yang
 * @since 2022/10/25 15:17
 * @desc
 */
sealed class HttpResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : HttpResult<T>()
    data class Failed(val exception: Exception) : HttpResult<Nothing>()
    fun checkSuccess(success: (T?) -> Unit): HttpResult<T> {
        if (this is Success)
            success(data)
        return this
    }

    fun checkFailed(failed: (Exception) -> Unit): HttpResult<T> {
        if (this is Failed)
            failed(exception)
        return this
    }

}