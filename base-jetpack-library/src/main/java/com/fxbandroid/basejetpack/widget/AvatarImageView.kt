package com.fxbandroid.basejetpack.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import com.fxbandroid.basejetpack.R
import kotlin.math.abs

/**
 * @author Yang
 * @date 2024/11/23
 * @desc
 */
open class AvatarImageView : AppCompatImageView {
    companion object {
        private val COLORS = intArrayOf(
            -0xbb449a,
            -0xaa3323,
            -0x4488cd,
            -0x99ab,
            -0x44bc,
            -0xbb5501
        )

        private val COLORS_NUMBER = COLORS.size
        private const val DEFAULT_TEXT_COLOR = -0x1
        private const val DEFAULT_BOARDER_COLOR = -0x1
        private const val DEFAULT_BOARDER_WIDTH = 4
        private const val DEFAULT_TYPE_BITMAP = 0
        private const val DEFAULT_TYPE_TEXT = 1
        private const val DEFAULT_TEXT = ""
        private const val COLOR_DRAWABLE_DIMENSION = 1
        private const val DEFAULT_TEXT_SIZE_RATIO = 0.4f
        private const val DEFAULT_TEXT_MASK_RATIO = 0.8f
        private const val DEFAULT_BOARDER_SHOW = false
        private val BITMAP_CONFIG_8888 = Bitmap.Config.ARGB_8888
        private val BITMAP_CONFIG_4444 = Bitmap.Config.ARGB_4444
    }

    private var isCircle = true //是否支持圆形头像

    private var mRadius = 0//the circle's radius
    private var mCenterX = 0
    private var mCenterY = 0
    private var mType = DEFAULT_TYPE_BITMAP
    private var mBgColor = COLORS[0] //background color when show tv_title

    private var mTextColor = DEFAULT_TEXT_COLOR
    private var mBoarderColor = DEFAULT_BOARDER_COLOR
    private var mBoarderWidth = DEFAULT_BOARDER_WIDTH
    private var mTextSizeRatio = DEFAULT_TEXT_SIZE_RATIO //the tv_title size divides (2 * mRadius)

    private var mTextMaskRatio =
        DEFAULT_TEXT_MASK_RATIO //the inner-radius tv_title divides outer-radius tv_title

    private var mShowBoarder = DEFAULT_BOARDER_SHOW
    private var mText: String? = DEFAULT_TEXT

    private var mPaintTextForeground: Paint? = null//draw tv_title, in tv_title mode
    private var mPaintTextBackground: Paint? = null//draw circle, in tv_title mode
    private var mPaintDraw: Paint? = null//draw bitmap, int bitmap mode
    private var mPaintCircle: Paint? = null//draw boarder
    private var mFontMetrics: Paint.FontMetrics? = null

    private var mBitmap //the pic
            : Bitmap? = null
    private var mBitmapShader //used to adjust position of bitmap
            : BitmapShader? = null
    private var mMatrix //used to adjust position of bitmap
            : Matrix? = null

    fun setCircle(circle: Boolean) {
        isCircle = circle
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        initAttr(context, attrs)
        init()
    }

