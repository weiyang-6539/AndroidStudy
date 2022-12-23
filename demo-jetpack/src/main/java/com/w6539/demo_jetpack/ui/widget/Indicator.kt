package com.w6539.demo_jetpack.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @author Yang
 * @since 2022/12/8 16:59
 * @desc
 */
class Indicator : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        visibility = if (selected) VISIBLE else GONE
    }
}