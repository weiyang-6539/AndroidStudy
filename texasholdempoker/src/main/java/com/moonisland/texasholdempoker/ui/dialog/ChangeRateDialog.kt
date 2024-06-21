package com.moonisland.texasholdempoker.ui.dialog

import android.annotation.SuppressLint
import com.moonisland.texasholdempoker.databinding.DialogChangeRateBinding
import com.moonisland.texasholdempoker.ext.click
import com.moonisland.texasholdempoker.global.TexasConfig
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author yang
 * @date 2024/6/21
 * @desc
 */
class ChangeRateDialog : BaseVBDialog<DialogChangeRateBinding>() {

    private var rate = TexasConfig.INIT_RATE

    var call: ((rate: Float) -> Boolean)? = null
    override fun initialize() {
        with(mBinding) {
            setTitleView()

            btnReduce.click {
                rate -= .05f
                setTitleView()
            }
            btnAdd.click {
                rate += .05f
                setTitleView()
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

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun setTitleView() {
        mBinding.tvTitle.text = "当前倍率${String.format("%.2f", rate)}, 一旦确定不能修改"
    }
}