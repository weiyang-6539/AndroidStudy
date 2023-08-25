package com.wyang.study.ui.fragment

import android.util.Log
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentAlgorithmBinding
import com.wyang.study.encrypt.RC4Util
import com.wyang.study.utils.LogUtils
import java.security.Security

class AlgorithmFragment : BaseFragment<FragmentAlgorithmBinding>() {
    override fun getViewBinding() = FragmentAlgorithmBinding.inflate(layoutInflater)

    override fun initialize() {
        mBinding.btnRc4.setOnClickListener { onClickTest() }
        mBinding.btnPrintAlgorithm.setOnClickListener { printAlgorithm() }
    }

    private fun onClickTest() {
        var key = RC4Util.getKey()
        LogUtils.e("key:", key)
        key = "naPrrmz_OjTg_ZJH"
        testRc4(key, "The world have three things, sun moon and you.")
        //testRc4(key, " 山有木兮木有枝，心悦君兮君不知。");
    }

    private fun testRc4(key: String, text: String) {
        LogUtils.e("encrypt_text:", RC4Util.rc4_trans(key, text))
        LogUtils.e("encrypt_text:", RC4Util.HloveyRC4(text, key))
        LogUtils.e("encrypt_text:", RC4Util.arc4(text, key))

        //String decrypt = RC4Util.rc4_trans(key, encrypt);
        //LogUtils.e("decrypt_text:", decrypt);
    }


    private fun printAlgorithm() {
        val providers = Security.getProviders()
        for (provider in providers) {
            Log.i("CRYPTO", "provider: " + provider.name)
            val services = provider.services
            for (service in services) {
                Log.i("CRYPTO", " algorithm: " + service.algorithm)
            }
        }
    }
}