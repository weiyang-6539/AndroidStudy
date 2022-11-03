package com.wyang.study.ui.fragment_second

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyang.study.R
import com.wyang.study.databinding.FragmentExampleBinding
import com.wyang.study.ui.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class RetrofitFragment : BaseFragment<FragmentExampleBinding>() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hsll6.jiaodaoren.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getViewBinding(): FragmentExampleBinding {
        return FragmentExampleBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        retrofit.create(ApiService::class.java)
            .baidu
            .enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        println("当前在主线程")
                    } else {
                        println("当前不在主线程")
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    println(t)
                }
            })
    }

    interface ApiService {
        @get:POST("api_users/user_accounts/register")
        val baidu: Call<Any?>
    }
}