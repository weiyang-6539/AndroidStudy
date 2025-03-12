package com.example.kline.core;

import java.util.ArrayList;

/**
 * @author Yang
 * @date 2025/3/12
 * @desc
 */
public interface IData {

    int getXSize();

    int getYSize();

    ArrayList<ArrayList<Number>> getLiqData();

    double getMaxLiq();

}
