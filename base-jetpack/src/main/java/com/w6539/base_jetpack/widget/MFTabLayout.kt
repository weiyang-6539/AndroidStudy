package com.w6539.base_jetpack.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

/**
 * @author Yang
 * @since 2022/8/25 11:46
 * @desc 拦截某一个tab的选择
 */
class MFTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : TabLayout(context, attrs) {

    var funcInterceptTab: (tab: Tab?) -> Boolean = { false }

    override fun selectTab(tab: Tab?) {
        if (funcInterceptTab(tab)) {
            return
        }
        super.selectTab(tab)
    }
}