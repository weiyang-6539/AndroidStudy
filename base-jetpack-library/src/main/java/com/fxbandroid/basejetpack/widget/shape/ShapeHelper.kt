package com.fxbandroid.basejetpack.widget.shape

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntRange
import androidx.core.view.ViewCompat
import com.fxbandroid.basejetpack.R
import java.util.Arrays

/**
 * @author Yang
 * @since 2022/6/23 13:19
 * @desc 背景辅助类
 */
class ShapeHelper(var view: View) {

    /**
     * shape背景状态色
     */
    private var selectorNormalColor = 0
    private var selectorPressedColor = 0
    private var selectorDisableColor = 0
    private var selectorSelectedColor = 0
    private var selectorFocusedColor = 0

    /**
     * 渐变色属性
     */
    private var gradientStartColor = 0
    private var gradientCenterColor = 0
    private var gradientEndColor = 0
    private var gradientAngle = 0
    private var gradientUseLevel = false

    /**
     * shape边框线，颜色与shape背景色数量一样
     */
    private var strokeWidth = 0
    private var strokeNormalColor = 0
    private var strokePressedColor = 0
    private var strokeDisableColor = 0
    private var strokeSelectedColor = 0
    private var strokeFocusedColor = 0
    private var strokeCheckedColor = 0
    private var strokeDashWidth = 0
    private var strokeDashGap = 0

    /**
     * shape圆角半径
     */
    private val radii = FloatArray(8)

    /**
     * 阴影
     */
    private var elevation = 0

    /**
     * 是否可以被点击
     */
    private var clickable = false

    /**
     * 按压效果是否启用波纹动画，需要selectorPressedColor!=Color.TRANSPARENT 和 5.0及以上系统才有效
     */
    private var ripple = false


    fun initAttrs(context: Context, attrs: AttributeSet?): ShapeHelper {
        return initAttrs(context, attrs, null)
    }

