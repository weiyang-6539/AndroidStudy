package com.wyang.study.ui.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import com.example.aidl.RemoteInterface
import com.wyang.study.databinding.FragmentAidlBinding
import com.wyang.study.ui.base.BaseFragment

class AidlFragment : BaseFragment<FragmentAidlBinding>() {
    private val aidl_pkg = "com.example.aidl"

    //绑定服务时的连接对象
    private var conn: ServiceConnection? = null

    //通过aidl创建的接口类的对象
    private var ri: RemoteInterface? = null

    override fun getViewBinding(): FragmentAidlBinding {
        return FragmentAidlBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.btnStart.setOnClickListener { start() }
        mBinding.btnStop.setOnClickListener { stop() }
        mBinding.btnBind.setOnClickListener { bind() }
        mBinding.btnUnbind.setOnClickListener { unbind() }
        mBinding.btnCall.setOnClickListener { callRemotePrint() }
    }

    private fun start() {
        val intent = Intent()
        //设置意图对象要匹配的action
        intent.action = "com.example.aidl"
        //android 5.0之后
        //设置包名，这里测试了一下，必须是服务所在的包名才可以
        intent.setPackage(aidl_pkg)
        requireActivity().startService(intent)
    }

    private fun stop() {
        val intent = Intent()
        intent.action = "com.example.aidl"
        intent.setPackage(aidl_pkg)
        requireActivity().stopService(intent)
    }

    //绑定服务
    private fun bind() {
        val intent = Intent()
        intent.action = "com.example.aidl"
        intent.setPackage(aidl_pkg)
        if (conn == null) {
            //创建连接服务的对象
            conn = RemoteServiceConnection()
        }
        requireActivity().bindService(intent, conn!!, Context.BIND_AUTO_CREATE)
    }

    private fun unbind() {
        if (conn != null) {
            requireActivity().unbindService(conn!!)
            conn = null
        }
    }

    //调用服务的方法
    private fun callRemotePrint() {
        Log.d(mTag, "callRemotePrint........................")
        if (ri != null) {
            try {
                //通过调用接口对象的remotePrintInterface方法，简介调用服务的remotePrint方法
                //ri.remotePrintInterface(et_text.getText().toString());
                val builder = StringBuilder()
                    .append("2212121")
                    .append("aaaa")
                    .append("bbbb")
                ri?.remotePrintStringBuilder(builder)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } else {
            Toast.makeText(context, "remote interface is null", Toast.LENGTH_SHORT).show()
        }
    }

    inner class RemoteServiceConnection : ServiceConnection {
        //当使用bindService方法绑定服务，并且服务返回IBinder对象的时候会调用
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.d(mTag, "onServiceConnected........................")
            //强转为接口对象
            ri = RemoteInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

    override fun onDestroy() {
        super.onDestroy()
        if (conn != null) {
            requireActivity().unbindService(conn!!)
            conn = null
        }
    }
}