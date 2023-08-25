package com.wyang.study.adapter

import android.content.Context
import android.widget.ImageView
import com.w6539android.base.ui.bravh.BaseListAdapter
import com.w6539android.base.ui.bravh.BaseViewHolder
import com.wyang.study.R
import com.wyang.study.bean.Discover
import com.wyang.study.ui.util.GlideImageLoader
import com.wyang.study.ui.widget.NineGridLayout
import com.wyang.study.ui.widget.NineGridLayout.ImageLoaderInterface

class DiscoverAdapter : BaseListAdapter<Discover>(
    R.layout.item_wechat_discovery
) {

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

    override fun convert(holder: BaseViewHolder, item: Discover) {
        holder.setText(R.id.tv_nickname, "阿明")
            .setText(R.id.tv_content, "内容文本")
            .getView<NineGridLayout>(R.id.mNineGridLayout)
            .setImageUrls(item.urls, imageLoader)
    }
}