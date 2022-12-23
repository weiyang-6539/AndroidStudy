package com.w6539.demo_jetpack.mvvm.vm

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.google.gson.Gson
import com.w6539.base_jetpack.base.vm.BaseViewModel
import com.w6539.demo_jetpack.mvvm.DailyRepository
import com.w6539.demo_jetpack.net.ApiClient
import kotlinx.coroutines.coroutineScope
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

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

    fun queryData() {
        launchOnUI {
            mRepository.queryData(
                Gson().toJson(
                    mutableMapOf("keywords" to "湖南")
                ).toRequestBody("application/json;charset=UTF-8".toMediaType())
            )
        }
    }

    fun queryVideo() {
        launchOnUI {
            Log.e("aaaa","11111111")
            coroutineScope {
                Log.e("aaaa","222222")

                ApiClient.apiService.queryVideo(3036)

                Log.e("aaaa","333333")

            }
        }
    }
}