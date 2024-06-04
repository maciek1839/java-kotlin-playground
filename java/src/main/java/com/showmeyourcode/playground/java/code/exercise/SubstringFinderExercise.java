package com.showmeyourcode.playground.java.code.exercise;

import java.util.ArrayList;
import java.util.List;

public class SubstringFinderExercise {

    public int findSubstring(String str, String substr) {
        if (str == null || substr == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        return str.indexOf(substr);
    }

    public List<Integer> findAllSubstrings(String str, String substr) {
        if (str == null || substr == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        List<Integer> indexes = new ArrayList<>();
        if (substr.length() == 0) {
            for (int i = 0; i <= str.length(); i++) {
                indexes.add(i);
            }
            return indexes;
        }
        if (str.length() == 0 || str.length() < substr.length()) {
            return indexes;
        }

        for (int i = 0; i <= str.length() - substr.length(); i++) {
            int j;
            for (j = 0; j < substr.length(); j++) {
                if (str.charAt(i + j) != substr.charAt(j)) {
                    break;
                }
            }
            if (j == substr.length()) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        SubstringFinderExercise finder = new SubstringFinderExercise();

        String str = "Hello, world!";
        String substr = "world";
        System.out.println("First occurrence of '" + substr + "' in '" + str + "' is at index: " + finder.findSubstring(str, substr)); // Output: 7

        substr = "Java";
        System.out.println("First occurrence of '" + substr + "' in '" + str + "' is at index: " + finder.findSubstring(str, substr)); // Output: -1

        String str2 = "Hello, world! Hello again!";
        String substr2 = "Hello";
        System.out.println("All occurrences of '" + substr2 + "' in '" + str + "' are at indexes: " + finder.findAllSubstrings(str2, substr2)); // Output: [0, 14]

        substr2 = "Java";
        System.out.println("All occurrences of '" + substr2 + "' in '" + str + "' are at indexes: " + finder.findAllSubstrings(str2, substr2)); // Output: []
    }
}
