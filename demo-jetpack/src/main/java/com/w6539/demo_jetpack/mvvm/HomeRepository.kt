package com.w6539.demo_jetpack.mvvm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.w6539.base_jetpack.base.vm.BaseRepository
import com.w6539.base_jetpack.net.ApiResult
import com.w6539.demo_jetpack.bean.HomePageRecommend
import com.w6539.demo_jetpack.net.ApiClient
import com.w6539.demo_jetpack.ui.home.RecommendPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Yang
 * @since 2022/12/14 10:27
 * @desc 首页网络仓库
 */
@Singleton
class HomeRepository @Inject constructor() : BaseRepository() {
    fun queryRecommendData(): Flow<PagingData<HomePageRecommend.Item>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { RecommendPagingSource() }
        ).flow
    }

    suspend fun query(): ApiResult<HomePageRecommend> {
        return handle(ApiClient.apiService.queryHomeRecommend(ApiClient.HOME_RECOMMEND))
    }
}