package com.example.kline.utils;

import com.example.kline.bean.KData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * @author Yang
 * @date 2025/3/12
 * @desc
 */
public class JsonUtil {
    static double maxLiqValue = 0.0;

    public static KData parse(String dataJson) {
        try {
            JsonObject jsonObject = JsonParser.parseString(dataJson).getAsJsonObject();

            ArrayList<Double> y = new ArrayList<>();
            ArrayList<ArrayList<Number>> liq = new ArrayList<>();
            ArrayList<ArrayList<String>> prices = new ArrayList<>();
            jsonObject.getAsJsonArray("y").forEach((it) -> y.add(it.getAsDouble()));
            jsonObject.getAsJsonArray("liq").forEach((it) -> {
                ArrayList<Number> ls = new ArrayList<>();
                it.getAsJsonArray().forEach((v) -> ls.add(v.getAsNumber()));
                maxLiqValue = Math.max(maxLiqValue, ls.get(2).doubleValue());
                liq.add(ls);
            });
            jsonObject.getAsJsonArray("prices").forEach((it) -> {
                ArrayList<String> ls = new ArrayList<>();
                it.getAsJsonArray().forEach((v) -> ls.add(v.getAsString()));
                prices.add(ls);
            });

            return new KData(y, liq, prices, maxLiqValue);
        } catch (Exception ignored) {
        }
        return new KData();
    }
}
