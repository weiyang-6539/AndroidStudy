package com.w6539.base_jetpack.widget.shape

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.TextViewCompat
import com.w6539.base_jetpack.R

/**
 * @author Yang
 * @since 2022/6/24 9:11
 * @desc 拓展TextView,支持图标,背景shape
 */
class SuperTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatTextView(context, attrs, defStyleAttr) {
    val shapeHelper = ShapeHelper(this)

    private var textNormalColor = 0
    private var textPressedColor = 0
    private var textDisableColor = 0
    private var textSelectedColor = 0

    /**
     * 对应图标资源id
     */
    private val iconIds = arrayOf(0, 0, 0, 0)

    /**
     * 一维对应左上右下图标，二维对应状态颜色（状态依次为：pressed,disable,selected,none）
     */
    private val iconColors = Array(4) { arrayOf(0, 0, 0, 0) }

    /**
     * 上下左右图标的尺寸
     */
    private var startIconSize = 18
    private var topIconSize = 18
    private var endIconSize = 18
    private var bottomIconSize = 18

    private var shapeGravity: Int = 0
    private var iconPadding = 5

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        shapeHelper.initAttrs(context, attrs, object : ShapeHelper.ExposeListener {
            override fun expose(a: TypedArray) {
                textNormalColor =
                    a.getColor(R.styleable.ShapeView_shapeTextNormalColor, Color.BLACK)
                textPressedColor =
                    a.getColor(R.styleable.ShapeView_shapeTextPressedColor, Color.TRANSPARENT)
                textDisableColor =
                    a.getColor(R.styleable.ShapeView_shapeTextDisableColor, Color.TRANSPARENT)
                textSelectedColor =
                    a.getColor(R.styleable.ShapeView_shapeTextSelectedColor, Color.TRANSPARENT)
                iconIds[0] = a.getResourceId(R.styleable.ShapeView_shapeStartIcon, 0)
                iconIds[1] = a.getResourceId(R.styleable.ShapeView_shapeTopIcon, 0)
                iconIds[2] = a.getResourceId(R.styleable.ShapeView_shapeEndIcon, 0)
                iconIds[3] = a.getResourceId(R.styleable.ShapeView_shapeBottomIcon, 0)
                iconColors[0][3] = a.getColor(R.styleable.ShapeView_shapeStartIconNormalColor, 0)
                iconColors[0][0] = a.getColor(R.styleable.ShapeView_shapeStartIconPressedColor, 0)
                iconColors[0][1] = a.getColor(R.styleable.ShapeView_shapeStartIconDisableColor, 0)
                iconColors[0][2] = a.getColor(R.styleable.ShapeView_shapeStartIconSelectedColor, 0)
                iconColors[1][3] = a.getColor(R.styleable.ShapeView_shapeTopIconNormalColor, 0)
                iconColors[1][0] = a.getColor(R.styleable.ShapeView_shapeTopIconPressedColor, 0)
                iconColors[1][1] = a.getColor(R.styleable.ShapeView_shapeTopIconDisableColor, 0)
                iconColors[1][2] = a.getColor(R.styleable.ShapeView_shapeTopIconSelectedColor, 0)
                iconColors[2][3] = a.getColor(R.styleable.ShapeView_shapeEndIconNormalColor, 0)
                iconColors[2][0] = a.getColor(R.styleable.ShapeView_shapeEndIconPressedColor, 0)
                iconColors[2][1] = a.getColor(R.styleable.ShapeView_shapeEndIconDisableColor, 0)
                iconColors[2][2] = a.getColor(R.styleable.ShapeView_shapeEndIconSelectedColor, 0)
                iconColors[3][3] = a.getColor(R.styleable.ShapeView_shapeBottomIconNormalColor, 0)
                iconColors[3][0] = a.getColor(R.styleable.ShapeView_shapeBottomIconPressedColor, 0)
                iconColors[3][1] = a.getColor(R.styleable.ShapeView_shapeBottomIconDisableColor, 0)
                iconColors[3][2] = a.getColor(R.styleable.ShapeView_shapeBottomIconSelectedColor, 0)
                startIconSize = a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeStartIconSize,
                    dp2px(startIconSize.toFloat())
                )
                topIconSize = a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeTopIconSize,
                    dp2px(topIconSize.toFloat())
                )
                endIconSize = a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeEndIconSize,
                    dp2px(endIconSize.toFloat())
                )
                bottomIconSize = a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeBottomIconSize,
                    dp2px(bottomIconSize.toFloat())
                )
                iconPadding = a.getDimensionPixelSize(
                    R.styleable.ShapeView_shapeIconPadding,
                    dp2px(iconPadding.toFloat())
                )
                shapeGravity = a.getInt(R.styleable.ShapeView_shapeGravity, 0)
            }

        }).apply()

        includeFontPadding = false
        //设置文本颜色
        setTextColor(createTextColor())
        //设置文字图标间距
        compoundDrawablePadding = iconPadding
        when (shapeGravity) {
            0 -> gravity = Gravity.CENTER
            1 -> gravity = Gravity.START or Gravity.CENTER_VERTICAL
            2 -> gravity = Gravity.END or Gravity.CENTER_VERTICAL
            3 -> gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            4 -> gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        }

        /*viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                updateIcon()
            }
        })*/
        post { updateIcon() }
    }

    fun setTextNormalColor(color: Int) {
        this.textNormalColor = color
        setTextColor(createTextColor())
    }

    private fun createTextColor(): ColorStateList {
        val stateMap = LinkedHashMap<IntArray, Int>()
        if (isNotTransparent(textPressedColor))
            stateMap[intArrayOf(State.PRESSED)] = textPressedColor
        if (isNotTransparent(textDisableColor))
            stateMap[intArrayOf(State.DISABLE)] = textDisableColor
        if (isNotTransparent(textSelectedColor))
            stateMap[intArrayOf(State.SELECTED)] = textSelectedColor
        if (isNotTransparent(textNormalColor))
            stateMap[intArrayOf()] = textNormalColor
        val states = Array(stateMap.size) {
            IntArray(1)
        }
        val colors = IntArray(stateMap.size)
        var index = 0
        for ((key, value) in stateMap) {
            states[index] = key
            colors[index] = value
            index++
        }
        return ColorStateList(states, colors)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        updateIcon()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int,
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        updateIcon()
    }

    private fun updateIcon() {
        if (isIconNull() || layout == null) {
            return
        }
        val startIcon = getDrawable(0)
        val topIcon = getDrawable(1)
        val endIcon = getDrawable(2)
        val bottomIcon = getDrawable(3)
        val textPaint: Paint = paint
        var text: String = text.toString()
        if (transformationMethod != null) {
            text = transformationMethod.getTransformation(text, this).toString()
        }
        val textWidth =
            textPaint.measureText(text).toInt().coerceAtMost(layout.ellipsizedWidth)
        val rect = Rect()
        textPaint.getTextBounds(text, 0, text.length, rect)
        val offsetX: Int = ((measuredWidth
            - textWidth
            - ViewCompat.getPaddingStart(this)
            - ViewCompat.getPaddingEnd(this)
            - (if (startIcon == null) 0 else startIcon.bounds.width() + if (textWidth == 0) 0 else iconPadding)
            - if (endIcon == null) 0 else endIcon.bounds.width() + if (textWidth == 0) 0 else iconPadding)
            shr 1)
        val offsetY: Int = ((measuredHeight
            - layout.height
            - paddingTop
            - paddingBottom
            - (if (topIcon == null) 0 else topIcon.bounds.height() + if (textWidth == 0) 0 else iconPadding)
            - if (bottomIcon == null) 0 else bottomIcon.bounds.height() + if (textWidth == 0) 0 else iconPadding)
            shr 1)
        if (startIcon != null && shapeGravity != 2) {
            val bounds = startIcon.copyBounds()
            bounds.left += offsetX
            bounds.right += offsetX
            startIcon.bounds = bounds
        }
        if (topIcon != null && shapeGravity != 4) {
            val bounds = topIcon.copyBounds()
            bounds.top += offsetY
            bounds.bottom += offsetY
            topIcon.bounds = bounds
        }
        if (endIcon != null && shapeGravity != 3) {
            val bounds = endIcon.copyBounds()
            bounds.left -= offsetX
            bounds.right -= offsetX
            endIcon.bounds = bounds
        }
        if (bottomIcon != null && shapeGravity != 1) {
            val bounds = bottomIcon.copyBounds()
            bounds.top -= offsetY
            bounds.bottom -= offsetY
            bottomIcon.bounds = bounds
        }
        TextViewCompat.setCompoundDrawablesRelative(this, startIcon, topIcon, endIcon, bottomIcon)
    }

    private fun getStateIcon(bitmap: Bitmap, @ColorInt color: Int): BitmapDrawable {
        var drawable = BitmapDrawable(resources, bitmap)
        drawable = DrawableCompat.wrap(drawable).mutate() as BitmapDrawable
        DrawableCompat.setTint(drawable, color)
        return drawable
    }

    private fun getDrawable(index: Int): Drawable? {
        val iconId = iconIds[index]
        if (iconId == 0) return null
        val bitmap = BitmapFactory.decodeResource(resources, iconId)
        val drawable = StateListDrawable()
        if (isNotTransparent(iconColors[index][0])) drawable.addState(
            intArrayOf(State.PRESSED), getStateIcon(
                bitmap,
                iconColors[index][0]
            )
        )
        if (isNotTransparent(iconColors[index][1])) drawable.addState(
            intArrayOf(State.DISABLE), getStateIcon(
                bitmap,
                iconColors[index][1]
            )
        )
        if (isNotTransparent(iconColors[index][2])) drawable.addState(
            intArrayOf(State.SELECTED), getStateIcon(
                bitmap,
                iconColors[index][2]
            )
        )
        if (isNotTransparent(iconColors[index][3])) drawable.addState(
            intArrayOf(), getStateIcon(
                bitmap,
                iconColors[index][3]
            )
        ) else drawable.addState(intArrayOf(), BitmapDrawable(resources, bitmap))

        //计算图片的宽高比,解决非方形icon的留白问题
        val ratio = bitmap.width * 1f / bitmap.height
        val left: Int
        val top: Int
        val right: Int
        val bottom: Int
        when (index) {
            0 -> {
                left = 0
                right = (startIconSize * ratio.coerceAtMost(1f)).toInt()
                top = (startIconSize * (1 - 1f / ratio.coerceAtLeast(1f)) / 2).toInt()
                bottom = startIconSize - top
                drawable.setBounds(left, top, right, bottom)
            }

            1 -> {
                left = (topIconSize * (1 - ratio.coerceAtMost(1f)) / 2).toInt()
                top = 0
                right = topIconSize - left
                bottom = (topIconSize / ratio.coerceAtLeast(1f)).toInt()
                drawable.setBounds(left, top, right, bottom)
            }

            2 -> {
                left = 0
                top = (endIconSize * (1 - 1f / ratio.coerceAtLeast(1f)) / 2).toInt()
                right = (endIconSize * ratio.coerceAtMost(1f)).toInt()
                bottom = endIconSize - top
                drawable.setBounds(left, top, right, bottom)
            }

            3 -> {
                left = (bottomIconSize * (1 - ratio.coerceAtMost(1f)) / 2).toInt()
                top = 0
                right = bottomIconSize - left
                bottom = (bottomIconSize / ratio.coerceAtLeast(1f)).toInt()
                drawable.setBounds(left, top, right, bottom)
            }
        }
        return drawable
    }

    fun setStartIcon(@DrawableRes resId: Int) {
        iconIds[0] = resId
        updateIcon()
    }

    fun setTopIcon(@DrawableRes resId: Int) {
        iconIds[1] = resId
        updateIcon()
    }

    fun setEndIcon(@DrawableRes resId: Int) {
        iconIds[2] = resId
        updateIcon()
    }

    fun setBottomIcon(@DrawableRes resId: Int) {
        iconIds[3] = resId
        updateIcon()
    }

    private fun isIconNull(): Boolean { //是否没有设置图标
        return iconIds.isNullOrEmpty() || iconIds[0] == 0 && iconIds[1] == 0 && iconIds[2] == 0 && iconIds[3] == 0
    }

    private fun isNotTransparent(color: Int): Boolean { //判断某个颜色是否为透明色
        return color != Color.TRANSPARENT
    }

    private fun dp2px(dpVal: Float) = (dpVal * resources.displayMetrics.density + .5f).toInt()
}