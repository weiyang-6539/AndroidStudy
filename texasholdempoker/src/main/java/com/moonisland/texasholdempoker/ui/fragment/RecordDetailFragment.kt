package com.moonisland.texasholdempoker.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.adapter.PlayerRecordAdapter
import com.moonisland.texasholdempoker.databinding.FragmentRecordDetailBinding
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.db.entity.PlayerRecord
import com.moonisland.texasholdempoker.ext.click
import com.moonisland.texasholdempoker.ext.clicks
import com.moonisland.texasholdempoker.ext.emit
import com.moonisland.texasholdempoker.ext.formatFloat
import com.moonisland.texasholdempoker.ext.formatRecordStatus
import com.moonisland.texasholdempoker.ext.navigate
import com.moonisland.texasholdempoker.ext.navigateUp
import com.moonisland.texasholdempoker.ext.once
import com.moonisland.texasholdempoker.ext.toast
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.moonisland.texasholdempoker.strategy.Strategy01
import com.moonisland.texasholdempoker.ui.base.BaseTexasFragment
import com.moonisland.texasholdempoker.ui.dialog.SetRateDialog
import com.moonisland.texasholdempoker.ui.dialog.ConfirmDialog
import com.moonisland.texasholdempoker.ui.dialog.GameRecordMenuDialog
import com.moonisland.texasholdempoker.ui.dialog.SetPlayerScoreDialog
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@Suppress("LABEL_NAME_CLASH")
@AndroidEntryPoint
class RecordDetailFragment : BaseTexasFragment<RecordViewModel, FragmentRecordDetailBinding>() {
    private val id by lazy { requireArguments().getLong("id") }

