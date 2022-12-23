package com.w6539.demo_jetpack.net

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Yang
 * @since 2022/12/23 13:31
 * @desc
 */
interface TestService {

    @POST("api/v3/appHome/getAppHomeModuleData")
    suspend fun queryData(@Body body: RequestBody): Any

}