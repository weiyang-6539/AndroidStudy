package com.wyang.study.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by *** on 2019/8/2.
 * 封装Gson解析通用用法
 */
public class GSONUtils {

    private GSONUtils() {
    }

    /**
     * 转成json
     */
    public static String bean2Json(Object object) {
        return new Gson().toJson(object);
    }

    /**
     * 转成bean
     */
    public static <T> T json2Bean(String string, Class<T> cls) {
        T t = new Gson().fromJson(string, cls);
        return t;
    }

    /**
     * 转成list
     * 解决泛型问题
     */
    public static <T> List<T> json2List(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        try {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        } catch (Exception ignored) {
        }
        return list;
    }

    public static <T> List<T> json2List1(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        try {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        } catch (Exception ignored) {
        }
        return list;
    }

    /**
     * 转成list中有map的
     */
    public static <T> List<Map<String, T>> json2ListMaps(String string) {
        List<Map<String, T>> list;
        list = new Gson().fromJson(string,
                new TypeToken<List<Map<String, T>>>() {
                }.getType());
        return list;
    }

    /**
     * 转成map的
     */
    public static <T> Map<String, T> json2Maps(String string) {
        Map<String, T> map;
        map = new Gson().fromJson(string, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }
}
