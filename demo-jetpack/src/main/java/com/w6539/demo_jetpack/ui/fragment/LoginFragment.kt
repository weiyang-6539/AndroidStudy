package com.w6539.demo_jetpack.ui.fragment

import com.gyf.immersionbar.ImmersionBar
import com.w6539.base_jetpack.base.fragment.BaseVBFragment
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.databinding.FragmentLoginBinding

/**
 * @author Yang
 * @since 2022/12/6 13:10
 * @desc 登录页
 */
class LoginFragment : BaseVBFragment<FragmentLoginBinding>() {
    override fun immersionBarEnabled(): Boolean = true

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.transparent)
            .statusBarDarkFont(false, 0.2f)
            .navigationBarDarkIcon(false, 0.2f)
            .autoStatusBarDarkModeEnable(false, 0.2f)
            .autoNavigationBarDarkModeEnable(false, 0.2f)
            .init()
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initialize() {

    }
}