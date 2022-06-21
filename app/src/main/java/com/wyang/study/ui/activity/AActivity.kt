package com.wyang.study.ui.activity

import com.wyang.study.databinding.ActivityExampleBinding
import com.wyang.study.ui.base.BaseActivity
import com.wyang.study.ui.util.ActivityUtils

class AActivity : BaseActivity<ActivityExampleBinding>() {
    override fun getViewBinding(): ActivityExampleBinding {
        return ActivityExampleBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding?.tvContent?.text = "Activity_AAAAA"

        mBinding?.tvContent?.setOnClickListener {
            ActivityUtils.startActivity(this, BActivity::class.java)
        }
    }
}