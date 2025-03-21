package com.fxbandroid.basejetpack.ext

import android.content.res.Resources
import android.graphics.Paint
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

/**
 * @author Yang
 * @date 2024/11/15
 * @desc
 */
fun dp2px(dpVal: Float) = (dpVal * Resources.getSystem().displayMetrics.density + .5f).toInt()
fun px2dp(pxVal: Int) = (pxVal / Resources.getSystem().displayMetrics.density + 0.5f).toInt()
fun sp2px(spVal: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP, spVal, Resources.getSystem().displayMetrics
)

fun View.applyGone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

fun View.applyVisible(isVisible: Boolean = true) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

// 添加删除线
fun TextView.applyStrikeThru(flag: Boolean) {
    paintFlags = if (flag)
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    else
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

// 添加下划线
fun TextView.applyUnderLine(flag: Boolean) {
    paintFlags = if (flag)
        paintFlags or Paint.UNDERLINE_TEXT_FLAG
    else
        paintFlags or Paint.UNDERLINE_TEXT_FLAG xor Paint.UNDERLINE_TEXT_FLAG
}

fun EditText.applyNumberType() {
    inputType = InputType.TYPE_CLASS_NUMBER
}

fun EditText.applyPasswordType() {
    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    transformationMethod = PasswordTransformationMethod.getInstance()
}

fun EditText.switchPasswordVisible() {
    transformationMethod = if (transformationMethod != null)
        null
    else
        PasswordTransformationMethod.getInstance()
}

fun View.click(
    interval: Long = 800,
    action: () -> Unit,
) {
    var viewLastClickTime = 0L
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (viewLastClickTime != 0L && (currentTime - viewLastClickTime < interval)) {
            return@setOnClickListener
        }
        viewLastClickTime = currentTime
        action()
    }
}

fun <T : View> Array<T>.click(call: () -> Unit) {
    this.forEach {
        it.click {
            call()
        }
    }
}

/**
 * 连击的拓展函数
 */
fun View.clicks(num: Int, action: () -> Unit) {
    var viewLastClickTime = 0L
    var clickCount = 0
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - viewLastClickTime < 1000) {
            clickCount++
        } else {
            clickCount = 0
        }
        viewLastClickTime = currentTime

        // 当clickCount=1时实际已经连续点击2次了
        if (clickCount + 1 >= num) {
            action()
            clickCount = 0
        }
    }
}

inline fun TabLayout.addOnTabSelectedListenerClosure(
    crossinline tabSelected: (tab: TabLayout.Tab) -> Unit = {},
    crossinline tabUnselected: (tab: TabLayout.Tab) -> Unit = {},
    crossinline tabReselected: (tab: TabLayout.Tab) -> Unit = {},
) {
    this.addOnTabSelectedListener(object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.apply { tabSelected.invoke(this) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            tab?.apply { tabUnselected.invoke(this) }
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            tab?.apply { tabReselected.invoke(this) }
        }
    })
}

fun CollapsingToolbarLayout.disableScroll(flag: Boolean) {
    layoutParams = (layoutParams as AppBarLayout.LayoutParams).apply {
        scrollFlags = if (flag)
            AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
        else
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    }
}