package com.w6539.demo_jetpack.ui.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.w6539.demo_jetpack.bean.HomePageRecommend
import com.w6539.demo_jetpack.ext.conversionVideoDuration
import com.w6539.demo_jetpack.ext.gone
import com.w6539.demo_jetpack.ext.load
import com.w6539.demo_jetpack.ext.visible
import com.w6539.demo_jetpack.ui.holder.FollowCardViewHolder
import com.w6539.demo_jetpack.ui.holder.ItemViewType
import com.w6539.demo_jetpack.ui.holder.RecyclerViewHelper
import com.w6539.demo_jetpack.ui.holder.TextCardViewHeader5ViewHolder

/**
 * @author Yang
 * @since 2022/12/20 13:23
 * @desc
 */
class RecommendAdapter : PagingDataAdapter<HomePageRecommend.Item, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<HomePageRecommend.Item>() {
        override fun areItemsTheSame(
            oldItem: HomePageRecommend.Item,
            newItem: HomePageRecommend.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HomePageRecommend.Item,
            newItem: HomePageRecommend.Item
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun getItemViewType(position: Int): Int =
        ItemViewType.getItemViewType(getItem(position)!!.getTypeStr())

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)!!
        when (holder) {
            is TextCardViewHeader5ViewHolder -> {
                holder.tvTitle.text = item.data.text
            }
            is FollowCardViewHolder -> {
                holder.ivVideo.load(item.data.content.data.cover.feed, 4f)
                holder.ivAvatar.load(item.data.header.icon)
                holder.tvVideoDuration.text =
                    item.data.content.data.duration.conversionVideoDuration()
                holder.tvDescription.text = item.data.header.description
                holder.tvTitle.text = item.data.header.title
                if (item.data.content.data.ad) holder.tvLabel.visible() else holder.tvLabel.gone()
                if (item.data.content.data.library == "DAILY") holder.ivChoiceness.visible() else holder.ivChoiceness.gone()
                holder.ivShare.setOnClickListener {
                }
                holder.itemView.setOnClickListener {
                    item.data.content.data.run {

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewHelper.getViewHolder(parent, viewType)
    }
}