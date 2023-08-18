package com.w6539android.base.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.w6539android.base.net.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Yang
 * @since 2022/10/26 14:10
 * @desc MVVM模式 ViewModel基类封装
 */
open class BaseViewModel : ViewModel() {
    private val mLoadStateLiveData = MutableLiveData<LoadState>()

    fun launchOnMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) { block() }
    }

    fun getLoadState(): MutableLiveData<LoadState> {
        return mLoadStateLiveData
    }

    fun switchLoading() {
        getLoadState().postValue(LoadState(LoadState.LOADING))
    }

    fun switchSuccess() {
        getLoadState().postValue(LoadState(LoadState.SUCCESS))
    }

    fun switchFailed() {
        getLoadState().postValue(LoadState(LoadState.FAILED))
    }

    fun switchEmpty() {
        getLoadState().postValue(LoadState(LoadState.EMPTY))
    }

    fun switchNoNetWork() {
        getLoadState().postValue(LoadState(LoadState.NO_NETWORK))
    }
}