package com.w6539.demo_jetpack.net

import com.w6539.demo_jetpack.bean.Daily
import com.w6539.demo_jetpack.bean.HomePageRecommend
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * @author Yang
 * @since 2022/12/13 15:54
 * @desc api接口封装
 */
interface ApiService {
    @GET
    suspend fun queryHomeRecommend(@Url url: String): HomePageRecommend

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun getDaily(@Url url: String): Daily
}