package com.wyang.study.ui.fragment

import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentTestNineBinding

/**
 * @author Yang
 * @date 2025/2/18
 * @desc
 */
class TestNineFragment : BaseFragment<FragmentTestNineBinding>() {
    private val str = "Mほ Gてd! ゐ ばsed ヵワせs fせltツ tつ brせng へp mほ fぁvはrぜte cてrnツ. "

    override fun initialize() {
        with(mBinding) {
            tvTest.text = str
        }
    }
}