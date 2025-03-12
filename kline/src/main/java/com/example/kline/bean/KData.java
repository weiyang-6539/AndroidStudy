package com.example.kline.bean;

import java.util.ArrayList;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class KData {
    public ArrayList<Double> y;
    public ArrayList<ArrayList<Number>> liq;
    public ArrayList<ArrayList<String>> prices;
    public double maxLiqValue;

    public KData() {
    }

    public KData(ArrayList<Double> y, ArrayList<ArrayList<Number>> liq, ArrayList<ArrayList<String>> prices, double maxLiqValue) {
        this.y = y;
        this.liq = liq;
        this.prices = prices;
        this.maxLiqValue = maxLiqValue;
    }
}
