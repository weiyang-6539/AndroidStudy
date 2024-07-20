package com.moonisland.texasholdempoker.mvvm.vm

import android.util.Log
import com.moonisland.texasholdempoker.mvvm.RemoteRepository
import com.w6539.base_jetpack.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author {USER}
 * @date {DATE}
 * @desc
 */
@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val mRepository: RemoteRepository
) : BaseViewModel() {

    fun test() {
        launchOnUI {
            val test = mRepository.test()

            Log.e("1111111111", test.toString())
        }
    }
}