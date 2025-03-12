package com.example.kline;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class LiqColorInterpolatorUtils {
    private static final ArrayList<Integer> colors = new ArrayList<>() {{
        add(Color.parseColor("#440053"));
        add(Color.parseColor("#3e1c62"));
        add(Color.parseColor("#363872"));
        add(Color.parseColor("#2f5381"));
        add(Color.parseColor("#297090"));
        add(Color.parseColor("#24888a"));
        add(Color.parseColor("#1fa084"));
        add(Color.parseColor("#3bb66d"));
        add(Color.parseColor("#6bc94e"));
        add(Color.parseColor("#afd929"));
        add(Color.parseColor("#f2e805"));
    }};
    private final List<Integer> interpolatedColors = new ArrayList<>();
    private final double maxValue;
    private final double minValue;

    public LiqColorInterpolatorUtils(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        // 你可以根据需要调整步数，步数越多颜色越平滑
        int steps = 30;
        for (int i = 0; i <= steps; i++) {
            double position = (double) i / steps;
            double currentValue = minValue + (maxValue - minValue) * position;
            int color = calculateColor(minValue, maxValue, currentValue);
            if (color != 0) {
                interpolatedColors.add(color);
            }
        }
    }

    public int getColor(double currentValue) {
        // 确保 currentValue 在 [minValue, maxValue] 范围内
        if (currentValue <= minValue) {
            return 0;
        }
        // 处理 currentValue >= maxValue 的情况，直接返回最大颜色
        if (currentValue >= maxValue) {
            return interpolatedColors.get(interpolatedColors.size() - 1);
        }
        // 计算 currentValue 在预先计算好的颜色数组中的位置
        int index = (int) Math.round((currentValue - minValue) / (maxValue - minValue) * (interpolatedColors.size() - 1));
        return interpolatedColors.get(index);
    }

    private static int calculateColor(double minValue, double maxValue, double currentValue) {
        double position = (currentValue - minValue) / (maxValue - minValue);
        int startIndex = (int) Math.floor(position * (colors.size() - 1));
        int endIndex = Math.min(startIndex + 1, colors.size() - 1);
        double startValue = minValue + (maxValue - minValue) * startIndex / (colors.size() - 1);
        double endValue = minValue + (maxValue - minValue) * endIndex / (colors.size() - 1);
        double relativePosition = (currentValue - startValue) / (endValue - startValue);
        int startColor = colors.get(startIndex);
        int endColor = colors.get(endIndex);
        return interpolateColorFrom(startColor, endColor, relativePosition);
    }

    private static int interpolateColorFrom(int fromColor, int toColor, double position) {
        int fromRed = Color.red(fromColor);
        int fromGreen = Color.green(fromColor);
        int fromBlue = Color.blue(fromColor);
        int fromAlpha = Color.alpha(fromColor);
        int toRed = Color.red(toColor);
        int toGreen = Color.green(toColor);
        int toBlue = Color.blue(toColor);
        int toAlpha = Color.alpha(toColor);

        int interpolatedRed = (int) (fromRed + (toRed - fromRed) * position);
        int interpolatedGreen = (int) (fromGreen + (toGreen - fromGreen) * position);
        int interpolatedBlue = (int) (fromBlue + (toBlue - fromBlue) * position);
        int interpolatedAlpha = (int) (fromAlpha + (toAlpha - fromAlpha) * position);

        return Color.argb(interpolatedAlpha, interpolatedRed, interpolatedGreen, interpolatedBlue);
    }
}
