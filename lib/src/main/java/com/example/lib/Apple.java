package com.example.lib;

import java.util.List;

public class Apple implements Fruit {
    @Override
    public String getName() {
        return "apple";
    }

    @Override
    public float getWeight() {
        return 1f;
    }

    public void addThis(List<? super Apple> list) {
        list.add(this);
    }
}
