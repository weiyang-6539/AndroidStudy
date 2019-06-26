package com.wyang.study.ui.fragment_second;

import android.content.Context;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.DataProvider;
import com.wyang.study.ui.util.GlideImageLoader;
import com.wyang.study.ui.widget.NineGridLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by weiyang on 2019/6/25.
 * 图片九宫格自定义
 */
public class NineGridLayoutFragment extends BaseFragment {

    @BindView(R.id.mSeekBar)
    SeekBar mSeekBar;
    @BindView(R.id.mNineGridLayout)
    NineGridLayout mNineGridLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ninegridlayout;
    }

    @Override
    protected void initView() {
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNineGridLayout.setSpacing(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private int count;

    @OnClick(R.id.tv_add)
    public void onClickAdd() {
        count++;

        mNineGridLayout.setImageUrls(DataProvider.getImageUrls(count), imageLoader);
    }

    @OnClick(R.id.tv_sub)
    public void onClickSub() {
        if (count == 0)
            return;

        count--;
        mNineGridLayout.setImageUrls(DataProvider.getImageUrls(count), imageLoader);
    }

    private NineGridLayout.ImageLoaderInterface imageLoader = new NineGridLayout.ImageLoaderInterface() {
        @Override
        public ImageView createImageView(Context context, int count, int pos) {
            ImageView child = new ImageView(context);
            child.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return child;
        }

        @Override
        public void displayImage(Context context, ImageView imageView, String url) {
            GlideImageLoader.load(context, imageView, url, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);
        }
    };
}
