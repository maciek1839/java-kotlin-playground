package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ListConcurrentModificationExample {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // Iterating over the list
        for (String name : names) {
            if (name.equals("Bob")) {
                // Attempting to modify the list (remove or add) while iterating results in ConcurrentModificationException
                names.remove(name); // Throws ConcurrentModificationException
                names.add("New bob");
            }
        }
    }
}
