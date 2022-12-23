package com.w6539.base_jetpack.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @author Yang
 * @since 2022/12/8 13:32
 * @desc 透明状态栏布局, 结合ImmersionBar库使用
 */
class TopLayout: ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}