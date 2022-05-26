package com.wyang.study.ui

import android.view.Menu
import com.wyang.study.R
import com.wyang.study.bean.Simple
import com.wyang.study.databinding.ActivityContainerBinding
import com.wyang.study.databinding.LayoutToolbarBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.DataProvider

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {
    var toolbarBinding: LayoutToolbarBinding? = null
    private var simple: Simple? = null

    override fun getViewBinding(): ActivityContainerBinding {
        return ActivityContainerBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        simple = intent.getSerializableExtra("simple") as Simple

        mBinding?.root?.let { toolbarBinding = LayoutToolbarBinding.bind(it) }

        val fragment = DataProvider.createFragmentByName(simple?.className)

        val transaction = supportFragmentManager.beginTransaction()
        if (fragment != null) {
            transaction.add(R.id.fl_container, fragment)
        }
        transaction.commitAllowingStateLoss()

        toolbarBinding?.toolbar?.let { initToolBar(it, simple?.title, true) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_right, menu)
        return true
    }
}