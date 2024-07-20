package com.moonisland.texasholdempoker.net

import com.w6539.base_jetpack.net.RetrofitClient

/**
 * @author {USER}
 * @date {DATE}
 * @desc
 */
object DataClient : RetrofitClient() {

    @JvmStatic
    val dataService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getService(DataService::class.java, "https://gitee.com/weiyang6539/")
    }
}