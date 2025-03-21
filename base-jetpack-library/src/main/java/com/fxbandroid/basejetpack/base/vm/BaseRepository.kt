package com.fxbandroid.basejetpack.base.vm

import com.fxbandroid.basejetpack.net.ApiException
import com.fxbandroid.basejetpack.net.IResponse
import kotlinx.coroutines.coroutineScope

/**
 * @author Yang
 * @since 2022/12/2 15:52
 * @desc 网络请求返回处理基类Repository
 */
open class BaseRepository(private val successCode: Int = 0) {

    suspend fun <T : Any> handleApiData(response: IResponse<T>): T {
        return coroutineScope {
            if (response.code() == successCode) {
                response.data()
            } else {
                throw ApiException(response.code(), response.message())
            }
        }
    }
}