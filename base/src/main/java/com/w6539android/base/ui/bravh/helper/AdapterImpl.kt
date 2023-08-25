package com.w6539android.base.ui.bravh.helper

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.core.util.keyIterator
import androidx.recyclerview.widget.RecyclerView
import com.w6539android.base.ui.bravh.BaseViewHolder
import com.w6539android.base.ui.bravh.OnClickListener
import com.w6539android.base.ui.bravh.OnLongClickListener
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Yang
 * @since 2023/8/23 10:33
 * @desc
 */
class AdapterImpl<T, Adapter : RecyclerView.Adapter<BaseViewHolder>> : IAdapter<T, Adapter> {
    private var itemClickListener: OnClickListener<Adapter>? = null
    private var itemLongClickListener: OnLongClickListener<Adapter>? = null
    private var itemChildClickListeners = SparseArray<OnClickListener<Adapter>>(3)
    private var itemChildLongClickListeners = SparseArray<OnLongClickListener<Adapter>>(3)

    override fun setItemClickListener(listener: OnClickListener<Adapter>) {
        itemClickListener = listener
    }

    override fun setItemLongClickListener(listener: OnLongClickListener<Adapter>) {
        itemLongClickListener = listener
    }

    override fun addItemChildClickListener(
        @IdRes id: Int, listener: OnClickListener<Adapter>
    ) {
        itemChildClickListeners.put(id, listener)
    }

    override fun addItemChildLongClickListener(
        @IdRes id: Int,
        listener: OnLongClickListener<Adapter>
    ) {
        itemChildLongClickListeners.put(id, listener)
    }

    override fun setClickListener(adapter: Adapter, holder: BaseViewHolder, viewType: Int) {
        holder.apply {
            itemClickListener?.let {
                itemView.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener?.invoke(adapter, it, position)
                    }
                }
            }

            itemLongClickListener?.let {
                itemView.setOnLongClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemLongClickListener?.invoke(adapter, it, position)
                    }
                    false
                }
            }

            itemChildClickListeners.keyIterator().forEach { viewId ->
                holder.getViewOrNull<View>(viewId)?.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemChildClickListeners.get(viewId)?.invoke(adapter, it, position)
                    }
                }
            }

            itemChildLongClickListeners.keyIterator().forEach { viewId ->
                holder.getViewOrNull<View>(viewId)?.setOnLongClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        itemChildLongClickListeners.get(viewId)?.invoke(adapter, it, position)
                    }
                    false
                }
            }
        }
    }

    lateinit var items: List<T>

    /**
     * items 转化为 MutableList
     */
    private val mutableItems: MutableList<T>
        get() {
            return when (items) {
                is ArrayList -> {
                    items as ArrayList
                }
                is MutableList -> {
                    items as MutableList
                }
                else -> {
                    items.toMutableList().apply { items = this }
                }
            }
        }

    override fun setNewData(list: List<T>) {
        items = list
    }

    override fun set(position: Int, data: T) {
        mutableItems[position] = data
    }

    override fun add(data: T) {
        mutableItems.add(data)
    }

    override fun add(position: Int, data: T) {
        if (position > mutableItems.size || position < 0) {
            throw IndexOutOfBoundsException("position: ${position}. size:${items.size}")
        }
        mutableItems.add(position, data)
    }

    override fun addAll(newCollection: Collection<T>) {
        mutableItems.addAll(newCollection)
    }

    override fun addAll(position: Int, newCollection: Collection<T>) {
        if (position > mutableItems.size || position < 0) {
            throw IndexOutOfBoundsException("position: ${position}. size:${items.size}")
        }
        mutableItems.addAll(position, newCollection)
    }

    override fun removeAt(position: Int) {
        mutableItems.removeAt(position)
    }

    override fun remove(data: T) {
        mutableItems.remove(data)
    }

    override fun swap(fromPosition: Int, toPosition: Int) {
        val size = items.size
        if (fromPosition in 0 until size || toPosition in 0 until size) {
            Collections.swap(mutableItems, fromPosition, toPosition)
        }
    }

    override fun move(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            move(toPosition, fromPosition)
        } else {
            mutableItems.add(toPosition, mutableItems.removeAt(fromPosition))
        }
    }

    override fun getItemData(position: Int) = mutableItems.getOrNull(position)
}