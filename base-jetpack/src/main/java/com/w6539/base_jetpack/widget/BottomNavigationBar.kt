package com.w6539.base_jetpack.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @author Yang
 * @since 2022/12/7 16:05
 * @desc 底部导航栏
 */
class BottomNavigationBar : LinearLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val tabs = mutableListOf<IBottomNavigationTab>()

    init {
        orientation = HORIZONTAL
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }
}