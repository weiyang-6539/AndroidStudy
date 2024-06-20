package com.w6539.base_jetpack.base.vm

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.w6539.base_jetpack.net.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * @author Yang
 * @since 2022/12/5 9:17
 * @desc
 */
open class BaseViewModel : ViewModel() {
    private val mLoadStateLiveData = MutableLiveData<LoadState>()
    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO) { block() }
    }

    fun getLoadState(): MutableLiveData<LoadState> {
        return mLoadStateLiveData
    }

    fun loadLoading(@StringRes stringId: Int = 0) {
        mLoadStateLiveData.postValue(LoadState(LoadState.LOADING, stringId))
    }

    fun loadEmpty() {
        mLoadStateLiveData.postValue(LoadState(LoadState.EMPTY))
    }

    fun loadSuccess(@StringRes stringId: Int) {
        mLoadStateLiveData.postValue(LoadState(LoadState.SUCCESS, stringId))
    }

    fun loadError(exception: Exception) {
        mLoadStateLiveData.postValue(LoadState(LoadState.FAILED, exception = exception))
    }
}