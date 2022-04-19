package com.example.lib;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    private static List<Fruit> list = new ArrayList<Fruit>() {{
        add(new Apple());
        add(new Apple());
        add(new Banana());
        add(new Apple());
    }};

    public static void main(String[] args) {

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Banana());

        float weight = getTotalWeight(fruits);

        getTotalWeight(new ArrayList<Apple>());

        new Apple().addThis(fruits);

        test(new ArrayList<>());

        System.out.println("");
    }

    public static float getTotalWeight(List<? extends Fruit> list) {
        float w = 0;
        for (Fruit fruit : list) {
            w += fruit.getWeight();
        }
        return w;
    }

    public static void test(List<? extends Object> list) {
    }
}