    private val mHeaderAdapter = object : BaseSingleItemAdapter<GameRecord, QuickViewHolder>() {
        override fun onBindViewHolder(holder: QuickViewHolder, item: GameRecord?) {
            item?.apply {
                holder.setText(R.id.tv_name, "对局：${item.name}")
                    .setText(R.id.tv_player, "人数：${item.playerIds.size}")
                    .setText(R.id.tv_score, "积分：${item.score}")
                    .setText(R.id.tv_status, "状态：${formatRecordStatus(item.status)}")
                    .setText(R.id.tv_money, "结现：${item.money.formatFloat()}")
                    .setText(R.id.tv_deduct, "抽水：${item.deductMoney.formatFloat()}")
                    .setText(R.id.tv_bonus, "奖金：${item.bonusMoney.formatFloat()}")
                    .setGone(R.id.tv_money, item.status != 2)
                    .setGone(R.id.tv_deduct, item.status != 2)
                    .setGone(R.id.tv_bonus, item.status != 2)
                holder.itemView.clicks(2) {
                    showMenuDialog()
                }
            }
        }

        override fun onCreateViewHolder(
            context: Context,
            parent: ViewGroup,
            viewType: Int
        ) = QuickViewHolder(
            layoutInflater.inflate(
                R.layout.layout_header_record_detail,
                parent,
                false
            )
        )

    }
    private val mAdapter = PlayerRecordAdapter()
    override fun initialize() {
        once<ArrayList<Long>>("addIds") { list ->
            mViewModel.gameRecordDetailResult.value?.apply {
                list.forEach {
                    val playerRecord = PlayerRecord(
                        0,
                        it,
                        this.id
                    )
                    mViewModel.insertPlayerRecord(playerRecord)
                    this.playerIds.add(it)
                    this.score += playerRecord.loan
                }
                mViewModel.updateGameRecord(this)
            }
        }
        once<ArrayList<Long>>("removeIds") { list ->
            mViewModel.gameRecordDetailResult.value?.apply {
                mViewModel.deletePlayerRecords(list)
            }
        }
        with(mBinding) {
            btnBack.click { navigateUp() }
        }
        with(mAdapter) {
            addOnItemChildClickListener(R.id.btn_loan) { _, _, position ->
                getItem(position)?.let {
                    ConfirmDialog.newInstance("${it.player?.name}借贷1000分")
                        .apply {
                            confirmCall = {
                                it.loan += 1000
                                mViewModel.invokeLoan(it, true)
                                true
                            }
                        }
                        .show(childFragmentManager, "loan")
                }
            }
            addOnItemChildClickListener(R.id.btn_loan_) { _, _, position ->
                getItem(position)?.let {
                    ConfirmDialog.newInstance("${it.player?.name}还贷1000分")
                        .apply {
                            confirmCall = {
                                it.loan -= 1000
                                mViewModel.invokeLoan(it, false)
                                true
                            }
                        }
                        .show(childFragmentManager, "loan")
                }
            }
            addOnItemChildClickListener(R.id.btn_rate) { _, _, position ->
                getItem(position)?.let {
                    SetRateDialog()
                        .apply {
                            call = { rate ->
                                it.rate = rate
                                it.isLockRate = true
                                mViewModel.updatePlayerRecord(it)
                                true
                            }
                        }
                        .show(childFragmentManager, "change_rate")
                }
            }
            addOnItemChildClickListener(R.id.btn_payoff) { _, _, position ->
                getItem(position)?.let {
                    SetPlayerScoreDialog.newInstance(it.player?.name ?: "")
                        .apply {
                            call = { score ->
                                it.score = score
                                mViewModel.updatePlayerRecord(it)
                                true
                            }
                        }
                        .show(childFragmentManager, "set_score")
                }
            }
        }
        mViewModel.queryGameRecordById(id)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun startObserver() {
        mViewModel.gameRecordDetailResult.observe(this) {
            mAdapter.status = it.status
            mHeaderAdapter.setItem(it, null)
//            mBinding.btnAddPlayer.isEnabled = it.status != 2
//            mBinding.btnCalculate.isEnabled = it.status != 2
        }
        mViewModel.playerRecordsResult.observe(this) {
            mAdapter.submitList(it)
            mBinding.mRecyclerView.adapter = ConcatAdapter().apply {
                addAdapter(mHeaderAdapter)
                addAdapter(mAdapter)
            }
        }
        mViewModel.updateRecordResult.observe(this) {
            emit("refresh_game_record_list", true)

            mAdapter.notifyDataSetChanged()
        }
        mViewModel.deleteRecordResult.observe(this) {
            toast("删除成功")
            emit("refresh_game_record_list", true)
            navigateUp()
        }
    }

    private fun showMenuDialog() {
        GameRecordMenuDialog()
            .apply {
                addPlayerCall = { addPlayer() }
                removePlayerCall = { removePlayer() }
                calculateCall = { calculate() }
                deleteCall = {
                    ConfirmDialog.newInstance("确认删除当前记录？")
                        .apply {
                            confirmCall = {
                                mViewModel.gameRecordDetailResult.value?.apply {
                                    mViewModel.deleteGameRecord(this)
                                }
                                true
                            }
                        }
                        .show(this@RecordDetailFragment.childFragmentManager, "loan")
                }
            }
            .show(childFragmentManager, "menu")
    }

    private fun addPlayer() {
        navigate(
            R.id.action_navigation_to_fragment_add_player,
            Bundle().apply {
                putSerializable("ids", mAdapter.getUidList())
            })
    }

    private fun removePlayer() {
        navigate(
            R.id.action_navigation_to_fragment_add_player,
            Bundle().apply {
                putSerializable("ids", mAdapter.getUidList())
                putBoolean("isRemove", true)
            })
    }

    private fun calculate() {
        mViewModel.gameRecordDetailResult.value?.apply {
            var total = 0
            mAdapter.items.forEach {
                total += it.score - it.loan
            }
            if (total == 0) {
                toast("积分正确")
            } else {
                toast("积分错误：${total}")
                return@apply
            }

            Strategy01().calculate(this, mAdapter.items)

            // 更新
            mAdapter.items.forEach {
                mViewModel.updatePlayerRecord(it)
            }
            mViewModel.updateGameRecord(this)
        }
    }
}