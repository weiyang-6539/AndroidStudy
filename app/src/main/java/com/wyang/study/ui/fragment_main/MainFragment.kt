package com.wyang.study.ui.fragment_main

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.w6539android.baselib.widget.decoration.SpacingDecoration
import com.wyang.study.adapter.SimpleAdapter
import com.wyang.study.databinding.FragmentMainBinding
import com.wyang.study.databinding.WidgetRecyclerBinding
import com.wyang.study.ui.ContainerActivity
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.ActivityUtils
import com.wyang.study.ui.util.DataProvider

class MainFragment private constructor() : BaseFragment<FragmentMainBinding>() {
    private val mRecyclerViewBinding by lazy { WidgetRecyclerBinding.bind(mBinding.root) }
    private val mAdapter: SimpleAdapter = SimpleAdapter()

    companion object {
        fun newInstance(page: Int): MainFragment {
            val fg = MainFragment()
            val args = Bundle()
            args.putInt("page", page)

            fg.arguments = args
            return fg
        }
    }

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mRecyclerViewBinding.mRecyclerView.layoutManager = GridLayoutManager(context, 2)
        mRecyclerViewBinding.mRecyclerView.addItemDecoration(
            SpacingDecoration.newDecoration()
        )

        mAdapter.bindToRecyclerView(mRecyclerViewBinding.mRecyclerView)
        mAdapter.setOnItemClickListener { _: BaseQuickAdapter<*, *>?, _: View?, position: Int ->
            val simple = mAdapter.data[position]
            if (!TextUtils.isEmpty(simple.className)) {
                val bundle = Bundle()
                bundle.putSerializable("simple", simple)
                context?.let {
                    ActivityUtils.startActivity(it, ContainerActivity::class.java, bundle)
                }
            } else {
                Toast.makeText(context, "请设置对应Fragment!", Toast.LENGTH_LONG).show()
            }
        }

        if (arguments != null) {
            val type = requireArguments().getInt("page")
            mAdapter.setNewData(DataProvider.getMainPageData(type))
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            val type = requireArguments().getInt("page")

            log("$type:hidden:$hidden:重新加载loadData")
        }
    }
}