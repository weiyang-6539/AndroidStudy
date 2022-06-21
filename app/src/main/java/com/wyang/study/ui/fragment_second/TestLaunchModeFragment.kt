package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentTestLaunchModeBinding
import com.wyang.study.ui.activity.AActivity
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.ActivityUtils

class TestLaunchModeFragment : BaseFragment<FragmentTestLaunchModeBinding>() {
    override fun getViewBinding(): FragmentTestLaunchModeBinding {
        return FragmentTestLaunchModeBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding?.btnTest?.setOnClickListener {
            ActivityUtils.startActivity(requireContext(), AActivity::class.java)
        }
    }
}