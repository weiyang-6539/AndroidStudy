package com.moonisland.texasholdempoker.ui.fragment

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.adapter.PlayerRecordAdapter
import com.moonisland.texasholdempoker.databinding.FragmentRecordDetailBinding
import com.moonisland.texasholdempoker.db.entity.GameRecord
import com.moonisland.texasholdempoker.ext.formatRecordStatus
import com.moonisland.texasholdempoker.mvvm.vm.RecordViewModel
import com.moonisland.texasholdempoker.ui.dialog.LoanDialog
import com.w6539.base_jetpack.base.fragment.BaseVMFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Yang
 * @date 2024/6/20
 * @desc
 */
@AndroidEntryPoint
class RecordDetailFragment : BaseVMFragment<RecordViewModel, FragmentRecordDetailBinding>() {
    private val id by lazy { requireArguments().getLong("id") }

    private val mHeaderAdapter = object : BaseSingleItemAdapter<GameRecord, QuickViewHolder>() {
        override fun onBindViewHolder(holder: QuickViewHolder, item: GameRecord?) {
            item?.apply {
                holder.setText(R.id.tv_name, "对局：${item.name}")
                    .setText(R.id.tv_player, "人数：${item.playerIds.size}")
                    .setText(R.id.tv_score, "积分：${item.score}")
                    .setText(R.id.tv_status, "状态：${formatRecordStatus(item.status)}")
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

        with(mAdapter) {
            addOnItemChildClickListener(R.id.btn_loan) { _, _, position ->
                getItem(position)?.apply {
                    LoanDialog()

                        .show(childFragmentManager, "loan")
                }
            }
        }
        mViewModel.queryGameRecordById(id)
    }

    override fun startObserver() {
        mViewModel.gameRecordDetailResult.observe(this) {
            mHeaderAdapter.setItem(it, null)
        }
        mViewModel.playerRecordsResult.observe(this) {
            mAdapter.submitList(it)
            mBinding.mRecyclerView.adapter = ConcatAdapter().apply {
                addAdapter(mHeaderAdapter)
                addAdapter(mAdapter)
            }
        }
    }
}