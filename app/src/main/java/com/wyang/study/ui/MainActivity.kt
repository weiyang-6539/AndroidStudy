package com.wyang.study.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.w6539android.base.widget.decoration.SpacingDecoration
import com.wyang.study.R
import com.wyang.study.adapter.SimpleAdapter
import com.wyang.study.bean.Simple
import com.wyang.study.databinding.ActivityMainBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.ActivityUtils
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

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

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
            mAdapter.bindToRecyclerView(mRecyclerView)
            mAdapter.setOnItemClickListener { _: BaseQuickAdapter<*, *>?, _: View?, position: Int ->
                val simple = mAdapter.data[position]
                if (!TextUtils.isEmpty(simple.className)) {
                    val bundle = Bundle()
                    bundle.putSerializable("simple", simple)
                    ActivityUtils.startActivity(
                        this@MainActivity,
                        ContainerActivity::class.java,
                        bundle
                    )
                } else {
                    Toast.makeText(this@MainActivity, "请设置对应Fragment!", Toast.LENGTH_LONG).show()
                }
            }

            mAdapter.setNewData(getData(0))
        }
    }
}