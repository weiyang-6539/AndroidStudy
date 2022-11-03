package com.wyang.study.ui;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoadIntentService extends IntentService {

    public DownLoadIntentService() {
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String path = intent.getStringExtra("path");

            File file = new File(this.getFilesDir(), "wechat2.apk");

            InputStream is = null;
            FileOutputStream fos = null;
            try {
                URL url = new URL(path);
                is = url.openStream();
                fos = new FileOutputStream(file);

                byte[] buffer = new byte[2048];
                int len = 0;
                int length = 0;
                while ((len = is.read(buffer)) > 0) {
                    fos.write(buffer);
                    length += len;
                    //System.out.println("线程" + (Thread.currentThread().getId() + 1) + "下载长度length = " + length);
                }
            } catch (Exception ex) {
                System.out.println("线程" + (Thread.currentThread().getId() + 1) + "下载出错" + ex);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
        executor.shutdown();
    }

    private ExecutorService executor;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Runtime.getRuntime().exec("cat /sys/class/gpio/gpio68/value");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(this);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("DownLoadService销毁");
    }
}
