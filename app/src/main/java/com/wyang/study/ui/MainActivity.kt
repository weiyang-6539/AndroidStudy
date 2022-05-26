package com.wyang.study.ui

import android.view.Gravity
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.ActionBarDrawerToggle
import com.wyang.study.R
import com.wyang.study.databinding.ActivityMainBinding
import com.wyang.study.databinding.LayoutToolbarBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.FragmentFactory

class MainActivity : BaseActivity<ActivityMainBinding>() {
    var toolbarBinding: LayoutToolbarBinding? = null

    private var mFactory: FragmentFactory? = null

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mFactory = FragmentFactory(this)

        mBinding?.root?.let { toolbarBinding = LayoutToolbarBinding.bind(it) }

        toolbarBinding?.toolbar?.let { initToolBar(it, "Android Study", true) }
        val drawerToggle = ActionBarDrawerToggle(
            this, mBinding?.drawerLayout,
            toolbarBinding?.toolbar, 0, 0
        )
        drawerToggle.syncState()

        mBinding?.navigationView?.setNavigationItemSelectedListener { item: MenuItem ->
            with(mBinding?.drawerLayout) { this?.closeDrawer(Gravity.LEFT) }
            val integer: Int? = idMap[item.itemId]
            if (integer != null)
                mFactory!!.selectItem(integer)

            false
        }

        mFactory?.selectItem(0)
    }

    private val idMap: Map<Int, Int> = object : HashMap<Int, Int>() {
        init {
            put(R.id.navigation_0, 0)
            put(R.id.navigation_1, 1)
            put(R.id.navigation_2, 2)
            put(R.id.navigation_3, 3)
            put(R.id.navigation_4, 4)
            put(R.id.navigation_5, 5)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Thread.dumpStack()
        return super.dispatchTouchEvent(ev)
    }
}