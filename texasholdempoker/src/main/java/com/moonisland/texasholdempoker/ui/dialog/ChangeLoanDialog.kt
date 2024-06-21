package com.moonisland.texasholdempoker.ui.dialog

import android.os.Bundle
import com.moonisland.texasholdempoker.databinding.DialogChangeLoanBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author Yang
 * @date 2024/6/21
 * @desc
 */
class ChangeLoanDialog private constructor() : BaseVBDialog<DialogChangeLoanBinding>() {
    companion object {
        fun newInstance(title: String) = ChangeLoanDialog().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }

    private val title by lazy {
        requireArguments().getString("title", "")
    }

    var call: (() -> Boolean)? = null
    override fun initialize() {
        with(mBinding) {
            tvTitle.text = title
            tvCancel.click { dismissAllowingStateLoss() }
            tvConfirm.click {
                if (call?.invoke() == true) {
                    dismissAllowingStateLoss()
                }
            }
        }
    }
}