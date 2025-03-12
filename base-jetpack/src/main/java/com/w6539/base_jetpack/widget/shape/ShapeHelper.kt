package com.w6539.base_jetpack.widget.shape

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
import com.w6539.base_jetpack.R
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
    private var selectorCheckedColor = 0

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
            selectorCheckedColor =
                a.getColor(R.styleable.ShapeView_shapeSelectorCheckedColor, Color.TRANSPARENT)
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
                ).toFloat()
            )
            setCornersTopRightRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersTopRightRadius,
                    0
                ).toFloat()
            )
            setCornersBotLeftRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersBotLeftRadius,
                    0
                ).toFloat()
            )
            setCornersBotRightRadius(
                a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeCornersBotRightRadius,
                    0
                ).toFloat()
            )
            setCornersRadius(
                a.getDimensionPixelSize(R.styleable.ShapeView_shapeCornersRadius, 0).toFloat()
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

    fun setSelectorNormalColor(color: Int): ShapeHelper {
        this.selectorNormalColor = color
        return this
    }

    fun setSelectorPressedColor(color: Int): ShapeHelper {
        this.selectorPressedColor = color
        return this
    }

    fun setSelectorDisableColor(color: Int): ShapeHelper {
        this.selectorDisableColor = color
        return this
    }

    fun selectorSelectedColor(color: Int): ShapeHelper {
        this.selectorPressedColor = color
        return this
    }

    fun setSelectorFocusedColor(color: Int): ShapeHelper {
        this.selectorFocusedColor = color
        return this
    }

    fun setSelectorCheckedColor(color: Int): ShapeHelper {
        this.selectorCheckedColor = color
        return this
    }

    fun setGradientStartColor(color: Int): ShapeHelper {
        this.gradientStartColor = color
        return this
    }

    fun setGradientCenterColor(color: Int): ShapeHelper {
        this.gradientCenterColor = color
        return this
    }

    fun setGradientEndColor(color: Int): ShapeHelper {
        this.gradientEndColor = color
        return this
    }

    fun setGradientAngle(@IntRange(from = 0, to = 7) angle: Int): ShapeHelper {
        this.gradientAngle = angle
        return this
    }

    fun setGradientUseLevel(userLevel: Boolean): ShapeHelper {
        this.gradientUseLevel = userLevel
        return this
    }

    private fun setCornersTopLeftRadius(cornersTopLeftRadius: Float): ShapeHelper {
        radii[1] = cornersTopLeftRadius
        radii[0] = radii[1]
        return this
    }

    private fun setCornersTopRightRadius(cornersTopRightRadius: Float): ShapeHelper {
        radii[3] = cornersTopRightRadius
        radii[2] = radii[3]
        return this
    }

    private fun setCornersBotRightRadius(cornersBotRightRadius: Float): ShapeHelper {
        radii[5] = cornersBotRightRadius
        radii[4] = radii[5]
        return this
    }

    private fun setCornersBotLeftRadius(cornersBotLeftRadius: Float): ShapeHelper {
        radii[7] = cornersBotLeftRadius
        radii[6] = radii[7]
        return this
    }

    fun setCornersRadius(cornersRadius: Float): ShapeHelper {
        if (cornersRadius != 0f)
            Arrays.fill(radii, cornersRadius)
        return this
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
            State.CHECKED -> gradientDrawable.setColor(selectorCheckedColor)
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

            State.CHECKED -> gradientDrawable.setStroke(
                strokeWidth,
                strokeCheckedColor,
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
        //选中填充色2
        if (!isTransparent(selectorCheckedColor) || !isTransparent(strokeCheckedColor)) background.addState(
            intArrayOf(State.CHECKED, State.ENABLED),
            getDrawable(State.CHECKED)
        )
        //默认填充色，最后添加
        background.addState(
            intArrayOf(-State.SELECTED, -State.FOCUSED, -State.CHECKED),
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
        view.isClickable = clickable
        ViewCompat.setBackground(view, getBackground())
    }

    private fun isTransparent(color: Int): Boolean {
        return color == Color.TRANSPARENT
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        // 剪裁
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        style = Paint.Style.FILL
    }
    val mClipPath = Path()
    val rectF = RectF()

    fun onSizeChanged(w: Int, h: Int) {
        rectF[0f, 0f, w.toFloat()] = h.toFloat()
        mClipPath.reset()
        mClipPath.addRoundRect(rectF, radii, Path.Direction.CW)
    }

    /**
     * 切割确保子View不超出圆角范围
     */
    fun clipCanvas(canvas: Canvas) {
        canvas.drawPath(mClipPath, mPaint)
        canvas.restore()
    }
}