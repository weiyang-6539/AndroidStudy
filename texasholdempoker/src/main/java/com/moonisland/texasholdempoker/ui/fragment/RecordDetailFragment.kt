package com.moonisland.texasholdempoker.ui.fragment

import android.util.Log
import com.moonisland.texasholdempoker.databinding.FragmentRecordDetailBinding
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import com.w6539.base_jetpack.ext.bean2Json
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@AndroidEntryPoint
class RecordDetailFragment : BaseVMFragment<RecordViewModel, FragmentRecordDetailBinding>() {
    private val id by lazy { requireArguments().getLong("id") }
    override fun initialize() {
        mViewModel.queryGameRecordById(id)
    }

    override fun startObserver() {
        mViewModel.gameRecordDetailResult.observe(this) {
            Log.e(mTag, it.bean2Json())
        }
    }
}