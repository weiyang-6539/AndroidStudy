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
class AddPlayerFragment : BaseTexasFragment<RecordViewModel, FragmentCreateRecordBinding>() {
    private val mAdapter by lazy { PlayerAdapter() }

    private val alreadyExistIds: ArrayList<Long> by lazy { requireArguments().getSerializable("ids") as ArrayList<Long> }
    override fun initialize() {
        with(mBinding) {
            mRecyclerView.adapter = mAdapter
            tvCancel.click {
                navigateUp()
            }
            tvConfirm.click {
                emit("ids", mAdapter.getCheckIds())
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
                !alreadyExistIds.contains(it.id)
            })
        }
    }
}