package com.moonisland.texasholdempoker.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import com.moonisland.texasholdempoker.databinding.DialogSetPlayerScoreBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author yang
 * @date 2024/6/21
 * @desc 输入玩家得分对话框
 */
class SetPlayerScoreDialog : BaseVBDialog<DialogSetPlayerScoreBinding>() {
    companion object {
        fun newInstance(name: String) = SetPlayerScoreDialog().apply {
            arguments = Bundle().apply {
                putString("name", name)
            }
        }
    }

    private val name by lazy { requireArguments().getString("name", "") }

    var call: ((score: Int) -> Boolean)? = null

    @SuppressLint("SetTextI18n")
    override fun initialize() {
        with(mBinding) {
            tvTitle.text = "${name}得分"
            tvCancel.click {
                dismissAllowingStateLoss()
            }
            tvConfirm.click {
                val input = etInput.text.toString()
                if (input.isNotEmpty() && call?.invoke(input.toInt()) == true) {
                    dismissAllowingStateLoss()
                }
            }
        }
    }
}