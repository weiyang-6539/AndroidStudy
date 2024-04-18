package com.wyang.study.ui;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
                int len;
                int length = 0;
                while ((len = is.read(buffer)) > 0) {
                    fos.write(buffer);
                    length += len;
                    System.out.println("线程" + (Thread.currentThread().getId() + 1) + "下载长度length = " + length);
                }
            } catch (Exception ex) {
                System.out.println("线程" + (Thread.currentThread().getId() + 1) + "下载出错" + ex);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ignored) {
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("DownLoadService销毁");
    }
}
