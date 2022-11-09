package com.wyang.study.ui.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wyang.study.R
import com.wyang.study.ui.fragment_main.MainFragment

class FragmentFactory(private val mActivity: FragmentActivity) {
    private var currentFragment: Fragment? = null
    private var currentPos = -1

    fun selectItem(pos: Int) {
        //点击的正是当前正在显示的，直接返回
        if (currentPos == pos) return
        val manager = mActivity.supportFragmentManager
        val transaction = manager.beginTransaction()
        //隐藏当前正在显示的fragment
        currentFragment?.let { transaction.hide(it) }
        currentPos = pos
        val fragment = manager.findFragmentByTag(getTag(pos))
        //通过findFragmentByTag判断是否已存在目标fragment，若存在直接show，否则去add
        if (fragment != null) {
            currentFragment = fragment
            transaction.show(fragment)
        } else {
            currentFragment = createFragment(pos)
            transaction.add(R.id.fl_container, currentFragment!!, getTag(pos))
        }
        transaction.commitAllowingStateLoss()
    }

    private fun getTag(pos: Int): String {
        return "fg_tag_$pos"
    }

    private fun createFragment(pos: Int): Fragment {
        return MainFragment.newInstance(pos)
    }
}
