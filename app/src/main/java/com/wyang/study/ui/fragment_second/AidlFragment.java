package com.wyang.study.ui.fragment_second;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.aidl.RemoteInterface;
import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

public class AidlFragment extends BaseFragment {
    private Button btn_start;
    private Button btn_stop;
    private Button btn_bind;
    private Button btn_unbind;
    private Button btn_call;
    private AppCompatEditText et_text;

    private String aidl_pkg = "com.example.aidl";

    //绑定服务时的连接对象
    private ServiceConnection conn = null;
    //通过aidl创建的接口类的对象
    private RemoteInterface ri = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_aidl;
    }

    @Override
    protected void initView() {
        btn_start = mRootView.findViewById(R.id.btn_start);
        btn_stop = mRootView.findViewById(R.id.btn_stop);
        btn_bind = mRootView.findViewById(R.id.btn_bind);
        btn_unbind = mRootView.findViewById(R.id.btn_unbind);
        btn_call = mRootView.findViewById(R.id.btn_call);
        et_text = mRootView.findViewById(R.id.et_text);

        btn_start.setOnClickListener(v -> start());
        btn_stop.setOnClickListener(v -> stop());
        btn_bind.setOnClickListener(v -> bind());
        btn_unbind.setOnClickListener(v -> unbind());
        btn_call.setOnClickListener(v -> callRemotePrint());
    }

    public void start() {
        Intent intent = new Intent();
        //设置意图对象要匹配的action
        intent.setAction("com.example.aidl");
        //android 5.0之后
        //设置包名，这里测试了一下，必须是服务所在的包名才可以
        intent.setPackage(aidl_pkg);

        getActivity().startService(intent);
    }

    public void stop() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidl");
        intent.setPackage(aidl_pkg);

        getActivity().stopService(intent);
    }

    //绑定服务
    public void bind() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidl");
        intent.setPackage(aidl_pkg);

        if (conn == null) {
            //创建连接服务的对象
            conn = new RemoteServiceConnection();
        }
        getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void unbind() {
        if (conn != null) {
            getActivity().unbindService(conn);

            conn = null;
        }
    }

    //调用服务的方法
    public void callRemotePrint() {
        Log.d(TAG, "callRemotePrint........................");

        if (ri != null) {
            try {
                //通过调用接口对象的remotePrintInterface方法，简介调用服务的remotePrint方法
                //ri.remotePrintInterface(et_text.getText().toString());

                StringBuilder builder = new StringBuilder()
                        .append("2212121")
                        .append("aaaa")
                        .append("bbbb");
                ri.remotePrintStringBuilder(builder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getContext(), "remote interface is null", Toast.LENGTH_SHORT).show();
        }
    }

    class RemoteServiceConnection implements ServiceConnection {
        //当使用bindService方法绑定服务，并且服务返回IBinder对象的时候会调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected........................");
            //强转为接口对象
            ri = RemoteInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            getActivity().unbindService(conn);
            conn = null;
        }
    }
}
