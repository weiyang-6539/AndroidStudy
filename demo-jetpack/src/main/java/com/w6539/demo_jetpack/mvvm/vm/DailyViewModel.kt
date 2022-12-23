package com.w6539.demo_jetpack.mvvm.vm

import androidx.hilt.lifecycle.ViewModelInject
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.demo_jetpack.mvvm.DailyRepository

/**
 * @author Yang
 * @since 2022/12/22 16:32
 * @desc
 */
class DailyViewModel @ViewModelInject constructor(private val mRepository: DailyRepository) :
    BaseViewModel() {

    fun queryDaily() {
        launchOnUI {
            mRepository.queryDaily()
        }
    }
}