package com.w6539.base_jetpack.base.vm

import com.w6539.base_jetpack.net.IResponse
import com.w6539.base_jetpack.net.ApiResult
import kotlinx.coroutines.coroutineScope

/**
 * @author Yang
 * @since 2022/12/2 15:52
 * @desc 网络请求返回处理基类Repository
 */
open class BaseRepository {
    suspend fun <T : Any> handle(response: IResponse<T>): ApiResult<T> {
        return try {
            coroutineScope { ApiResult.Success(response.data()) }
        } catch (e: Exception) {
            ApiResult.Failed(e)
        }
    }

    suspend fun <T : Any> handle(t: T): ApiResult<T> {
        return try {
            coroutineScope { ApiResult.Success(t) }
        } catch (e: Exception) {
            ApiResult.Failed(e)
        }
    }
}