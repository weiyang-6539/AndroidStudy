package com.w6539android.base.ui.bravh

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.ui.bravh.entity.SpanSizeEntity
import com.w6539android.base.ui.bravh.helper.AdapterImpl
import com.w6539android.base.ui.bravh.helper.IAdapter
import kotlin.math.abs
import kotlin.math.min

/**
 * @author Yang
 * @since 2023/8/30 16:05
 * @desc 适用于不频繁刷新列表
 */
abstract class BaseQuickAdapter<T>(
    @LayoutRes private val layoutId: Int = 0,
) : RecyclerView.Adapter<BaseViewHolder>(), IBaseAdapter<T>, IAdapter<T> {
    private val mAdapterImpl = AdapterImpl<T>()

    init {
        mAdapterImpl.setNewData(emptyList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        createBaseViewHolder(parent, viewType).also {
            mAdapterImpl.setClickListener(this, it, viewType)
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
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

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layoutId, parent)

    override fun convert(holder: BaseViewHolder, item: T, payload: Any) {}

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val spanSizeLookup = manager.spanSizeLookup

            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return get(position).let {
                        if (it is SpanSizeEntity)
                            it.getSpanSize()
                        else
                            spanSizeLookup.getSpanSize(position)
                    }
                }
            }
        }
    }

    /*点击事件及数据操作*/
    final override fun setItemClickListener(
        listener: (
            adapter: IAdapter<T>,
            view: View,
            position: Int
        ) -> Unit
    ) {
        mAdapterImpl.setItemClickListener(listener)
    }

    final override fun setItemLongClickListener(
        listener: (
            adapter: IAdapter<T>,
            view: View,
            position: Int
        ) -> Boolean
    ) {
        mAdapterImpl.setItemLongClickListener(listener)
    }

    final override fun addItemChildClickListener(
        @IdRes id: Int,
        listener: (
            adapter: IAdapter<T>,
            view: View,
            position: Int
        ) -> Unit
    ) {
        mAdapterImpl.addItemChildClickListener(id, listener)
    }

    final override fun addItemChildLongClickListener(
        @IdRes id: Int,
        listener: (
            adapter: IAdapter<T>,
            view: View,
            position: Int
        ) -> Boolean
    ) {
        mAdapterImpl.addItemChildLongClickListener(id, listener)
    }

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
        notifyItemRangeChanged(min(fromPosition, toPosition), abs(fromPosition - toPosition + 1))
    }

    final override fun get(position: Int) =
        mAdapterImpl.get(position)
}