package com.wyang.study.bean;

import java.io.Serializable;

/**
 * Created by weiyang on 2019/6/19.
 */
public class Simple implements Serializable {
    private String title;
    private String description;
    private String className;

    public Simple(String title, String description, String className) {
        this.title = title;
        this.description = description;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
