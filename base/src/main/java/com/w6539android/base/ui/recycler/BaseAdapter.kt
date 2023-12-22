package com.w6539android.base.ui.recycler

import android.annotation.SuppressLint
import android.util.Log
import android.util.SparseIntArray
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.ui.recycler.entity.MultiItemEntity
import com.w6539android.base.ui.recycler.helper.AdapterImpl
import com.w6539android.base.ui.recycler.interfaces.IBaseAdapter
import com.w6539android.base.ui.recycler.interfaces.IViewHolder
import kotlin.math.abs
import kotlin.math.min

/**
 * @author Yang
 * @since 2023/8/30 16:05
 * @desc 适用于不频繁刷新列表, layoutId非零为单条目, layoutIds size > 1 为多条目
 */
abstract class BaseAdapter<T>(
    @LayoutRes private val layoutId: Int = 0,
) : RecyclerView.Adapter<BaseViewHolder>(), IBaseAdapter<T>, IViewHolder<T> {
    private val mAdapterImpl = AdapterImpl<T>()
    private val layoutIds = SparseIntArray()

    init {
        mAdapterImpl.setNewData(emptyList())
        if (layoutId != 0) {
            layoutIds.put(0, layoutId)
        }
    }

    protected fun addItemType(type: Int, @LayoutRes layoutResId: Int) {
        layoutIds.put(type, layoutResId)
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layoutIds.get(viewType), parent).also {
            mAdapterImpl.setClickListener(this, it)
        }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, get(position))
    }

    final override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            convert(holder, get(position)!!)
        } else {
            convert(holder, get(position)!!, payloads[0])
        }
    }

    override fun getItemCount() = mAdapterImpl.items.size

    override fun getItemViewType(position: Int): Int {
        val t = get(position)
        if (t is MultiItemEntity) {
            return t.getItemType()
        }
        return super.getItemViewType(position)
    }

    override fun convert(holder: BaseViewHolder, item: T, payload: Any) {}

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

    @SuppressLint("NotifyDataSetChanged")
    final override fun setNewData(list: List<T>) {
        mAdapterImpl.setNewData(list)
        notifyDataSetChanged()
    }

    final override fun set(@IntRange(from = 0) position: Int, data: T) {
        mAdapterImpl.set(position, data)
        notifyItemChanged(position)
    }

    final override fun add(data: T) {
        mAdapterImpl.add(data)
        notifyItemInserted(mAdapterImpl.items.size - 1)
    }

    final override fun add(@IntRange(from = 0) position: Int, data: T) {
        mAdapterImpl.add(position, data)
        notifyItemInserted(position)
    }

    final override fun addAll(newCollection: Collection<T>) {
        val oldSize = mAdapterImpl.items.size
        mAdapterImpl.addAll(newCollection)
        notifyItemRangeInserted(oldSize, newCollection.size)
    }

    final override fun addAll(
        @IntRange(from = 0) position: Int,
        newCollection: Collection<T>
    ) {
        mAdapterImpl.addAll(position, newCollection)
        notifyItemRangeChanged(position, newCollection.size)
    }

    final override fun removeAt(@IntRange(from = 0) position: Int) {
        mAdapterImpl.removeAt(position)
        notifyItemRemoved(position)
    }

    final override fun remove(data: T) {
        mAdapterImpl.remove(data)
        notifyItemRemoved(mAdapterImpl.items.size - 1)
    }

    final override fun swap(
        @IntRange(from = 0) fromPosition: Int,
        @IntRange(from = 0) toPosition: Int
    ) {
        mAdapterImpl.swap(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    final override fun move(fromPosition: Int, toPosition: Int) {
        mAdapterImpl.move(fromPosition, toPosition)
        notifyItemRangeChanged(min(fromPosition, toPosition), abs(fromPosition - toPosition) + 1)
    }

    final override fun get(position: Int) = mAdapterImpl.get(position)
}