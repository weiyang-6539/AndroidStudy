package com.w6539.demo_jetpack.ui.fragment

import androidx.fragment.app.Fragment
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/7 11:47
 * @desc 主页面fragment
 */
@AndroidEntryPoint
class MainFragment : BaseVBFragment<FragmentMainBinding>() {
    private val fragments = mutableListOf<Fragment>(
        HomeFragment(), SquareFragment(), DiscoverFragment(), MineFragment()
    )
    private var currentPos = -1
    private var currentFragment: Fragment? = null

    override fun initialize() {
        mBinding.navView.itemIconTintList = null
        mBinding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> select(0)
                R.id.navigation_square -> select(1)
                R.id.navigation_discover -> select(2)
                R.id.navigation_mine -> select(3)
            }
            true
        }

        select(0)
    }

    private fun select(index: Int) {
        //点击的正是当前正在显示的，直接返回
        if (currentPos == index) return
        val manager = this.childFragmentManager
        val transaction = manager.beginTransaction()
        if (currentFragment != null) {
            //隐藏当前正在显示的fragment
            transaction.hide(currentFragment!!)
        }
        currentPos = index
        val fragment = manager.findFragmentByTag("fg_tag_$index")
        //通过findFragmentByTag判断是否已存在目标fragment，若存在直接show，否则去add
        if (fragment != null) {
            currentFragment = fragment
            transaction.show(fragment)
        } else {
            currentFragment = fragments[index]
            transaction.add(R.id.fl_container, currentFragment!!, "fg_tag_$index")
        }
        transaction.commitAllowingStateLoss()
    }
}