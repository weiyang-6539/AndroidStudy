package com.w6539.demo_jetpack.mvvm

import com.w6539.base_jetpack.base.vm.BaseRepository
import com.w6539.base_jetpack.net.ApiResult
import com.w6539.demo_jetpack.bean.Daily
import com.w6539.demo_jetpack.net.ApiClient
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Yang
 * @since 2022/12/22 16:32
 * @desc
 */
@Singleton
class DailyRepository @Inject constructor() : BaseRepository() {

    suspend fun queryDaily():ApiResult<Daily>{
        return handle(ApiClient.apiService.getDaily(ApiClient.DAILY_URL))
    }
}