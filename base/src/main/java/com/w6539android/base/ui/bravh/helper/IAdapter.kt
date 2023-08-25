package com.w6539android.base.ui.bravh.helper

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.ui.bravh.BaseViewHolder
import com.w6539android.base.ui.bravh.OnClickListener
import com.w6539android.base.ui.bravh.OnLongClickListener

/**
 * @author Yang
 * @since 2023/8/22 17:28
 * @desc
 */
interface IAdapter<T, Adapter : RecyclerView.Adapter<BaseViewHolder>> {

    fun setNewData(list: List<T>)
    fun set(position: Int, data: T)
    fun add(data: T)
    fun add(position: Int, data: T)
    fun addAll(newCollection: Collection<T>)
    fun addAll(position: Int, newCollection: Collection<T>)
    fun removeAt(position: Int)
    fun remove(data: T)
    fun swap(fromPosition: Int, toPosition: Int)
    fun move(fromPosition: Int, toPosition: Int)

    fun getItemData(position: Int): T?

    /** event */
    fun setItemClickListener(listener: OnClickListener<Adapter>)
    fun setItemLongClickListener(listener: OnLongClickListener<Adapter>)
    fun addItemChildClickListener(@IdRes id: Int,listener: OnClickListener<Adapter>)
    fun addItemChildLongClickListener(@IdRes id: Int,listener: OnLongClickListener<Adapter>)

    fun setClickListener(adapter: Adapter, holder: BaseViewHolder, viewType: Int)
}