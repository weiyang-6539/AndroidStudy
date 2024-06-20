package com.moonisland.texasholdempoker.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.databinding.FragmentMainBinding
import com.moonisland.texasholdempoker.ext.navigate
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @since 2022/12/7 11:47
 * @desc 主页面fragment
 */
@AndroidEntryPoint
class MainFragment : BaseVBFragment<FragmentMainBinding>() {
    private val fragments = mutableListOf<Fragment>(
        RecordFragment(),
        RankFragment(),
    )
    private var currentPos = -1
    private var currentFragment: Fragment? = null

    override fun initialize() {
        with(mBinding) {
            navView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_record -> select(0)
                    R.id.navigation_player -> select(1)
                }
                true
            }
            navView.selectedItemId = R.id.navigation_record

            flAdd.setOnClickListener {
                navigate(R.id.action_navigation_to_fragment_create_record)
            }
        }

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