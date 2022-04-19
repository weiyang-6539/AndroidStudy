package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("------远程服务RemoteService创建------");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("------远程服务RemoteService销毁------");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("------远程服务RemoteService解绑------");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("------远程服务RemoteService绑定------");
        return new MyBinder();
    }

    public void remotePrint(String str) {
        Intent intent = new Intent();
        intent.putExtra("message", str);
        intent.setAction(Constant.AIDL_ACTION);
        sendBroadcast(intent);
    }

    public class MyBinder extends RemoteInterface.Stub {

        @Override
        public void remotePrintInterface(String str) {
            remotePrint(str);
        }

        @Override
        public void remotePrintMap(Map map) {
            remotePrint(new Gson().toJson(map));
        }

        @Override
        public void remotePrintStringBuilder(CharSequence c) {
            remotePrint(c.toString());
        }
    }
}
