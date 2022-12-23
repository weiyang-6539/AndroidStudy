package com.w6539.demo_jetpack.net

import com.w6539.base_jetpack.net.RetrofitClient
import okhttp3.Interceptor
import java.util.*

/**
 * @author Yang
 * @since 2022/12/14 15:41
 * @desc
 */
object ApiClient : RetrofitClient() {

    @JvmStatic
    val apiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getService(ApiService::class.java, BASE_URL)
    }

    private const val BASE_URL = "http://baobab.kaiyanapp.com/"

    const val HOME_RECOMMEND = "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec"


    /**
     * 首页-日报列表
     */
    const val DAILY_URL = "http://baobab.kaiyanapp.com/api/v5/index/tab/feed"

    @JvmStatic
    val testService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getService(TestService::class.java, "http://39.108.53.223:61556/")
    }
}