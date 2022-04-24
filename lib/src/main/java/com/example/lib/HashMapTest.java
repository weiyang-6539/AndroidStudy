package com.example.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    private static final HashMap<String, Object> map = new HashMap<String, Object>() {{
        put("001", "111111111");
        put("003", "333333333");
        put("005", "555555555");
        put("007", "777777777");
    }};

    public void for01() {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            print(key + " - " + value.toString());
        }
    }

    public void for02() {
        for (String key:map.keySet()){
            Object value = map.get(key);

            print(key + " - " + value.toString());
        }
    }

    public void for03(){
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, Object> next = it.next();
            String key = next.getKey();
            Object value = next.getValue();

            print(key + " - " + value.toString());
        }
    }

    public void for04(){
        map.forEach((key, value) -> print(key + " - " + value.toString()));
    }

    private void print(String s) {
        System.out.println(s);
    }
}
