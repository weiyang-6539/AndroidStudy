package com.w6539android.base.widget.decoration

/**
 * @author Yang
 * @since 2022/12/12 16:55
 * @desc
 */
interface StickyDelegate : SpacingDecorationDelegate {
    fun getGroupName(position: Int): String
}