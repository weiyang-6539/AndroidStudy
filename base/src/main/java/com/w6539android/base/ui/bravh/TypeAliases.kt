package com.w6539android.base.ui.bravh

import android.view.View

/**
 * @author Yang
 * @since 2023/8/23 8:27
 * @desc 类型的申明
 */
/*点击事件的函数, 返回值Boolean只有长按点击用到*/
typealias OnClickListener<T> = (T, View, Int) -> Unit
typealias OnLongClickListener<T> = (T, View, Int) -> Boolean