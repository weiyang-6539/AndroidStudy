package com.wyang.study.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by fxb on 2020-01-03.
 */
public class AlbumBase implements MultiItemEntity {
    public static final int ALBUM = 0;
    public static final int DATE = 1;

    private String date;

    public AlbumBase(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int getItemType() {
        return DATE;
    }
}
