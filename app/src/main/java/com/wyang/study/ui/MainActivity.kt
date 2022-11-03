package com.wyang.study.ui

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.wyang.study.R
import com.wyang.study.databinding.ActivityMainBinding
import com.wyang.study.databinding.LayoutToolbarBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.FragmentFactory

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val toolbarBinding by lazy {
        LayoutToolbarBinding.bind(mBinding.root)
    }

    private val mFactory = FragmentFactory(this)

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun initialize() {
        mBinding.drawerLayout.animate().let { }

        initToolBar(toolbarBinding.toolbar, "Android Study", true)
        val drawerToggle = ActionBarDrawerToggle(
            this, mBinding.drawerLayout,
            toolbarBinding.toolbar, 0, 0
        )
        drawerToggle.syncState()

        mBinding.navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            mBinding.drawerLayout.closeDrawer(mBinding.navigationView)
            val integer = idMap[item.itemId]
            if (integer != null)
                mFactory.selectItem(integer)
            false
        }

        mFactory.selectItem(0)
    }

    private val idMap = mutableMapOf(
        R.id.navigation_0 to 0,
        R.id.navigation_1 to 1,
        R.id.navigation_2 to 2,
        R.id.navigation_3 to 3,
        R.id.navigation_4 to 4,
        R.id.navigation_5 to 5,
    )
}