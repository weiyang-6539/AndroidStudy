package com.w6539.base_jetpack

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author Yang
 * @since 2022/6/23 9:58
 * @desc ViewPager2的Fragment适配器
 */
class PagerFragmentAdapter : FragmentStateAdapter {
    val fragments = ArrayList<Fragment>()

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}