package com.wyang.demo;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private List<Item> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init RecyclerView
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new ItemDecoration(10));

        //init 适配器
        mAdapter = new Adapter();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Item item = mData.get(position);
            startActivity(new Intent(this, item.activity));
        });

        //bind RecyclerView和适配器
        mAdapter.bindToRecyclerView(mRecyclerView);

        //设置data
        mData = new ArrayList<>();
        mData.add(new Item("MD ShapeButton", "结合google md材质设计，自定义Button", ShapeButtonActivity.class));
        mData.add(new Item("底部导航", "结合google md材质设计，自定义Button", BottomNavigationActivity.class));
        mData.add(new Item("Ripple波纹动画", "xml及代码写法", RippleActivity.class));
        mAdapter.setNewData(mData);
    }

}

class Adapter extends BaseQuickAdapter<Item, BaseViewHolder> {

    Adapter() {
        super(R.layout.item_main_recycler);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_description, item.description);
    }
}

class Item {
    String title;
    String description;
    Class activity;

    public Item(String title, String description, Class activity) {
        this.title = title;
        this.description = description;
        this.activity = activity;
    }
}

class ItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 0;
    private int leftSpace = 0;
    private int rightSpace = 0;
    private int topSpace = 0;
    private int bottomSpace = 0;
    private boolean isTop = false;

    public ItemDecoration(int leftSpace, int rightSpace, int topSpace, int bottomSpace) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }

    public ItemDecoration(int leftSpace, int rightSpace, int topSpace, int bottomSpace, boolean isTop) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
        this.isTop = isTop;
    }

    public ItemDecoration(int space, boolean isTop) {
        this.space = space;
        this.isTop = isTop;
    }

    public ItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (space == 0) {
            outRect.left = leftSpace;
            outRect.right = rightSpace;
            outRect.bottom = topSpace;
            outRect.top = bottomSpace;
        } else {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }

        if (isTop) {
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = 0;
            }
            outRect.left = 0;
            outRect.right = 0;
        }
    }

}
