package com.wyang.demo;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by weiyang on 2019-10-14.
 */
public class RippleActivity extends AppCompatActivity {
    private TextView tvRippleBg1;
    private TextView tvRippleBg2;
    private TextView tvRippleBg3;
    private TextView tvRippleBg4;
    private TextView tvRippleBg5;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        tvRippleBg1 = findViewById(R.id.tv_rippleBg1);
        tvRippleBg2 = findViewById(R.id.tv_rippleBg2);
        tvRippleBg3 = findViewById(R.id.tv_rippleBg3);
        tvRippleBg4 = findViewById(R.id.tv_rippleBg4);
        tvRippleBg5 = findViewById(R.id.tv_rippleBg5);

        initTv1RippleBG(R.color.colorRed);
        initTv2RippleBG();
        initTv3RippleBG();
        initTv4RippleBG();
        initTv5RippleBG();
    }

    /**
     * 作者：CnPeng
     * 时间：2018/8/8 下午3:37
     * 功用：xml中已经设置背景为 ripple_1.xml 为背景，此处是更改ripple_1中的颜色
     * 说明：
     */
    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initTv1RippleBG(final int colorResId) {
        final RippleDrawable rippleDrawable = (RippleDrawable) tvRippleBg1.getBackground();
        tvRippleBg1.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rippleDrawable.setHotspot(event.getX(), event.getY());
                //如果radius小于控件的宽高中的大值，则，触摸超出radius的部分时，也只会在控件中心位置为起点以radius为半径绘制ripple
                rippleDrawable.setRadius(200);
                rippleDrawable.setColor(ColorStateList.valueOf(getResources().getColor(colorResId)));
                return false;
            }
        });
    }

    /**
     * 作者：CnPeng
     * 时间：2018/8/8 下午12:02
     * 功用：以代码的方式构建rippleDrawable为背景——没有设置mask
     * 说明：http://www.tothenew.com/blog/ripple-effect-in-android/
     * https://www.programcreek.com/java-api-examples/index.php?api=android.graphics.drawable.RippleDrawable
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initTv2RippleBG() {

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        //深蓝
        int normalColor = Color.parseColor("#303F9F");
        //玫瑰红
        int pressedColor = Color.parseColor("#FF4081");
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, null, null);
        tvRippleBg2.setBackground(rippleDrawable);
    }

    /**
     * 作者：CnPeng
     * 时间：2018/8/8 下午12:02
     * 功用：以代码的方式构建rippleDrawable为背景——有drawable,但不设置mask
     * 说明：http://www.tothenew.com/blog/ripple-effect-in-android/
     * https://www.programcreek.com/java-api-examples/index.php?api=android.graphics.drawable.RippleDrawable
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initTv3RippleBG() {

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        //深蓝
        int normalColor = Color.parseColor("#303F9F");
        //玫瑰红
        int pressedColor = Color.parseColor("#FF4081");
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, drawable, null);
        tvRippleBg3.setBackground(rippleDrawable);
    }

    /**
     * 作者：CnPeng
     * 时间：2018/8/8 下午12:02
     * 功用：以代码的方式构建rippleDrawable为背景——无drawable,设置mask
     * 说明：http://www.tothenew.com/blog/ripple-effect-in-android/
     * https://www.programcreek.com/java-api-examples/index.php?api=android.graphics.drawable.RippleDrawable
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initTv4RippleBG() {

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        //深蓝
        int normalColor = Color.parseColor("#303F9F");
        //玫瑰红
        int pressedColor = Color.parseColor("#FF4081");
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, null, drawable);
        tvRippleBg4.setBackground(rippleDrawable);
    }

    /**
     * 作者：CnPeng
     * 时间：2018/8/8 下午12:02
     * 功用：以代码的方式构建rippleDrawable为背景——有drawable,设置mask
     * 说明：http://www.tothenew.com/blog/ripple-effect-in-android/
     * https://www.programcreek.com/java-api-examples/index.php?api=android.graphics.drawable.RippleDrawable
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initTv5RippleBG() {

        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        //深蓝
        int normalColor = Color.parseColor("#303F9F");
        //玫瑰红
        int pressedColor = Color.parseColor("#FF4081");
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        float[] outRadius = new float[]{10, 10, 15, 15, 20, 20, 25, 25};
        RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);
        ShapeDrawable maskDrawable = new ShapeDrawable();
        maskDrawable.setShape(roundRectShape);
        maskDrawable.getPaint().setColor(Color.parseColor("#000000"));
        maskDrawable.getPaint().setStyle(Paint.Style.FILL);

        ShapeDrawable contentDrawable = new ShapeDrawable();
        contentDrawable.setShape(roundRectShape);
        contentDrawable.getPaint().setColor(Color.parseColor("#f7c653"));
        contentDrawable.getPaint().setStyle(Paint.Style.FILL);

        //contentDrawable实际是默认初始化时展示的；maskDrawable 控制了rippleDrawable的范围
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, contentDrawable, maskDrawable);
        tvRippleBg5.setBackground(rippleDrawable);
    }
}
