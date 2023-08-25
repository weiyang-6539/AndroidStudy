package com.w6539android.base.ui.bravh

/**
 * @author Yang
 * @since 2023/8/23 10:56
 * @desc 适配器公共接口
 */
interface IBaseAdapter<T> {
    fun convert(holder: BaseViewHolder, item: T)
}