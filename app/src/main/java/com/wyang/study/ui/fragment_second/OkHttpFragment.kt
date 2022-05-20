package com.wyang.study.ui.fragment_second

import com.wyang.study.databinding.FragmentExampleBinding
import com.wyang.study.ui.base.BaseFragment
import okhttp3.*
import java.io.IOException

class OkHttpFragment : BaseFragment<FragmentExampleBinding>() {
    override fun getViewBinding(): FragmentExampleBinding {
        return FragmentExampleBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .build()

        val request: Request = Request.Builder()
            .url("http://www.baidu.com")
            .build()

        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                requireActivity().runOnUiThread {
                    try {
                        mBinding?.tvText?.text = response.body!!.string()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}