    private fun initAttr(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        val a = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView)
        val n = a.indexCount
        for (i in 0 until n) {
            when (val attr = a.getIndex(i)) {
                R.styleable.AvatarImageView_aiv_isCircle -> {
                    isCircle = a.getBoolean(attr, isCircle)
                }

                R.styleable.AvatarImageView_aiv_TextSizeRatio -> {
                    mTextSizeRatio = a.getFloat(attr, DEFAULT_TEXT_SIZE_RATIO)
                }

                R.styleable.AvatarImageView_aiv_TextMaskRatio -> {
                    mTextMaskRatio = a.getFloat(attr, DEFAULT_TEXT_MASK_RATIO)
                }

                R.styleable.AvatarImageView_aiv_BoarderWidth -> {
                    mBoarderWidth = a.getDimensionPixelSize(attr, DEFAULT_BOARDER_WIDTH)
                }

                R.styleable.AvatarImageView_aiv_BoarderColor -> {
                    mBoarderColor = a.getColor(attr, DEFAULT_BOARDER_COLOR)
                }

                R.styleable.AvatarImageView_aiv_TextColor -> {
                    mTextColor = a.getColor(attr, DEFAULT_TEXT_COLOR)
                }

                R.styleable.AvatarImageView_aiv_ShowBoarder -> {
                    mShowBoarder = a.getBoolean(attr, DEFAULT_BOARDER_SHOW)
                }

                R.styleable.AvatarImageView_aiv_CornerRadius -> {
                    mRadius =
                        a.getDimensionPixelSize(R.styleable.AvatarImageView_aiv_CornerRadius, 0)
                }
            }
        }
        a.recycle()
    }

    private fun init() {
        mMatrix = Matrix()
        mPaintTextForeground = Paint()
        mPaintTextForeground!!.color = mTextColor
        mPaintTextForeground!!.isAntiAlias = true
        mPaintTextForeground!!.textAlign = Paint.Align.CENTER
        mPaintTextBackground = Paint()
        mPaintTextBackground!!.isAntiAlias = true
        mPaintTextBackground!!.style = Paint.Style.FILL
        mPaintDraw = Paint()
        mPaintDraw!!.isAntiAlias = true
        mPaintDraw!!.style = Paint.Style.FILL
        mPaintCircle = Paint()
        mPaintCircle!!.isAntiAlias = true
        mPaintCircle!!.style = Paint.Style.STROKE
        mPaintCircle!!.color = mBoarderColor
        mPaintCircle!!.strokeWidth = mBoarderWidth.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val paddingLeft: Int = paddingLeft
        val paddingTop: Int = paddingTop
        val contentWidth: Int = w - paddingLeft - paddingRight
        val contentHeight: Int = h - paddingTop - paddingBottom
        if (isCircle) mRadius =
            if (contentWidth < contentHeight) contentWidth / 2 else contentHeight / 2
        mCenterX = paddingLeft + mRadius
        mCenterY = paddingTop + mRadius
        refreshTextSizeConfig()
    }

    private fun refreshTextSizeConfig() {
        mPaintTextForeground!!.textSize = mTextSizeRatio * 2 * mRadius
        mFontMetrics = mPaintTextForeground!!.fontMetrics
    }

    private fun refreshTextConfig() {
        if (mBgColor != mPaintTextBackground!!.color) {
            mPaintTextBackground!!.color = mBgColor
        }
        if (mTextColor != mPaintTextForeground!!.color) {
            mPaintTextForeground!!.color = mTextColor
        }
    }

    fun setTextAndColor(text: String?, bgColor: Int) {
        if (mType != DEFAULT_TYPE_TEXT || !TextUtils.equals(text, mText) || bgColor != mBgColor) {
            mText = text
            mBgColor = bgColor
            mType = DEFAULT_TYPE_TEXT
            invalidate()
        }
    }

    fun setTextColor(textColor: Int) {
        if (mTextColor != textColor) {
            mTextColor = textColor
            mPaintTextForeground!!.color = mTextColor
            invalidate()
        }
    }

    fun setTextAndColorSeed(text: String?, colorSeed: String) {
        setTextAndColor(text, getColorBySeed(colorSeed))
    }

    open fun setBitmap(bitmap: Bitmap?) {
        if (bitmap == null) {
            return
        }
        if (mType != DEFAULT_TYPE_BITMAP || bitmap != mBitmap) {
            mBitmap = bitmap
            mType = DEFAULT_TYPE_BITMAP
            invalidate()
        }
    }

    open fun setDrawable(drawable: Drawable?) {
        val bitmap = getBitmapFromDrawable(drawable)
        setBitmap(bitmap)
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else try {
            val bitmap: Bitmap = if (drawable is ColorDrawable) {
                Bitmap.createBitmap(
                    COLOR_DRAWABLE_DIMENSION,
                    COLOR_DRAWABLE_DIMENSION,
                    BITMAP_CONFIG_8888
                )
            } else {
                Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    BITMAP_CONFIG_8888
                )
            }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (mBitmap != null && mType == DEFAULT_TYPE_BITMAP) {
            toDrawBitmap(canvas)
        } else if (mText != null && mType == DEFAULT_TYPE_TEXT) {
            toDrawText(canvas)
        }
        if (mShowBoarder) {
            drawBoarder(canvas)
        }
    }

    private fun toDrawText(canvas: Canvas) {
        if (mText!!.length == 1) {
            //draw tv_title to the view's canvas directly
            drawText(canvas)
        } else {
            //draw tv_title with clip effect, need to create a bitmap
            drawBitmap(canvas, createClipTextBitmap((mRadius / mTextMaskRatio).toInt()), false)
        }
    }

    private fun toDrawBitmap(canvas: Canvas) {
        if (mBitmap == null) return
        if (!mBitmap!!.isRecycled)
            drawBitmap(canvas, mBitmap!!, true)
    }

    private fun drawBitmap(canvas: Canvas, bitmap: Bitmap, adjustScale: Boolean) {
        refreshBitmapShaderConfig(bitmap, adjustScale)
        mPaintDraw!!.shader = mBitmapShader
        if (isCircle) canvas.drawCircle(
            mCenterX.toFloat(), mCenterY.toFloat(), mRadius.toFloat(),
            mPaintDraw!!
        ) else {
            val rectF = RectF(
                paddingLeft.toFloat(),
                paddingTop.toFloat(),
                width.toFloat(),
                height.toFloat()
            )
            if (mRadius != 0) canvas.drawRoundRect(
                rectF, mRadius.toFloat(), mRadius.toFloat(),
                mPaintDraw!!
            ) else canvas.drawRect(rectF, mPaintDraw!!)
        }
    }

    private fun refreshBitmapShaderConfig(bitmap: Bitmap, adjustScale: Boolean) {
        mBitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mMatrix!!.reset()
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        if (adjustScale) {
            /*int bSize = Math.min(bitmapWidth, bitmapHeight);
            float scale = mRadius * 2.0f / bSize;*/
            val scaleX: Float = width * 1.0f / bitmapWidth
            val scaleY: Float = height * 1.0f / bitmapHeight
            mMatrix!!.setScale(scaleX, scaleY)
            if (bitmapWidth > bitmapHeight) {
                mMatrix!!.postTranslate(
                    -(bitmapWidth * scaleX / 2 - width / 2 - paddingLeft),
                    paddingTop.toFloat()
                )
            } else {
                mMatrix!!.postTranslate(
                    paddingLeft.toFloat(),
                    -(bitmapHeight * scaleY / 2 - height / 2 - paddingTop)
                )
            }
        } else {
            val x: Float = -(bitmapWidth * 1.0f / 2 - mRadius - paddingLeft)
            var y: Float = -(bitmapHeight * 1.0f / 2 - mRadius - paddingTop)
            y += if (height == width) 0 else width shr 1
            mMatrix!!.postTranslate(x, y)
        }
        mBitmapShader!!.setLocalMatrix(mMatrix)
    }

    private fun createClipTextBitmap(bitmapRadius: Int): Bitmap {
        val bitmapClipText =
            Bitmap.createBitmap(bitmapRadius * 2, bitmapRadius * 2, BITMAP_CONFIG_4444)
        val canvasClipText = Canvas(bitmapClipText)
        val paintClipText = Paint()
        paintClipText.style = Paint.Style.FILL
        paintClipText.isAntiAlias = true
        paintClipText.color = mBgColor
        if (isCircle) canvasClipText.drawCircle(
            bitmapRadius.toFloat(),
            bitmapRadius.toFloat(),
            bitmapRadius.toFloat(),
            paintClipText
        ) else canvasClipText.drawRect(
            0f,
            0f,
            (bitmapRadius * 2).toFloat(),
            (bitmapRadius * 2).toFloat(),
            paintClipText
        )
        paintClipText.textSize = mTextSizeRatio * mRadius * 2
        paintClipText.color = mTextColor
        paintClipText.textAlign = Paint.Align.CENTER
        val fontMetrics = paintClipText.fontMetrics
        canvasClipText.drawText(
            mText!!, 0, mText!!.length, bitmapRadius.toFloat(),
            bitmapRadius + abs(fontMetrics.top + fontMetrics.bottom) / 2, paintClipText
        )
        return bitmapClipText
    }

    private fun drawText(canvas: Canvas) {
        refreshTextConfig()
        if (isCircle) canvas.drawCircle(
            mCenterX.toFloat(), mCenterY.toFloat(), mRadius.toFloat(),
            mPaintTextBackground!!
        ) else {
            val rectF = RectF(
                paddingLeft.toFloat(),
                paddingTop.toFloat(),
                width.toFloat(),
                height.toFloat()
            )
            if (mRadius != 0) canvas.drawRoundRect(
                rectF, mRadius.toFloat(), mRadius.toFloat(),
                mPaintTextBackground!!
            ) else canvas.drawRect(rectF, mPaintTextBackground!!)
        }
        canvas.drawText(
            mText!!, 0, mText!!.length, mCenterX.toFloat(), mCenterY + abs(
                mFontMetrics!!.top + mFontMetrics!!.bottom
            ) / 2, mPaintTextForeground!!
        )
    }

    private fun drawBoarder(canvas: Canvas) {
        if (isCircle) canvas.drawCircle(
            mCenterX.toFloat(),
            mCenterY.toFloat(),
            (mRadius - (mBoarderWidth shr 1)).toFloat(),
            mPaintCircle!!
        )
    }

    private fun getColorBySeed(seed: String): Int {
        return if (TextUtils.isEmpty(seed)) {
            COLORS[0]
        } else COLORS[abs(seed.hashCode() % COLORS_NUMBER)]
    }

    override fun setImageDrawable(drawable: Drawable?) {
        setDrawable(drawable)
    }

    override fun setImageResource(resId: Int) {
        drawable = ResourcesCompat.getDrawable(resources, resId, null)
    }
}