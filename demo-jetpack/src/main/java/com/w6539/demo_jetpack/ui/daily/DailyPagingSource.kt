package com.w6539.demo_jetpack.ui.daily

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.w6539.demo_jetpack.bean.Daily
import com.w6539.demo_jetpack.net.ApiClient

/**
 * @author Yang
 * @since 2022/12/13 15:29
 * @desc
 */
class DailyPagingSource : PagingSource<String, Daily.Item>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Daily.Item> {
        return try {
            val page = params.key ?: ApiClient.DAILY_URL

            val response = ApiClient.apiService.queryHomeDaily(page)
            val itemList = response.itemList
            val prevKey = null
            val nextKey =
                if (itemList.isNotEmpty() && !response.nextPageUrl.isNullOrEmpty()) response.nextPageUrl else null
            LoadResult.Page(itemList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Daily.Item>): String? = null
}