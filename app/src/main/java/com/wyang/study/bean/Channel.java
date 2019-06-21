package com.wyang.study.bean;

/**
 * Created by weiyang on 2019/6/20.
 * 仿今日头条,频道bean
 */
public class Channel {
    private String name;
    private boolean isActivated;
    private boolean isMine;

    public Channel(String name, boolean isActivated, boolean isMine) {
        this.name = name;
        this.isActivated = isActivated;
        this.isMine = isMine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
