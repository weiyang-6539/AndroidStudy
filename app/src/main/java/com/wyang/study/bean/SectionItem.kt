package com.wyang.study.bean

import com.w6539android.base.ui.bravh.entity.MultiItemEntity
import com.w6539android.base.ui.bravh.entity.SpanSizeEntity

/**
 * @author Yang
 * @since 2023/8/23 16:57
 * @desc
 */
class SectionItem(
    val isMine: Boolean = false,
    val title: String = ""
) : MultiItemEntity, SpanSizeEntity {

    companion object {
        const val TYPE_HEADER_MINE = 1
        const val TYPE_HEADER_OTHER = 2
        const val TYPE_ITEM = 100
    }

    var channel: Channel? = null

    constructor(channel: Channel) : this() {
        this.channel = channel
    }

    override fun getItemType() = if (isMine) TYPE_HEADER_MINE else if (channel!=null)TYPE_ITEM else TYPE_HEADER_OTHER
    override fun getSpanSize() = if (channel == null) 4 else 1
}