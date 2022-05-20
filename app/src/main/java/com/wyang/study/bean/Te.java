package com.wyang.study.bean;

import com.google.gson.annotations.SerializedName;

public class Te {
    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private Object data;
}
