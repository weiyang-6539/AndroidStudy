package com.fxbandroid.basejetpack.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @author Yang
 * @date 2024/11/19
 * @desc 倒计时
 */
fun AppCompatActivity.countDown(
    time: Int = 5,
    start: (scope: CoroutineScope) -> Unit,
    end: () -> Unit,
    err: (e: Throwable) -> Unit,
    next: (time: Int) -> Unit,
) {
    lifecycleScope.launch {
        // 在这个范围内启动的协程会在Lifecycle被销毁的时候自动取消
        //开启一个子协程，可以取消这个子线程，无需取消整个VewModelScope
        val that = this
        launch {
            flow {
                (time downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }.onStart {
                // 倒计时开始 ，在这里可以让Button 禁止点击状态
                start(that)
            }
                .onCompletion {
                    // 倒计时结束 ，在这里可以让Button 恢复点击状态
                    end()
                }
                .catch {
                    //错误
                    err(it)
                }
                .collect {
                    // 在这里 更新值来显示到UI
                    next(it)
                }
        }
    }
}

/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val imm = act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            it.clearFocus()
        }
    }
}

fun showSoftKeyboard(activity: Context?, et: EditText) {
    activity?.let { act ->
        et.requestFocus()
        val imm = act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
    }
}