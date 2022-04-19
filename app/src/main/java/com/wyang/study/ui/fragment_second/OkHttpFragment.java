package com.wyang.study.ui.fragment_second;

import android.widget.TextView;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpFragment extends BaseFragment {
    TextView tv_text;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_example;
    }

    @Override
    protected void initView() {
        tv_text = mRootView.findViewById(R.id.tv_text);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) {
                        return null;
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                getActivity().runOnUiThread(() -> {
                    try {
                        tv_text.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
