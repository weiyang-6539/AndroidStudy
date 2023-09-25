package com.w6539android.base.ui.recycler.interfaces

import com.w6539android.base.ui.recycler.BaseViewHolder

/**
 * @author Yang
 * @since 2023/9/1 9:24
 * @desc Item复用显示数据
 */
interface IViewHolder<T> {
    fun convert(holder: BaseViewHolder, item: T)
    fun convert(holder: BaseViewHolder, item: T, payload: Any)
}