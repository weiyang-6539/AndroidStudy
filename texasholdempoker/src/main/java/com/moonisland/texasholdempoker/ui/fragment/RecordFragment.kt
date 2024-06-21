package com.moonisland.texasholdempoker.ui.fragment

import android.os.Bundle
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.adapter.GameRecordAdapter
import com.moonisland.texasholdempoker.databinding.FragmentRecordBinding
import com.moonisland.texasholdempoker.ext.navigate
import com.moonisland.texasholdempoker.ext.once
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.moonisland.texasholdempoker.ui.widget.decoration.SpacingDecoration
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author WeiYang
 * @date 2024/6/18
 * @desc
 */
@AndroidEntryPoint
class RecordFragment : BaseVMFragment<RecordViewModel, FragmentRecordBinding>() {
    private val mAdapter by lazy { GameRecordAdapter() }
    override fun initialize() {
        once<Boolean>("refresh_game_record_list") {
            if (it) {
                mViewModel.queryAllGameRecord()
            }
        }
        with(mBinding) {
            mRecyclerView.addItemDecoration(SpacingDecoration.newDecoration())
            mRecyclerView.adapter = mAdapter
        }
        with(mAdapter) {
            setOnItemClickListener { _, _, position ->
                getItem(position)?.apply {
                    navigate(R.id.action_navigation_to_fragment_record_detail, Bundle().apply {
                        putLong("id", id)
                    })
                }
            }
        }
        mViewModel.queryAllGameRecord()
    }

    override fun startObserver() {
        mViewModel.gameRecordListResult.observe(this) {
            mAdapter.submitList(it)
        }
    }
}