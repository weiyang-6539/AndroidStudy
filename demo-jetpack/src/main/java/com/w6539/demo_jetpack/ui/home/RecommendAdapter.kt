package com.w6539.demo_jetpack.ui.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.w6539.demo_jetpack.bean.HomePageRecommend
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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewHelper.getViewHolder(parent, viewType)
    }
}