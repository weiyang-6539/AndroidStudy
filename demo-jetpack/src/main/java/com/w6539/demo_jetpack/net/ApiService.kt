package com.w6539.demo_jetpack.net

import com.w6539.demo_jetpack.bean.Daily
import com.w6539.demo_jetpack.bean.HomePageRecommend
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * @author Yang
 * @since 2022/12/13 15:54
 * @desc api接口封装
 */
interface ApiService {
    /**
     * 首页-推荐列表
     */
    @GET
    suspend fun queryHomeRecommend(@Url url: String): HomePageRecommend

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun queryHomeDaily(@Url url: String): Daily

    @GET("api/v2/video/{id}")
    suspend fun queryVideo(@Path("id") vId: Int): Any
}