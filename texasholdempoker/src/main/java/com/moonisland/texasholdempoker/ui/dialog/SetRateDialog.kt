package com.moonisland.texasholdempoker.ui.dialog

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.children
import com.chad.library.adapter4.util.getItemView
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.databinding.DialogChangeRateBinding
import com.moonisland.texasholdempoker.ext.click
import com.moonisland.texasholdempoker.ext.formatFloat
import com.moonisland.texasholdempoker.global.TexasConfig
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author yang
 * @date 2024/6/21
 * @desc
 */
class SetRateDialog : BaseVBDialog<DialogChangeRateBinding>() {
    private var rate = TexasConfig.INIT_RATE
    private val list = mutableListOf(
        0.05f, 0.10f, 0.15f, 0.20f, 0.25f, 0.30f, 0.35f, 0.40f, 0.45f, 0.50f
    )

    var call: ((rate: Float) -> Boolean)? = null
    override fun initialize() {
        with(mBinding) {

            list.forEach {
                // 倒序添加
                flexContainer.addView(
                    flexContainer.getItemView(R.layout.item_rate_single_view).apply {
                        val format = it.formatFloat()
                        (this as AppCompatTextView).text = format
                        this.tag = format
                        this.click(100) {
                            rate = it
                            selected(format)
                        }
                    }, 0
                )
            }
            tvCancel.click {
                dismissAllowingStateLoss()
            }
            tvConfirm.click {
                if (call?.invoke(rate) == true) {
                    dismissAllowingStateLoss()
                }
            }
        }
    }

    private fun selected(rate: String) {
        mBinding.flexContainer.children.forEach {
            it.isSelected = it.tag == rate
        }
    }
}