package com.example.aidl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_adil;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && TextUtils.equals(Constant.AIDL_ACTION, intent.getAction())) {

                String message = intent.getStringExtra("message");

                tv_adil.setText(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_adil = findViewById(R.id.tv_aidl);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.AIDL_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}