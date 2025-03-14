package com.example.kline.bean;

import com.example.kline.core.IData;

import java.util.ArrayList;

/**
 * @author yang
 * @date 2025/3/11
 * @desc
 */
public class KData implements IData {
    public ArrayList<Double> y;
    public ArrayList<ArrayList<Number>> liq;
    public ArrayList<ArrayList<Number>> prices;
    public double maxLiqValue;

    public KData() {
    }

    public KData(ArrayList<Double> y, ArrayList<ArrayList<Number>> liq, ArrayList<ArrayList<Number>> prices, double maxLiqValue) {
        this.y = y;
        this.liq = liq;
        this.prices = prices;
        this.maxLiqValue = maxLiqValue;
    }

    @Override
    public int getXSize() {
        return prices.size();
    }

    @Override
    public int getYSize() {
        return y.size();
    }

    @Override
    public ArrayList<ArrayList<Number>> getLiqData() {
        return liq;
    }

    @Override
    public ArrayList<ArrayList<Number>> getXData() {
        return prices;
    }

    @Override
    public ArrayList<Double> getYData() {
        return y;
    }

    @Override
    public double getMaxLiq() {
        return maxLiqValue;
    }
}
