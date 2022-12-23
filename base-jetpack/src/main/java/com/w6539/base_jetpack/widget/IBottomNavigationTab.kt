package com.w6539.base_jetpack.widget

import android.view.View

/**
 * @author Yang
 * @since 2022/12/7 16:29
 * @desc
 */
interface IBottomNavigationTab {
    fun getTab(): View

    fun setSelected(isSelected: Boolean)
}