package com.wyang.study.utils;

import android.os.Handler;
import android.os.HandlerThread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadTask extends HandlerThread {
    private Handler handler;

    public Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getLooper());
        }
        return handler;
    }

    public DownloadTask(String name) {
        super(name);
    }

    public DownloadTask(String name, int priority) {
        super(name, priority);
    }

    public void startDownloadTask(String path, File file) {
        start();

        getHandler().post(() -> {
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
                    System.out.println("线程" + (getThreadId() + 1) + "下载长度length = " + length);
                }
            } catch (Exception ex) {
                System.out.println("线程" + (getThreadId() + 1) + "下载出错" + ex);
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
        });
    }
}
