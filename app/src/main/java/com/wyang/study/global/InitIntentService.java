package com.wyang.study.global;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.wyang.study.utils.LogUtils;

public class InitIntentService extends IntentService {
    private static final String ACTION = "init_library";

    public static void start(Context context) {
        Intent intent = new Intent(context, InitIntentService.class);
        intent.setAction(ACTION);
        context.startService(intent);
    }

    public InitIntentService() {
        super("AsyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //第三方库的初始化等一系列初始化操作


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("InitIntentService", "服务销毁~");
    }
}
