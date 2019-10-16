package com.wyang.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.wyang.common.widget.bottomnavigation.BottomNavigationBar;
import com.wyang.common.widget.bottomnavigation.BottomNavigationItem;
import com.wyang.common.widget.bottomnavigation.ShapeBadgeItem;
import com.wyang.common.widget.bottomnavigation.TextBadgeItem;

/**
 * Created by weiyang on 2019-10-10.
 */
public class BottomNavigationActivity extends AppCompatActivity {
    private BottomNavigationBar bottomBar;
    private TextBadgeItem shopBadge;
    private ShapeBadgeItem discoverBadge;

    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mNavigationView = findViewById(R.id.mNavigationView);


        bottomBar = findViewById(R.id.bottomBar);

        shopBadge = new TextBadgeItem()
                .setBackgroundColor(0xffff0000)
                .setBorderColor(0xffffffff)
                .setBorderWidth(2)
                .setTextColor(0xffffffff)
                .setText("999");

        discoverBadge = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(0xffff0000)
                .setSizeInPixels(5, 5);

        bottomBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_selected, "首页")
                        .setInactiveIconResource(R.drawable.ic_home_normal)
                        .setActiveColorResource(R.color.colorRed))
                .addItem(new BottomNavigationItem(R.drawable.ic_search_selected, "搜索")
                        .setInactiveIconResource(R.drawable.ic_search_normal)
                        .setActiveColorResource(R.color.colorRed))
                .addItem(new BottomNavigationItem(R.drawable.ic_discover_selected, "发现")
                        .setInactiveIconResource(R.drawable.ic_discover_normal)
                        .setActiveColorResource(R.color.colorRed)
                        .setBadgeItem(discoverBadge))
                /*.addItem(new BottomNavigationItem(R.drawable.ic_shop_selected, "购物")
                        .setInactiveIconResource(R.drawable.ic_shop_normal)
                        .setActiveColorResource(R.color.colorRed)
                        .setBadgeItem(shopBadge))
                .addItem(new BottomNavigationItem(R.drawable.ic_unlogin_selected, "未登录")
                        .setInactiveIconResource(R.drawable.ic_unlogin_normal)
                        .setActiveColorResource(R.color.colorRed))*/
                .setFirstSelectedPosition(0)
                .initialise();

        bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
