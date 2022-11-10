package com.w6539android.baselib.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @author Yang
 * @since 2022/11/2 16:43
 * @desc
 */
class SuperLinearLayout : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setDividerDrawable(divider: Drawable?) {
        super.setDividerDrawable(divider)
    }
}