package com.wyang.study.ui

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.w6539android.base.ext.openActivity
import com.w6539android.base.ui.recycler.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.adapter.SimpleAdapter
import com.wyang.study.bean.Simple
import com.wyang.study.databinding.ActivityMainBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.DataProvider

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mAdapter = SimpleAdapter()
    private val dataMap = mutableMapOf<Int, MutableList<Simple>>()

    private fun getData(index: Int): MutableList<Simple> {
        var list = dataMap[index]
        if (list == null) {
            list = DataProvider.getMainPageData(index)
            dataMap[index] = list
        }
        return list
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initialize() {
        mBinding.apply {
            mRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            mRecyclerView.addItemDecoration(
                SpacingDecoration.newDecoration()
            )
            toolbar.title = "Android Study"
            val drawerToggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, toolbar, 0, 0
            )
            drawerToggle.syncState()

            navigationView.setNavigationItemSelectedListener { item: MenuItem ->
                drawerLayout.closeDrawer(navigationView)
                when (item.itemId) {
                    R.id.navigation_0 -> mAdapter.setNewData(getData(0))
                    R.id.navigation_1 -> mAdapter.setNewData(getData(1))
                    R.id.navigation_2 -> mAdapter.setNewData(getData(2))
                    R.id.navigation_3 -> mAdapter.setNewData(getData(3))
                    R.id.navigation_4 -> mAdapter.setNewData(getData(4))
                    R.id.navigation_5 -> mAdapter.setNewData(getData(5))
                }
                false
            }
            mRecyclerView.adapter = mAdapter
            mAdapter.setItemClickListener { _, _, position ->
                val simple = mAdapter.get(position)
                if (simple.isActivity) {
                    simple.clazz?.let {
                        openActivity(it as Class<out Activity>)
                    }
                } else {
                    Bundle().apply {
                        putSerializable("simple", simple)
                        openActivity(ContainerActivity::class.java, this)
                    }
                }
            }
            mAdapter.setNewData(getData(0))
        }
    }
}