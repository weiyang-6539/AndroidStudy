package com.w6539android.base.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.*
import com.w6539android.base.ui.recycler.helper.AdapterImpl
import com.w6539android.base.ui.recycler.interfaces.IBaseAdapter
import com.w6539android.base.ui.recycler.interfaces.IViewHolder
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Yang
 * @since 2023/8/21 10:32
 * @desc 单条目列表
 */
abstract class BaseDifferAdapter<T>(
    @LayoutRes private val layoutId: Int,
    callback: DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any) = false
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any) = false
    },
    executor: Executor = BaseDifferAdapter.executor
) : ListAdapter<T, BaseViewHolder>(
    AsyncDifferConfig.Builder(callback)
        .setBackgroundThreadExecutor(executor)
        .build()
), IBaseAdapter<T>, IViewHolder<T> {
    private companion object {
        val executor: ExecutorService = Executors.newFixedThreadPool(5)
    }

    private val mAdapterImpl = AdapterImpl<T>()

    init {
        mAdapterImpl.setNewData(currentList)
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layoutId, parent).also {
            mAdapterImpl.setClickListener(this, it)
        }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, getItem(position))
    }

    final override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            convert(holder, getItem(position))
        } else {
            convert(holder, getItem(position), payloads[0])
        }
    }

    override fun convert(holder: BaseViewHolder, item: T, payload: Any) {

    }

    /*点击事件及数据操作*/
    final override fun setItemClickListener(
        listener: (
            adapter: IBaseAdapter<T>,
            view: View,
            position: Int
        ) -> Unit
    ) {
        mAdapterImpl.setItemClickListener(listener)
    }

    final override fun setItemLongClickListener(
        listener: (
            adapter: IBaseAdapter<T>,
            view: View,
            position: Int
        ) -> Boolean
    ) {
        mAdapterImpl.setItemLongClickListener(listener)
    }

    final override fun addItemChildClickListener(
        @IdRes id: Int,
        listener: (
            adapter: IBaseAdapter<T>,
            view: View,
            position: Int
        ) -> Unit
    ) {
        mAdapterImpl.addItemChildClickListener(id, listener)
    }

    final override fun addItemChildLongClickListener(
        @IdRes id: Int,
        listener: (
            adapter: IBaseAdapter<T>,
            view: View,
            position: Int
        ) -> Boolean
    ) {
        mAdapterImpl.addItemChildLongClickListener(id, listener)
    }

    final override fun setNewData(list: List<T>) {
        mAdapterImpl.setNewData(list)
        submitList(list)
    }

    final override fun set(@IntRange(from = 0) position: Int, data: T) {
        mAdapterImpl.set(position, data)
    }

    final override fun add(data: T) {
        mAdapterImpl.add(data)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun add(@IntRange(from = 0) position: Int, data: T) {
        mAdapterImpl.add(position, data)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun addAll(newCollection: Collection<T>) {
        mAdapterImpl.addAll(newCollection)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun addAll(
        @IntRange(from = 0) position: Int,
        newCollection: Collection<T>
    ) {
        mAdapterImpl.addAll(position, newCollection)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun removeAt(@IntRange(from = 0) position: Int) {
        mAdapterImpl.removeAt(position)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun remove(data: T) {
        mAdapterImpl.remove(data)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun swap(
        @IntRange(from = 0) fromPosition: Int,
        @IntRange(from = 0) toPosition: Int
    ) {
        mAdapterImpl.swap(fromPosition, toPosition)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun move(fromPosition: Int, toPosition: Int) {
        mAdapterImpl.move(fromPosition, toPosition)
        submitList(mAdapterImpl.items.toMutableList())
    }

    final override fun get(position: Int) =
        mAdapterImpl.get(position)
}