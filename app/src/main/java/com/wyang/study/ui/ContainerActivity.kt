package com.wyang.study.ui

import android.view.Menu
import com.wyang.study.R
import com.wyang.study.bean.Simple
import com.wyang.study.databinding.ActivityContainerBinding
import com.wyang.study.databinding.LayoutToolbarBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.DataProvider

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {
    private val toolbarBinding by lazy {
        LayoutToolbarBinding.bind(mBinding.root)
    }
    private val simple by lazy {
        intent.getSerializableExtra("simple") as Simple
    }

    override fun getViewBinding(): ActivityContainerBinding {
        return ActivityContainerBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        val fragment = DataProvider.createFragmentByName(simple.className)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container, fragment)
        transaction.commitAllowingStateLoss()

        initToolBar(toolbarBinding.toolbar, simple.title, true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_right, menu)
        return true
    }
}