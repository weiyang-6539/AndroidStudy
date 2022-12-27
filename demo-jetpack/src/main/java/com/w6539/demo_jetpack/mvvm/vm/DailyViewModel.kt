package com.w6539.demo_jetpack.mvvm.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.demo_jetpack.bean.Daily
import com.w6539.demo_jetpack.mvvm.HomeRepository

/**
 * @author Yang
 * @since 2022/12/22 16:32
 * @desc
 */
class DailyViewModel @ViewModelInject constructor(private val mRepository: HomeRepository) :
    BaseViewModel() {

    fun inject(adapter: suspend (pagingData: PagingData<Daily.Item>) -> Unit) {
        launchOnUI {
            mRepository.queryDailyData().cachedIn(viewModelScope).collect {
                adapter.invoke(it)
            }
        }
    }
}