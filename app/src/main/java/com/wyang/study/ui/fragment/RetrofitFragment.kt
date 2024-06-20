package com.wyang.study.ui.fragment

import android.os.Looper
import com.w6539android.base.base.fragment.BaseFragment
import com.wyang.study.databinding.FragmentExampleBinding
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