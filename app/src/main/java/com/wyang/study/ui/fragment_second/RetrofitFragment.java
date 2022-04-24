package com.wyang.study.ui.fragment_second;

import android.os.Looper;
import android.os.MessageQueue;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class RetrofitFragment extends BaseFragment {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://hsll6.jiaodaoren.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_example;
    }

    @Override
    protected void initView() {
        retrofit.create(ApiService.class)
                .getBaidu()
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            System.out.println("当前在主线程");
                        } else {
                            System.out.println("当前不在主线程");
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        System.out.println(t);
                    }
                });

        Looper.myQueue().addIdleHandler(() -> {
            System.out.println("空闲~~~~~~~~~~");
            return true;
        });
    }

    public interface ApiService {

        @POST("api_users/user_accounts/register")
        Call<Object> getBaidu();
    }
}
