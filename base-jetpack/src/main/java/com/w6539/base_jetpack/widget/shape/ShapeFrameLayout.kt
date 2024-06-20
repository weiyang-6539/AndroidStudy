package com.w6539.base_jetpack.widget.shape

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * @author Yang
 * @since 2022/6/23 13:17
 * @desc 可定制背景的FrameLayout
 */
class ShapeFrameLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    var shapeHelper = ShapeHelper(this)

    init {
        shapeHelper.initAttrs(context, attrs).apply()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        shapeHelper.onSizeChanged(w, h)
    }

    override fun drawChild(canvas: Canvas, child: View?, drawingTime: Long): Boolean {
        canvas.clipPath(shapeHelper.mClipPath)
        return super.drawChild(canvas, child, drawingTime)
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(shapeHelper.rectF, null)
        super.dispatchDraw(canvas)
        shapeHelper.clipCanvas(canvas)
    }
}