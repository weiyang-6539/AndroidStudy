package com.w6539.demo_jetpack.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.ui.utils.TypefaceUtil

/**
 * @author Yang
 * @since 2022/12/21 10:21
 * @desc 支持拓展字体TextView
 */
class TypefaceTextView : AppCompatTextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView, 0, 0)
            typeface = TypefaceUtil.getTypeface(a.getInt(R.styleable.TypefaceTextView_typeface, 0))
            a.recycle()
        }
    }
}