    fun initAttrs(context: Context, attrs: AttributeSet?, listener: ExposeListener?): ShapeHelper {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ShapeView)
            selectorNormalColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorNormalColor, Color.TRANSPARENT)
            selectorPressedColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorPressedColor, Color.TRANSPARENT)
            selectorDisableColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorDisableColor, Color.TRANSPARENT)
            selectorSelectedColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorSelectedColor, Color.TRANSPARENT)
            selectorFocusedColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorFocusedColor, Color.TRANSPARENT)
            gradientStartColor =
                a.getColor(R.styleable.ShapeView_shapeGradientStartColor, Color.TRANSPARENT)
            gradientCenterColor =
                a.getColor(R.styleable.ShapeView_shapeGradientCenterColor, Color.TRANSPARENT)
            gradientEndColor =
                a.getColor(R.styleable.ShapeView_shapeGradientEndColor, Color.TRANSPARENT)
            gradientAngle = a.getInt(R.styleable.ShapeView_shapeGradientAngle, 0)
            gradientUseLevel = a.getBoolean(R.styleable.ShapeView_shapeGradientUseLevel, false)
            strokeWidth = a.getDimensionPixelSize(R.styleable.ShapeView_shapeStrokeWidth, 0)
            strokeNormalColor =
                a.getColor(R.styleable.ShapeView_shapeStrokeNormalColor, Color.TRANSPARENT)
            strokePressedColor =
                a.getColor(R.styleable.ShapeView_shapeStrokePressedColor, Color.TRANSPARENT)
            strokeDisableColor =
                a.getColor(R.styleable.ShapeView_shapeStrokeDisableColor, Color.TRANSPARENT)
            strokeSelectedColor =
                a.getColor(R.styleable.ShapeView_shapeStrokeSelectedColor, Color.TRANSPARENT)
            strokeFocusedColor =
                a.getColor(R.styleable.ShapeView_shapeStrokeFocusedColor, Color.TRANSPARENT)
            strokeCheckedColor =
                a.getColor(R.styleable.ShapeView_shapeStrokeCheckedColor, Color.TRANSPARENT)
            strokeDashWidth = a.getDimensionPixelSize(R.styleable.ShapeView_shapeStrokeDashWidth, 0)
            strokeDashGap = a.getDimensionPixelSize(R.styleable.ShapeView_shapeStrokeDashGap, 0)
            //设置圆角
            setCornersTopLeftRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersTopLeftRadius,
                    0
                )
                    .toFloat()
            )
            setCornersTopRightRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersTopRightRadius,
                    0
                )
                    .toFloat()
            )
            setCornersBotLeftRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersBotLeftRadius,
                    0
                )
                    .toFloat()
            )
            setCornersBotRightRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersBotRightRadius,
                    0
                )
                    .toFloat()
            )
            setCornersRadius(
                a.getDimensionPixelSize(R.styleable.ShapeView_shapeCornersRadius, 0)
                    .toFloat()
            )
            elevation = a.getDimensionPixelSize(R.styleable.ShapeView_shapeElevation, 0)
            clickable = a.getBoolean(R.styleable.ShapeView_shapeClickable, clickable)
            ripple = a.getBoolean(R.styleable.ShapeView_shapeRipple, false)

            //拓展非Shape属性
            listener?.expose(a)
            a.recycle()
        }
        return this
    }

    interface ExposeListener {
        fun expose(a: TypedArray)
    }

    fun setSelectorNormalColor(color: Int) = apply {
        this.selectorNormalColor = color
    }

    fun setSelectorPressedColor(color: Int) = apply {
        this.selectorPressedColor = color
    }

    fun setSelectorDisableColor(color: Int) = apply {
        this.selectorDisableColor = color
    }

    fun setSelectorSelectedColor(color: Int) = apply {
        this.selectorSelectedColor = color
    }

    fun setSelectorFocusedColor(color: Int) = apply {
        this.selectorFocusedColor = color
    }

    fun setGradientStartColor(color: Int) = apply {
        this.gradientStartColor = color
    }

    fun setGradientCenterColor(color: Int) = apply {
        this.gradientCenterColor = color
    }

    fun setGradientEndColor(color: Int) = apply {
        this.gradientEndColor = color
    }

    fun setGradientAngle(@IntRange(from = 0, to = 7) angle: Int) = apply {
        this.gradientAngle = angle
    }

    fun setGradientUseLevel(userLevel: Boolean) = apply {
        this.gradientUseLevel = userLevel
    }

    private fun setCornersTopLeftRadius(cornersTopLeftRadius: Float) = apply {
        radii[1] = cornersTopLeftRadius
        radii[0] = radii[1]
    }

    private fun setCornersTopRightRadius(cornersTopRightRadius: Float) = apply {
        radii[3] = cornersTopRightRadius
        radii[2] = radii[3]
    }

    private fun setCornersBotRightRadius(cornersBotRightRadius: Float) = apply {
        radii[5] = cornersBotRightRadius
        radii[4] = radii[5]
    }

    private fun setCornersBotLeftRadius(cornersBotLeftRadius: Float) = apply {
        radii[7] = cornersBotLeftRadius
        radii[6] = radii[7]
    }

    fun setCornersRadius(cornersRadius: Float) = apply {
        if (cornersRadius != 0f)
            Arrays.fill(radii, cornersRadius)
    }

    fun setStrokeWidth(strokeWidth: Int) = apply {
        this.strokeWidth = strokeWidth
    }

    fun setStrokeNormalColor(strokeNormalColor: Int) = apply {
        this.strokeNormalColor = strokeNormalColor
    }

    fun setStrokePressedColor(strokePressedColor: Int) = apply {
        this.strokePressedColor = strokePressedColor
    }

    fun setStrokeDisableColor(strokeDisableColor: Int) = apply {
        this.strokeDisableColor = strokeDisableColor
    }

    fun setStrokeSelectedColor(strokeSelectedColor: Int) = apply {
        this.strokeSelectedColor = strokeSelectedColor
    }

    fun setStrokeFocusedColor(strokeFocusedColor: Int) = apply {
        this.strokeFocusedColor = strokeFocusedColor
    }

    fun setStrokeCheckedColor(strokeCheckedColor: Int) = apply {
        this.strokeCheckedColor = strokeCheckedColor
    }

    fun setStrokeDashWidth(strokeDashWidth: Int) = apply {
        this.strokeDashWidth = strokeDashWidth
    }

    fun setStrokeDashGap(strokeDashGap: Int) = apply {
        this.strokeDashGap = strokeDashGap
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun getDrawable(@State state: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        //设置填充颜色，优先填充solidColor和渐变色，若状态选择器有值，会覆盖这一操作
        if (!isTransparent(gradientStartColor) || !isTransparent(gradientEndColor)) {
            //渐变色和默认背景色不同时使用
            selectorNormalColor = Color.TRANSPARENT
            gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (isTransparent(gradientCenterColor)) {
                    gradientDrawable.colors = intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    gradientDrawable.colors =
                        intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                when (gradientAngle) {
                    0 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.LEFT_RIGHT

                    1 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.BL_TR

                    2 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.BOTTOM_TOP

                    3 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.BR_TL

                    4 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.RIGHT_LEFT

                    5 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.TR_BL

                    6 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.TOP_BOTTOM

                    7 -> gradientDrawable.orientation =
                        GradientDrawable.Orientation.TL_BR
                }
            } else if (!isTransparent(gradientStartColor)) {
                gradientDrawable.setColor(gradientStartColor)
            } else if (!isTransparent(gradientEndColor)) {
                gradientDrawable.setColor(gradientEndColor)
            }
            gradientDrawable.useLevel = gradientUseLevel
        }
        when (state) {
            State.NONE, State.ENABLED ->
                if (!isTransparent(selectorNormalColor))
                    gradientDrawable.setColor(selectorNormalColor)

            State.PRESSED -> gradientDrawable.setColor(selectorPressedColor)
            State.DISABLE -> gradientDrawable.setColor(selectorDisableColor)
            State.SELECTED -> gradientDrawable.setColor(selectorSelectedColor)
            State.FOCUSED -> gradientDrawable.setColor(selectorFocusedColor)
        }
        when (state) {
            State.NONE, State.ENABLED -> gradientDrawable.setStroke(
                strokeWidth,
                strokeNormalColor,
                strokeDashWidth.toFloat(),
                strokeDashGap.toFloat()
            )

            State.PRESSED -> gradientDrawable.setStroke(
                strokeWidth,
                strokePressedColor,
                strokeDashWidth.toFloat(),
                strokeDashGap.toFloat()
            )

            State.DISABLE -> gradientDrawable.setStroke(
                strokeWidth,
                strokeDisableColor,
                strokeDashWidth.toFloat(),
                strokeDashGap.toFloat()
            )

            State.SELECTED -> gradientDrawable.setStroke(
                strokeWidth,
                strokeSelectedColor,
                strokeDashWidth.toFloat(),
                strokeDashGap.toFloat()
            )

            State.FOCUSED -> gradientDrawable.setStroke(
                strokeWidth,
                strokeFocusedColor,
                strokeDashWidth.toFloat(),
                strokeDashGap.toFloat()
            )
        }

        //设置圆角
        gradientDrawable.cornerRadii = radii
        return gradientDrawable
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun getBackground(): Drawable {
        val background = StateListDrawable()
        //添加按压色
        if (!ripple && !isTransparent(selectorPressedColor) || !isTransparent(strokePressedColor)) background.addState(
            intArrayOf(State.PRESSED, State.ENABLED),
            getDrawable(State.PRESSED)
        )
        //禁用填充色
        if (!isTransparent(selectorDisableColor) || !isTransparent(strokeDisableColor)) background.addState(
            intArrayOf(State.DISABLE),
            getDrawable(State.DISABLE)
        )
        //选中填充色1
        if (!isTransparent(selectorSelectedColor) || !isTransparent(strokeSelectedColor)) background.addState(
            intArrayOf(State.SELECTED, State.ENABLED),
            getDrawable(State.SELECTED)
        )
        //焦点填充色
        if (!isTransparent(selectorFocusedColor) || !isTransparent(strokeFocusedColor)) background.addState(
            intArrayOf(State.FOCUSED, State.ENABLED),
            getDrawable(State.FOCUSED)
        )
        //默认填充色，最后添加
        background.addState(
            intArrayOf(-State.SELECTED, -State.FOCUSED),
            getDrawable(State.ENABLED)
        )
        return if (ripple && !isTransparent(selectorPressedColor) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RippleDrawable(
                ColorStateList.valueOf(selectorPressedColor),
                background,
                getDrawable(State.PRESSED)
            )
        } else background
    }

    fun apply() {
        //5.0版本及以上
        if (elevation > 0) ViewCompat.setElevation(view, elevation.toFloat())

        if (!view.isClickable)
            view.isClickable = clickable
        ViewCompat.setBackground(view, getBackground())
    }

    private fun isTransparent(color: Int): Boolean {
        return color == Color.TRANSPARENT
    }

    val mClipPath = Path()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val rectF = RectF()

    fun onSizeChanged(w: Int, h: Int) {
        rectF[0f, 0f, w.toFloat()] = h.toFloat()
        mClipPath.reset()
        mClipPath.addRoundRect(rectF, radii, Path.Direction.CW)

        // 剪裁
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        mPaint.style = Paint.Style.FILL
    }

    /**
     * 切割确保子View不超出圆角范围
     */
    fun clipCanvas(canvas: Canvas) {
        canvas.drawPath(mClipPath, mPaint)
        canvas.restore()
    }
}