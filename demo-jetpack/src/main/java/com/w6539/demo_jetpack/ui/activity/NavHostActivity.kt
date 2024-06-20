package com.w6539.demo_jetpack.ui.activity

import androidx.navigation.findNavController
import com.w6539.base_jetpack.base.activity.BaseVBActivity
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.databinding.ActivityNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : BaseVBActivity<ActivityNavHostBinding>() {

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }

    override fun initialize() {
    }

}