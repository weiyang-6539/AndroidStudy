package com.w6539.base_jetpack.base.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder

/**
 * @author WeiYang
 * @date 2024/5/30
 * @desc
 */
abstract class BaseAdapter<T : Any>(@LayoutRes val layoutId: Int) :
    BaseQuickAdapter<T, QuickViewHolder>() {

    final override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ) = QuickViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: T?) {
        item?.let {
            convert(holder, it)
        }
    }

    override fun onBindViewHolder(
        holder: QuickViewHolder,
        position: Int,
        item: T?,
        payloads: List<Any>
    ) {
        item?.let { convert(holder, it, payloads[0]) }
    }

    abstract fun convert(holder: QuickViewHolder, item: T)

    open fun convert(holder: QuickViewHolder, item: T, payload: Any) {}
}