package com.wyang.study.ui

import android.view.Menu
import androidx.fragment.app.Fragment
import com.wyang.study.R
import com.wyang.study.bean.Simple
import com.wyang.study.databinding.ActivityContainerBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.fragment.NullFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {
    private val simple by lazy {
        intent.getSerializableExtra("simple") as Simple
    }

    override fun getViewBinding() = ActivityContainerBinding.inflate(layoutInflater)

    override fun initialize() {
        val fragment = simple.clazz?.newInstance() as? Fragment ?: NullFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container, fragment)
        transaction.commitAllowingStateLoss()

        initToolBar(mBinding.toolbar, simple.title, true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_right, menu)
        return true
    }
}