package com.example.kline.core;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.Bitmap;

import com.example.kline.LiqColorInterpolatorUtils;

import java.util.ArrayList;

/**
 * @author Yang
 * @date 2025/3/12
 * @desc
 */
public class KLineAdapter {
    protected final DataSetObservable mDataSetObservable = new DataSetObservable();

    private IData data;
    private Bitmap bitmap;

    public IData getData() {
        return data;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setData(IData data) {
        this.data = data;
        this.bitmap = buildBitmap();
        notifyDataSetChanged();
    }

    public float getRatio() {
        return 1f * data.getXSize() / data.getYSize();
    }

    private Bitmap buildBitmap() {
        int width = data.getXSize();
        int height = data.getYSize();
        LiqColorInterpolatorUtils utils = new LiqColorInterpolatorUtils(0, data.getMaxLiq() * .9f);
        int[] intArr = new int[width * height];
        data.getLiqData().forEach((it) -> {
            int x = it.get(0).intValue();
            int y = it.get(1).intValue();
            double value = it.get(2).doubleValue();
            intArr[x + y * width] = utils.getColor(value);
        });

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(intArr, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 注册一个数据观察者
     *
     * @param observer 数据观察者
     */
    void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    /**
     * 移除一个数据观察者
     *
     * @param observer 数据观察者
     */
    void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }
}
