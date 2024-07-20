package com.moonisland.texasholdempoker.ui.dialog

import com.moonisland.texasholdempoker.databinding.DialogGameRecordMenuBinding
import com.moonisland.texasholdempoker.ext.click
import com.w6539.base_jetpack.base.dialog.BaseVBDialog

/**
 * @author {USER}
 * @date {DATE}
 * @desc
 */
class GameRecordMenuDialog : BaseVBDialog<DialogGameRecordMenuBinding>() {
    var addPlayerCall: (() -> Unit)? = null
    var removePlayerCall: (() -> Unit)? = null
    var calculateCall: (() -> Unit)? = null
    var deleteCall: (() -> Unit)? = null
    override fun initialize() {
        with(mBinding) {
            btnAddPlayer.click {
                dismissNow()
                addPlayerCall?.invoke()
            }
            btnRemovePlayer.click {
                dismissNow()
                removePlayerCall?.invoke()
            }
            btnCalculate.click {
                dismissNow()
                calculateCall?.invoke()
            }
            btnDelete.click {
                dismissNow()
                deleteCall?.invoke()
            }
        }
    }
}