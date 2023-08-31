package com.w6539android.base.ui.bravh

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * @author Yang
 * @since 2023/8/21 17:23
 * @desc 分页适配器, 无限滚动
 */
abstract class BasePagingAdapter<T : Any>(
    open var callback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseViewHolder>(callback) {
}