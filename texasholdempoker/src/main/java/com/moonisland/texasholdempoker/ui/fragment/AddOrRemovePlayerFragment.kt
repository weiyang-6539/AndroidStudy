package com.moonisland.texasholdempoker.ui.fragment

import com.moonisland.texasholdempoker.adapter.PlayerAdapter
import com.moonisland.texasholdempoker.databinding.FragmentCreateRecordBinding
import com.moonisland.texasholdempoker.ext.click
import com.moonisland.texasholdempoker.ext.emit
import com.moonisland.texasholdempoker.ext.navigateUp
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.moonisland.texasholdempoker.ui.base.BaseTexasFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author yang
 * @date 2024/6/22
 * @desc
 */
@AndroidEntryPoint
class AddOrRemovePlayerFragment :
    BaseTexasFragment<RecordViewModel, FragmentCreateRecordBinding>() {
    private val mAdapter by lazy { PlayerAdapter() }

    private val ids: ArrayList<Long> by lazy { requireArguments().getSerializable("ids") as ArrayList<Long> }
    private val isRemove: Boolean by lazy { requireArguments().getBoolean("isRemove", false) }
    override fun initialize() {
        with(mBinding) {
            mRecyclerView.adapter = mAdapter
            tvCancel.click {
                navigateUp()
            }
            tvConfirm.click {
                if (isRemove)
                    emit("removeIds", mAdapter.getCheckIds())
                else
                    emit("addIds", mAdapter.getCheckIds())
                navigateUp()
            }
        }

        with(mAdapter) {
            setOnItemClickListener { _, _, position ->
                resetChecked(position)

                mBinding.tvConfirm.isEnabled = getCheckIds().isNotEmpty()
            }
        }
        mViewModel.queryAllPlayer()
    }

    override fun startObserver() {
        mViewModel.playerListResult.observe(this) { list ->
            mAdapter.submitList(list.filter {
                if (isRemove) ids.contains(it.id) else !ids.contains(it.id)
            })
        }
    }
}