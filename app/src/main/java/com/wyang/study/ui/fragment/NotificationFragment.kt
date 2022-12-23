package com.wyang.study.ui.fragment

import com.wyang.study.databinding.FragmentNotificationBinding
import com.wyang.study.ui.base.BaseFragment

class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    override fun getViewBinding(): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(layoutInflater)
    }

    override fun initialize() {

    }
}