package com.w6539android.base.ui.bravh.extend

import android.util.SparseIntArray
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.w6539android.base.ui.bravh.BaseListAdapter
import com.w6539android.base.ui.bravh.BaseViewHolder
import com.w6539android.base.ui.bravh.entity.MultiItemEntity
import java.util.concurrent.Executor

/**
 * @author Yang
 * @since 2023/8/22 17:10
 * @desc 多条目列表
 */
abstract class BaseMultiItemAdapter<T : MultiItemEntity> : BaseListAdapter<T> {
    constructor() : super(0)
    constructor(
        callback: DiffUtil.ItemCallback<T>,
        executor: Executor
    ) : super(0, callback, executor)

    private val layouts by lazy(LazyThreadSafetyMode.NONE) { SparseIntArray() }

    protected fun addItemType(type: Int, @LayoutRes layoutRes: Int) {
        layouts.put(type, layoutRes)
    }

    override fun getItemViewType(position: Int): Int {
        return getItemData(position)?.getItemType()?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layouts.get(viewType), parent).apply {
            setClickListener(this@BaseMultiItemAdapter, this, viewType)
        }
}