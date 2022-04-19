package com.example.lib;

public class Banana implements Fruit{
    @Override
    public String getName() {
        return "banana";
    }

    @Override
    public float getWeight() {
        return 1.5f;
    }
}
