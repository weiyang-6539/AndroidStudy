package com.w6539.base_jetpack.net

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.w6539.base_jetpack.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Yang
 * @since 2022/6/29 13:39
 * @desc Retrofit封装
 */
open class RetrofitClient(
    private val connectTimeout: Long = 30,
    private val writeTimeout: Long = 30,
    private val readTimeout: Long = 30,
) {
    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
            val headerInterceptor = createInterceptor()
            if (headerInterceptor != null) {
                builder.addInterceptor(headerInterceptor)
            }

            builder.addNetworkInterceptor(createLoggingInterceptor())
            return builder.build()
        }


    protected open fun createInterceptor(): Interceptor? = null
    protected open fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { message ->
            run {
                if (BuildConfig.DEBUG)
                    Log.e(RetrofitClient::class.java.simpleName, message)
            }
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //动态代理构建Retrofit的服务
    fun <S> getService(service: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        object : TypeToken<Map<String, Any>>() {}.type, MapTypeAdapter()
                    ).create()
                )
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(service)
    }
}