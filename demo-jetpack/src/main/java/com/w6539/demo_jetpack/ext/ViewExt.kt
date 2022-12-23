package com.w6539.demo_jetpack.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.w6539.demo_jetpack.R

/**
 * @author Yang
 * @since 2022/12/8 16:39
 * @desc
 */
fun TabLayout.bind(vp: ViewPager2, tabTexts: MutableList<String>) {
    TabLayoutMediator(this, vp, true, vp.isUserInputEnabled) { tab, position ->
        val tabCustom = getItemView(R.layout.layout_tab)
        tabCustom.findViewById<AppCompatTextView>(R.id.tv_text).text = tabTexts[position]
        tab.customView = tabCustom
    }.attach()
}

//ViewGroup
fun ViewGroup.getItemView(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutResId, this, false)
}