package com.wyang.study.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @author Yang
 * @since 2022/10/27 14:41
 * @desc 带凸起底部导航栏
 */
class BottomBar(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(
    context,
    attrs,
    defStyleAttr
) {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    private var contentHeight: Float // 内容高度
    private var humpHeight: Float // 驼峰高度
    private var shaderHeight: Float // 阴影高度

    init {
        contentHeight = dip2px(50)
        humpHeight = dip2px(20)
        shaderHeight = dip2px(5)

        setBackgroundColor(Color.TRANSPARENT)

        setOnClickListener {
            current++
            if (current == 4)
                current = 1
            invalidate()
        }
    }


    private var current = 2

    /**
     * 背景画笔
     */
    private val bgPaint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint
    }

    private val linePaint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        //paint.color = Color.parseColor("#AAAAAAAA")
        paint.shader =
            LinearGradient(
                0f,
                0f,
                0f,
                dip2px(15),
                Color.TRANSPARENT,
                Color.GRAY,
                Shader.TileMode.CLAMP
            )
        paint
    }

    private val a = PointF(0f, 0f)
    private val b = PointF(0f, 0f)
    private val c = PointF(0f, 0f)
    private val a2 = PointF(0f, 0f)
    private val b2 = PointF(0f, 0f)
    private val c2 = PointF(0f, 0f)
    private val a3 = PointF(0f, 0f)
    private val b3 = PointF(0f, 0f)
    private val c3 = PointF(0f, 0f)

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(resetPath(), bgPaint)
        canvas.drawPath(linePath, linePaint)
        super.onDraw(canvas)
    }

    private val bgPath = Path()
    private val linePath = Path()
    private fun resetPath(): Path {
        bgPath.reset()
        val humpCenterX = width / 6f * (current * 2 - 1)

        a.x = humpCenterX - contentHeight / 2
        a.y = shaderHeight + humpHeight
        b.x = a.x + contentHeight / 12
        b.y = a.y
        c.x = a.x + contentHeight / 6
        c.y = humpHeight * 5 / 6 + shaderHeight

        a2.x = c.x
        a2.y = c.y
        b2.x = humpCenterX
        b2.y = shaderHeight
        c2.x = a.x + contentHeight * 5 / 6
        c2.y = humpHeight * 5 / 6 + shaderHeight

        a3.x = c2.x
        a3.y = c2.y
        b3.x = a.x + contentHeight * 11 / 12
        b3.y = shaderHeight + humpHeight
        c3.x = humpCenterX + contentHeight / 2
        c3.y = shaderHeight + humpHeight

        bgPaint.setShadowLayer(30f, 0f, 20f, Color.parseColor("#d4d5d9"))

        //moveTo 用来移动画笔
        bgPath.moveTo(0f, a.y) //设置下一个轮廓线的起始点(x,y)。第一个点
        bgPath.lineTo(a.x, a.y) //绘制到贝塞尔曲线第一个点 也就是a1点
        bgPath.quadTo(b.x, b.y, c.x, c.y) //第左边曲线
        bgPath.quadTo(b2.x, b2.y, c2.x, c2.y) //中间曲线
        bgPath.quadTo(b3.x, b3.y, c3.x, c3.y) //第右边曲线
        bgPath.lineTo(width * 1f, humpHeight + shaderHeight) //画线
        bgPath.lineTo(width * 1f, contentHeight + humpHeight + shaderHeight) //画线
        bgPath.lineTo(0f, contentHeight + humpHeight + shaderHeight)
        bgPath.close()

        linePath.moveTo(0f, a.y) //设置下一个轮廓线的起始点(x,y)。第一个点
        linePath.lineTo(a.x, a.y) //绘制到贝塞尔曲线第一个点 也就是a1点
        linePath.quadTo(b.x, b.y, c.x, c.y) //第左边曲线
        linePath.quadTo(b2.x, b2.y, c2.x, c2.y) //中间曲线
        linePath.quadTo(b3.x, b3.y, c3.x, c3.y) //第右边曲线
        linePath.lineTo(width * 1f, humpHeight + shaderHeight) //画线
        linePath.lineTo(width * 1f, humpHeight)
        linePath.lineTo(c3.x, c3.y - shaderHeight)
        linePath.quadTo(c3.x, c3.y - shaderHeight, b3.x, b3.y - shaderHeight)
        linePath.quadTo(b2.x, b2.y - shaderHeight, c.x, c.y - shaderHeight)
        linePath.quadTo(b.x, b.y - shaderHeight, a.x, a.y - shaderHeight)
        linePath.lineTo(0f, humpHeight)
        linePath.close()
        return bgPath
    }

    /**
     * 根据屏幕的分辨率从 dp 的单位 转成为 px(像素)
     */
    private fun dip2px(dpValue: Int): Float {
        val scale = resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}