package com.wyang.study.adapter

import android.content.Context
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.Discover
import com.wyang.study.ui.util.GlideImageLoader
import com.wyang.study.ui.widget.NineGridLayout
import com.wyang.study.ui.widget.NineGridLayout.ImageLoaderInterface

class DiscoverAdapter: BaseQuickAdapter<Discover, BaseViewHolder>(R.layout.item_wechat_discovery) {
    override fun convert(helper: BaseViewHolder, item: Discover) {
        helper.setText(R.id.tv_nickname, "阿明")
        helper.setText(R.id.tv_content, "内容文本")
        val mNineGridLayout = helper.getView<NineGridLayout>(R.id.mNineGridLayout)
        mNineGridLayout.setImageUrls(item.urls, imageLoader)
    }

    private val imageLoader: ImageLoaderInterface = object : ImageLoaderInterface {
        override fun createImageView(
            context: Context,
            count: Int,
            pos: Int
        ): ImageView {
            return ImageView(context)
        }

        override fun displayImage(context: Context, imageView: ImageView, url: String) {
            GlideImageLoader.load(context, imageView, url, 0, 0)
        }
    }
}