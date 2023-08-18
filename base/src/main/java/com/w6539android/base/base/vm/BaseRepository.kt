package com.w6539android.base.base.vm

import com.w6539android.base.net.ApiException
import com.w6539android.base.net.HttpResponse
import com.w6539android.base.net.HttpResult
import com.google.gson.JsonParseException
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author Yang
 * @since 2022/10/25 11:49
 * @desc 网络请求回调基类封装
 */
class BaseRepository(private val successCode: Int = 0) {
    suspend fun <T : Any> handleApiCall(call: suspend () -> HttpResult<T>): HttpResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            HttpResult.Failed(handleExceptionMessage(e))
        }
    }

    suspend fun <T : Any> handleApiResponse(response: HttpResponse<T>): HttpResult<T> {
        return coroutineScope {
            if (response.code() == successCode) {
                HttpResult.Success(response.data())
            } else {
                HttpResult.Failed(ApiException(response.code(), response.message()))
            }
        }
    }

    suspend fun <T : Any> handleApi(response: HttpResponse<T>): HttpResult<T> {
        return try {
            coroutineScope {
                if (response.code() == successCode) {
                    HttpResult.Success(response.data())
                } else {
                    HttpResult.Failed(ApiException(response.code(), response.message()))
                }
            }
        } catch (e: Exception) {
            HttpResult.Failed(handleExceptionMessage(e))
        }
    }

    //处理自定义错误消息
    private fun handleExceptionMessage(e: Exception): IOException {
        return when (e) {
            is UnknownHostException -> IOException("Unable to access domain name, unknown domain name.")
            is JsonParseException -> IOException("Data parsing exception.")
            is HttpException -> IOException("The server is on business. Please try again later.")
            is ConnectException -> IOException("Network connection exception, please check the network.")
            is SocketException -> IOException("Network connection exception, please check the network.")
            is SocketTimeoutException -> IOException("Network connection timeout.")
            is RuntimeException -> IOException("Error running, please try again.")
            else -> IOException(e.toString())
        }
    }
}