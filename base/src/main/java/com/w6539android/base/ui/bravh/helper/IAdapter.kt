package com.w6539android.base.ui.bravh.helper

import androidx.annotation.IdRes
import com.w6539android.base.ui.bravh.OnClickListener
import com.w6539android.base.ui.bravh.OnLongClickListener

/**
 * @author Yang
 * @since 2023/8/22 17:28
 * @desc
 */
interface IAdapter<T> {

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
    fun get(position: Int): T

    /** event */
    fun setItemClickListener(listener: OnClickListener<IAdapter<T>>)
    fun setItemLongClickListener(listener: OnLongClickListener<IAdapter<T>>)
    fun addItemChildClickListener(@IdRes id: Int,listener: OnClickListener<IAdapter<T>>)
    fun addItemChildLongClickListener(@IdRes id: Int,listener: OnLongClickListener<IAdapter<T>>)
}