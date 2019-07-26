package com.wyang.study.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wyang.study.R;
import com.wyang.study.bean.Discover;
import com.wyang.study.ui.util.GlideImageLoader;
import com.wyang.study.ui.widget.NineGridLayout;

/**
 * Created by weiyang on 2019/6/27.
 */
public class DiscoverAdapter extends BaseQuickAdapter<Discover, BaseViewHolder> {
    public DiscoverAdapter() {
        super(R.layout.item_wechat_discovery);
    }

    @Override
    protected void convert(BaseViewHolder helper, Discover item) {
        helper.setText(R.id.tv_nickname, "阿明");
        helper.setText(R.id.tv_content, "内容文本");
        NineGridLayout mNineGridLayout = helper.getView(R.id.mNineGridLayout);
        mNineGridLayout.setImageUrls(item.getUrls(), imageLoader);
    }

    private NineGridLayout.ImageLoaderInterface imageLoader = new NineGridLayout.ImageLoaderInterface() {
        @Override
        public ImageView createImageView(Context context, int count, int pos) {
            ImageView imageView = new ImageView(context);
            return imageView;
        }

        @Override
        public void displayImage(Context context, ImageView imageView, String url) {
            GlideImageLoader.load(context, imageView, url, 0, 0);
        }
    };
}
