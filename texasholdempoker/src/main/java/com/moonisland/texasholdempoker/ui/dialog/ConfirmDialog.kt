package com.moonisland.texasholdempoker.ui.dialog

import android.os.Bundle
import com.moonisland.texasholdempoker.databinding.DialogConfirmBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class ConfirmDialog private constructor() : BaseVBDialog<DialogConfirmBinding>() {
    companion object {
        fun newInstance(prompt: String) = ConfirmDialog().apply {
            arguments = Bundle().apply {
                putString("prompt", prompt)
            }
        }
    }

    private val prompt by lazy {
        requireArguments().getString("prompt", "")
    }

    var confirmCall: (() -> Boolean)? = null
    override fun initialize() {
        with(mBinding) {
            tvPrompt.text = prompt
            tvCancel.click { dismissAllowingStateLoss() }
            tvConfirm.click {
                if (confirmCall?.invoke() == true) {
                    dismissAllowingStateLoss()
                }
            }
        }
    }
}