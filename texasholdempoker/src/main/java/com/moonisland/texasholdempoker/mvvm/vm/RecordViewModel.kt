package com.moonisland.texasholdempoker.mvvm.vm

import com.moonisland.texasholdempoker.mvvm.DataRepository
import com.w6539.base_jetpack.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@HiltViewModel
class RecordViewModel @Inject constructor(
    private val mRepository: DataRepository
) : BaseViewModel() {

}