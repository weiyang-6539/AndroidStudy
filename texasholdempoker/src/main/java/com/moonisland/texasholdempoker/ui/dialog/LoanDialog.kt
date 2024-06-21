package com.moonisland.texasholdempoker.ui.dialog

import com.moonisland.texasholdempoker.databinding.DialogLoanBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class LoanDialog : BaseVBDialog<DialogLoanBinding>() {

    var call: (() -> Unit)? = null
    override fun initialize() {
        with(mBinding) {
            tvCancel.click { dismissAllowingStateLoss() }
            tvConfirm.click {

            }
        }
    }
}