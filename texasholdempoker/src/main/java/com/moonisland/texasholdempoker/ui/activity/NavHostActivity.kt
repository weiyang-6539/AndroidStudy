package com.moonisland.texasholdempoker.ui.activity

import androidx.navigation.findNavController
import com.moonisland.texasholdempoker.R
import com.moonisland.texasholdempoker.databinding.ActivityNavHostBinding
import com.w6539android.base.base.activity.BaseVBActivity

class NavHostActivity : BaseVBActivity<ActivityNavHostBinding>() {

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }

    override fun initialize() {
    }
}