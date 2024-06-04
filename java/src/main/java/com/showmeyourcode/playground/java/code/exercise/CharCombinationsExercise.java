package com.showmeyourcode.playground.java.code.exercise;

import java.util.ArrayList;
import java.util.List;

public class CharCombinationsExercise {

    // Method to generate all combinations
    public static List<String> generateCombinations(char[] chars) {
        List<String> result = new ArrayList<>();
        generateCombinationsHelper(chars, 0, new StringBuilder(), result);
        return result;
    }

    // Helper method for recursion
    private static void generateCombinationsHelper(char[] chars, int start, StringBuilder current, List<String> result) {
        for (int i = start; i < chars.length; i++) {
            current.append(chars[i]);
            result.add(current.toString());
            generateCombinationsHelper(chars, i + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        List<String> combinations = generateCombinations(chars);

        System.out.println("All possible combinations:");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}
