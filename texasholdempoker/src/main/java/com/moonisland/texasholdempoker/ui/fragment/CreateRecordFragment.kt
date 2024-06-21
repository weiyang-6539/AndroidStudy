package com.moonisland.texasholdempoker.ui.fragment

import com.moonisland.texasholdempoker.adapter.PlayerAdapter
import com.moonisland.texasholdempoker.databinding.FragmentCreateRecordBinding
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.ext.click
import com.moonisland.texasholdempoker.ext.emit
import com.moonisland.texasholdempoker.ext.navigateUp
import com.moonisland.texasholdempoker.ext.toast
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.moonisland.texasholdempoker.ui.base.BaseTexasFragment
import com.moonisland.texasholdempoker.ui.dialog.InputGameRecordNameDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author yang
 * @date 2024/6/20
 * @desc 创建对局
 */
@AndroidEntryPoint
class CreateRecordFragment : BaseTexasFragment<RecordViewModel, FragmentCreateRecordBinding>() {
    private val mAdapter by lazy { PlayerAdapter() }
    private val initScore = 1000
    override fun initialize() {
        with(mBinding) {
            mRecyclerView.adapter = mAdapter

            tvCancel.click {
                navigateUp()
            }
            tvConfirm.click {
                InputGameRecordNameDialog()
                    .apply {
                        call = {
                            mViewModel.createRecord(
                                GameRecord(
                                    0,
                                    it,
                                    mAdapter.getCheckIds(),
                                    initScore * mAdapter.getCheckIds().size,
                                ).apply {
                                    status = 1// 进行中
                                }
                            )
                        }
                    }
                    .show(childFragmentManager, "input_game_record_name")
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
        mViewModel.playerListResult.observe(this) {
            mAdapter.submitList(it)
        }
        mViewModel.createGameRecordResult.observe(this) {
            toast("对局创建成功:${it.id}")
            emit("refresh_game_record_list", true)
            navigateUp()
        }
    }
}