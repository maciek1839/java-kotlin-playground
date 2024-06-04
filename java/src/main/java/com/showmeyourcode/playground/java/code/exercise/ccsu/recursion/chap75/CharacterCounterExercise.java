package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;

import java.util.HashMap;
import java.util.Map;

public class CharacterCounterExercise {
    public static void main(String[] args) {
        String testString = "XaaaYaaaZaaaYaaaaY";

        Map<Character, Integer> charCountMap = countEachChar(testString);
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println("Character '" + entry.getKey() + "' appears " + entry.getValue() + " times.");
        }
    }

    public static Map<Character, Integer> countEachChar(String str) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        countEachCharHelper(str, 0, charCountMap);
        return charCountMap;
    }

    private static void countEachCharHelper(String str, int index, Map<Character, Integer> charCountMap) {
        if (index == str.length()) {
            return; // Base case: end of string
        }

        char currentChar = str.charAt(index);
        charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

        countEachCharHelper(str, index + 1, charCountMap);
    }
}
