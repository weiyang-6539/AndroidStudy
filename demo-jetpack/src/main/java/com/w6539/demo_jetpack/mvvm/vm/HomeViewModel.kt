package com.w6539.demo_jetpack.mvvm.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.demo_jetpack.bean.HomePageRecommend
import com.w6539.demo_jetpack.mvvm.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Yang
 * @since 2022/12/14 16:50
 * @desc
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mRepository: HomeRepository
) : BaseViewModel() {

    private fun getPagingData(): Flow<PagingData<HomePageRecommend.Item>> {
        return mRepository.queryRecommendData().cachedIn(viewModelScope)
    }

    fun inject(adapter: suspend (pagingData: PagingData<HomePageRecommend.Item>) -> Unit) {
        launchOnUI {
            getPagingData().collect {
                adapter.invoke(it)
            }
        }
    }


}