package com.w6539android.base.ui.bravh

import androidx.annotation.LayoutRes

/**
 * @author Yang
 * @since 2023/8/22 16:51
 * @desc
 */
class SingleItemAdapter<T>(
    @LayoutRes private val layoutId: Int,
    private var data: T? = null
) : BaseQuickAdapter<T>(layoutId) {

    init {

    }

    override fun convert(holder: BaseViewHolder, item: T) {
    }


}