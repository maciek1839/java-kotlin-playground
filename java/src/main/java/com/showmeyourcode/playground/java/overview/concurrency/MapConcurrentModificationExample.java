package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.HashMap;
import java.util.Map;

public class MapConcurrentModificationExample {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        // Iterating over the map's entry set
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == 2) {
                // Attempting to modify the map while iterating
                map.remove(entry.getKey()); // Throws ConcurrentModificationException
            }
        }
    }
}
