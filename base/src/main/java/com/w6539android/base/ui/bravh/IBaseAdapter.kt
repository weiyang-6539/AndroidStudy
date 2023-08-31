package com.w6539android.base.ui.bravh

import android.view.ViewGroup

/**
 * @author Yang
 * @since 2023/8/23 10:56
 * @desc 适配器公共接口
 */
interface IBaseAdapter<T> {
    fun createBaseViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
    fun convert(holder: BaseViewHolder, item: T)
    fun convert(holder: BaseViewHolder, item: T, payload: Any)
}