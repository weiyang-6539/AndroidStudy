package com.fxbandroid.basejetpack.base.vm

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fxbandroid.basejetpack.net.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Yang
 * @since 2022/12/5 9:17
 * @desc
 */
open class BaseViewModel : ViewModel() {
    private val mLoadStateLiveData = MutableLiveData<LoadState>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadError(throwable)
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler) { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO) { return@withContext block() }
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

    fun loadSuccess(@StringRes stringId: Int = 0) {
        mLoadStateLiveData.postValue(LoadState(LoadState.SUCCESS, stringId))
    }

    fun loadError(exception: Throwable) {
        mLoadStateLiveData.postValue(LoadState(LoadState.FAILED, throwable = exception))
    }
}