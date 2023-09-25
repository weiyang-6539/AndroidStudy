package com.wyang.study.bean

import com.w6539android.base.ui.recycler.entity.MultiItemEntity

/**
 * @author Yang
 * @since 2023/9/22 14:29
 * @desc
 */
class Banner : MultiItemEntity {
    override fun getItemType(): Int {
        return Int.MAX_VALUE
    }
}