package com.wyang.study.bean;

/**
 * Created by fxb on 2020-01-03.
 */
public class Album extends AlbumBase {
    private final String url;

    public Album(String date, String url) {
        super(date);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int getItemType() {
        return ALBUM;
    }
}
