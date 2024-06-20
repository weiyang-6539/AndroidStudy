package com.w6539.base_jetpack.base.vm

import com.w6539.base_jetpack.net.ApiException
import com.w6539.base_jetpack.net.ApiResult
import com.w6539.base_jetpack.net.IResponse
import kotlinx.coroutines.coroutineScope

/**
 * @author Yang
 * @since 2022/12/2 15:52
 * @desc 网络请求返回处理基类Repository
 */
open class BaseRepository(private val successCode: Int = 0) {
    suspend fun <T : Any> handleApiCall(call: suspend () -> ApiResult<T>): ApiResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            ApiResult.Failed(e)
        }
    }

    suspend fun <T : Any> handleApiResponse(response: IResponse<T>): ApiResult<T> {
        return coroutineScope {
            if (response.code() == successCode) {
                ApiResult.Success(response.data())
            } else {
                ApiResult.Failed(ApiException(response.code(), response.message()))
            }
        }
    }
}