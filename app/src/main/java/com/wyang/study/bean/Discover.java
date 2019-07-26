package com.wyang.study.bean;

import java.util.List;

/**
 * Created by weiyang on 2019/6/27.
 */
public class Discover {
    private String nickname;
    private String avatarUrl;
    private String content;
    private List<String> urls;

    public Discover(String nickname, String avatarUrl, String content, List<String> urls) {
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.content = content;
        this.urls = urls;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
