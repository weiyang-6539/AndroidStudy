package com.w6539android.base.ui.bravh

import android.util.SparseIntArray
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.w6539android.base.ui.bravh.entity.MultiItemEntity

/**
 * @author Yang
 * @since 2023/8/22 17:10
 * @desc 多条目列表
 */
abstract class BaseMultiItemAdapter<T : MultiItemEntity> : BaseQuickAdapter<T>() {

    private val layoutIds by lazy(LazyThreadSafetyMode.NONE) { SparseIntArray() }

    protected fun addItemType(type: Int, @LayoutRes layoutId: Int) {
        layoutIds.put(type, layoutId)
    }

    override fun getItemViewType(position: Int) =
        get(position).getItemType()

    override fun createBaseViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(layoutIds.get(viewType), parent)

}