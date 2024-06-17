package com.showmeyourcode.playground.java.code.exercise.recursion;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a method (similar to Xlength()) that counts the number of times a particular character occurs in a string. The string and the particular character are both parameters of the method.
 * For example, countChar( "XaaaYaaaZaaaYaaaaY", 'Y' ) returns 3.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap75/progExercises75.html">Exercise source</a>
 */
@Slf4j
public class CharacterCounterExercise {

    private CharacterCounterExercise() {
    }

    public static void main(String[] args) {
        String testString = "XaaaYaaaZaaaYaaaaY";

        Map<Character, Integer> charCountMap = countEachChar(testString);
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            log.info("Character '{}' appears {} times.", entry.getKey(), entry.getValue());
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
