package com.w6539android.base.ui.recycler.interfaces

/**
 * @author Yang
 * @since 2023/9/1 9:29
 * @desc 多数据适配器接口
 */
interface IBaseAdapter<T> : IListener<IBaseAdapter<T>> {
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

    fun isFullSpanAdapter() = false
    fun isFullSpanItem(position: Int) = false
}