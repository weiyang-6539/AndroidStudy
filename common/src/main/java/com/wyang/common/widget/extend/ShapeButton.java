package com.wyang.common.widget.extend;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.wyang.common.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by weiyang on 2019-10-11.
 * 参考google材质设计 https://material.io/components/buttons/
 */
public class ShapeButton extends AppCompatTextView {
    public static final int NONE = 0;
    public static final int STANDARD = 1;
    public static final int RIPPLE = 2;

    @IntDef({NONE, STANDARD, RIPPLE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Selector {
    }

    @Selector
    private int mSelector = NONE;

    private int solidColor = 0x00000000;
    private int selectorDefaultColor = 0xFFFFFFFF;//默认颜色
    private int selectorPressedColor = 0xFFE5E5E5;//按下颜色
    private int selectorDisableColor = 0xFFAAAAAA;//禁用颜色

    private float cornersRadius;//圆角半径
    private float cornersTopLeftRadius;
    private float cornersTopRightRadius;
    private float cornersBottomLeftRadius;
    private float cornersBottomRightRadius;

    private int strokeWidth;
    private int strokeColor = 0xff000000;

    private int mGravity;
    private float mElevation;//使用阴影效果时，需要结合margin使用

    private Drawable leftIcon;
    private int leftIconSize = 18;
    private Drawable topIcon;
    private int topIconSize = 18;
    private Drawable rightIcon;
    private int rightIconSize = 18;
    private Drawable bottomIcon;
    private int bottomIconSize = 18;
    private int iconPadding = 8;//单位dp

    public ShapeButton(Context context) {
        this(context, null);
    }

    public ShapeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        init();
    }

    /**
     * 初始化xml中设置属性
     */
    private void parseAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeButton);

            mSelector = typedArray.getInt(R.styleable.ShapeButton_sSelector, NONE);

            solidColor = typedArray.getColor(R.styleable.ShapeButton_sSolidColor, solidColor);
            selectorDefaultColor = typedArray.getColor(R.styleable.ShapeButton_sSelectorDefaultColor, selectorDefaultColor);
            selectorPressedColor = typedArray.getColor(R.styleable.ShapeButton_sSelectorPressedColor, selectorPressedColor);
            selectorDisableColor = typedArray.getColor(R.styleable.ShapeButton_sSelectorDisableColor, selectorDisableColor);

            cornersRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sCornersRadius, 0);
            cornersTopLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sCornersTopLeftRadius, 0);
            cornersTopRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sCornersTopRightRadius, 0);
            cornersBottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sCornersBottomLeftRadius, 0);
            cornersBottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sCornersBottomRightRadius, 0);

            strokeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sStrokeWidth, 0);

            strokeColor = typedArray.getColor(R.styleable.ShapeButton_sStrokeColor, strokeColor);

            mGravity = typedArray.getInt(R.styleable.ShapeButton_sGravity, mGravity);
            mElevation = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sElevation, 0);

            leftIcon = typedArray.getDrawable(R.styleable.ShapeButton_sLeftIcon);
            topIcon = typedArray.getDrawable(R.styleable.ShapeButton_sTopIcon);
            rightIcon = typedArray.getDrawable(R.styleable.ShapeButton_sRightIcon);
            bottomIcon = typedArray.getDrawable(R.styleable.ShapeButton_sBottomIcon);
            leftIconSize = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sLeftIconSize, dp2px(leftIconSize));
            topIconSize = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sTopIconSize, dp2px(topIconSize));
            rightIconSize = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sRightIconSize, dp2px(rightIconSize));
            bottomIconSize = typedArray.getDimensionPixelSize(R.styleable.ShapeButton_sBottomIconSize, dp2px(bottomIconSize));
            iconPadding = typedArray.getDimensionPixelOffset(R.styleable.ShapeButton_sIconPadding, dp2px(iconPadding));

            typedArray.recycle();
        }
    }

    private void init() {
        setClickable(true);
        switch (mSelector) {
            case NONE:
                ViewCompat.setBackground(this, getDrawable(0));
                break;
            case STANDARD:
                ViewCompat.setBackground(this, getStandard());
                break;
            case RIPPLE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ViewCompat.setBackground(this, getRipple());
                } else {
                    ViewCompat.setBackground(this, getStandard());
                }
                break;
        }

        //设置文本位置
        switch (mGravity) {
            case 0:
                setGravity(Gravity.CENTER);
                break;
            case 1:
                setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                break;
            case 2:
                setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                break;
            case 3:
                setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                break;
            case 4:
                setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                break;
        }
        //设置阴影
        if (mElevation > 0)
            ViewCompat.setElevation(this, mElevation);

        drawDrawable();
    }

    private GradientDrawable getDrawable(int state) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);

        if (cornersRadius != 0)
            drawable.setCornerRadius(cornersRadius);
        else
            drawable.setCornerRadii(new float[]{
                    cornersTopLeftRadius, cornersTopLeftRadius,
                    cornersTopRightRadius, cornersTopRightRadius,
                    cornersBottomRightRadius, cornersBottomRightRadius,
                    cornersBottomLeftRadius, cornersBottomLeftRadius
            });
        if (state == android.R.attr.state_enabled) {
            drawable.setColor(selectorDefaultColor);
        } else if (state == -android.R.attr.state_enabled) {
            drawable.setColor(selectorDisableColor);
        } else if (state == android.R.attr.state_pressed) {
            drawable.setColor(selectorPressedColor);
        }
        return drawable;
    }

    private Drawable getStandard() {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, getDrawable(android.R.attr.state_pressed));
        drawable.addState(new int[]{-android.R.attr.state_enabled}, getDrawable(-android.R.attr.state_enabled));
        drawable.addState(new int[]{}, getDrawable(android.R.attr.state_enabled));
        return drawable;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Drawable getRipple() {

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        int normalColor = Color.parseColor("#aaaaaa");
        int[] stateColorList = new int[]{
                selectorPressedColor,
                selectorPressedColor,
                selectorPressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        float tlR = cornersRadius != 0 ? cornersRadius : cornersTopLeftRadius;
        float trR = cornersRadius != 0 ? cornersRadius : cornersTopRightRadius;
        float brR = cornersRadius != 0 ? cornersRadius : cornersBottomRightRadius;
        float blR = cornersRadius != 0 ? cornersRadius : cornersBottomLeftRadius;
        float[] outRadius = new float[]{
                tlR, tlR,
                trR, trR,
                brR, brR,
                blR, blR
        };

        RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);

        GradientDrawable maskDrawable = getDrawable(android.R.attr.state_pressed);
        GradientDrawable contentDrawable = getDrawable(android.R.attr.state_enabled);

        //contentDrawable实际是默认初始化时展示的；maskDrawable 控制了rippleDrawable的范围
        return new RippleDrawable(colorStateList, contentDrawable, maskDrawable);
    }

    private void drawDrawable() {
        if (leftIcon != null) {
            Bitmap bitmap = ((BitmapDrawable) leftIcon).getBitmap();
            leftIcon = new BitmapDrawable(getResources(), getBitmap(bitmap, leftIconSize));
        }
        if (topIcon != null) {
            Bitmap bitmap = ((BitmapDrawable) topIcon).getBitmap();
            topIcon = new BitmapDrawable(getResources(), getBitmap(bitmap, topIconSize));
        }
        if (rightIcon != null) {
            Bitmap bitmap = ((BitmapDrawable) rightIcon).getBitmap();
            rightIcon = new BitmapDrawable(getResources(), getBitmap(bitmap, rightIconSize));
        }
        if (bottomIcon != null) {
            Bitmap bitmap = ((BitmapDrawable) bottomIcon).getBitmap();
            bottomIcon = new BitmapDrawable(getResources(), getBitmap(bitmap, bottomIconSize));
        }
        setCompoundDrawablesWithIntrinsicBounds(leftIcon, topIcon, rightIcon, bottomIcon);
        setCompoundDrawablePadding(iconPadding);
    }

    public Bitmap getBitmap(Bitmap bitmap, int size) {
        //实际的大小
        int totalWidth = bitmap.getWidth();
        int totalHeight = bitmap.getHeight();
        //计算缩放比例
        float scaleWidth = (float) size / totalWidth;
        float scaleHeight = (float) size / totalHeight;
        Matrix matrix = new Matrix();
        //提交缩放
        matrix.postScale(scaleWidth, scaleHeight);

        //得到缩放后的图片
        return Bitmap.createBitmap(bitmap, 0, 0, totalWidth, totalHeight, matrix, true);
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
