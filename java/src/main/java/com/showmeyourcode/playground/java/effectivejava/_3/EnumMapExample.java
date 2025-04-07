package com.showmeyourcode.playground.java.effectivejava._3;

import java.util.EnumMap;

public class EnumMapExample {

    public static void main(String[] args) {
        EnumMap<EnumInstanceField.Ensemble, String> descriptions = new EnumMap<>(EnumInstanceField.Ensemble.class);

        descriptions.put(EnumInstanceField.Ensemble.SOLO, "A single performer");
        descriptions.put(EnumInstanceField.Ensemble.DUET, "Two musicians");
        descriptions.put(EnumInstanceField.Ensemble.TRIO, "Three musicians");
        descriptions.put(EnumInstanceField.Ensemble.QUARTET, "Four musicians");
        // ... and so on

        // Example usage
        EnumInstanceField.Ensemble group = EnumInstanceField.Ensemble.QUARTET;
        System.out.println(group + " has " + group.numberOfMusicians() + " musicians.");
        System.out.println("Description: " + descriptions.get(group));
    }
}
