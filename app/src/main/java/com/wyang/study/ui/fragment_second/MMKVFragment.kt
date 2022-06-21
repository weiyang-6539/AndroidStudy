package com.wyang.study.ui.fragment_second

import com.tencent.mmkv.MMKV
import com.wyang.study.databinding.FragmentExampleBinding
import com.wyang.study.ui.base.BaseFragment

class MMKVFragment: BaseFragment<FragmentExampleBinding>() {
    override fun getViewBinding(): FragmentExampleBinding {
        return FragmentExampleBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        //获取实例
        val mmkv = MMKV.defaultMMKV()
        val mmkvWithID = MMKV.mmkvWithID("USER")
        val mmkvWithID1 = MMKV.mmkvWithID("USER", MMKV.MULTI_PROCESS_MODE)

        mmkv.encode("phone","1234567890")
        val decodeInt = mmkv.decodeInt("phone")

        val string = mmkv.decodeString("phone")

        log("decodeInt:$decodeInt")

        log("string:$string")

        mmkv.encode("isLogin",true)

        val bool = mmkv.decodeBool("isLogin")
        log("isLogin:$bool")

        val bool1 = mmkv.decodeBool("phone")
        log("bool1:$bool1")
    }
}