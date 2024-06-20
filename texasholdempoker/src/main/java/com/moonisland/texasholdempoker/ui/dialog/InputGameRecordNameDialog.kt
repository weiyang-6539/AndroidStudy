package com.moonisland.texasholdempoker.ui.dialog

import com.moonisland.texasholdempoker.databinding.DialogInputGameRecordNameBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author yang
 * @date 2024/6/20
 * @desc
 */
class InputGameRecordNameDialog : BaseVBDialog<DialogInputGameRecordNameBinding>() {
    var call: ((name: String) -> Unit)? = null
    override fun getConfig() = Config().apply {
        widthScale = .8f
    }

    override fun initialize() {
        with(mBinding) {
            tvCancel.click { dismissAllowingStateLoss() }
            tvConfirm.click {
                etInput.text.toString().trim().apply {
                    if (this.isNotEmpty()) {
                        call?.invoke(this)
                        dismissAllowingStateLoss()
                    }
                }
            }
        }
    }
}