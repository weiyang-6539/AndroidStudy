package com.w6539.demo_jetpack.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.w6539.demo_jetpack.bean.HomePageRecommend
import com.w6539.demo_jetpack.net.ApiClient
import java.lang.Exception

/**
 * @author Yang
 * @since 2022/12/13 15:29
 * @desc
 */
class RecommendPagingSource : PagingSource<String, HomePageRecommend.Item>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, HomePageRecommend.Item> {
        return try {
            val page = params.key ?: ApiClient.HOME_RECOMMEND

            val response = ApiClient.apiService.queryHomeRecommend(page)
            val itemList = response.itemList
            val prevKey = null
            val nextKey =
                if (itemList.isNotEmpty() && !response.nextPageUrl.isNullOrEmpty()) response.nextPageUrl else null
            LoadResult.Page(itemList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, HomePageRecommend.Item>): String? = null
}