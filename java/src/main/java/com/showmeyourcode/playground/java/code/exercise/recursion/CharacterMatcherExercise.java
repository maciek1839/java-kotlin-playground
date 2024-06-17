package com.showmeyourcode.playground.java.code.exercise.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * Write a program that implements string matching,
 * except now the character '?' in either string matches any single character at the same position in the other string.
 * If both strings have '?' in the same position, the characters match each other.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap75/progExercises75.html">Exercise source</a>
 */
@Slf4j
public class CharacterMatcherExercise {

    private CharacterMatcherExercise() {
    }

    public static boolean matches(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false; // Strings must be of the same length to match
        }
        return matchesRecursive(str1, str2, 0);
    }

    private static boolean matchesRecursive(String str1, String str2, int index) {
        // Base case: If we've reached the end of the strings, they match
        if (index == str1.length()) {
            return true;
        }

        char ch1 = str1.charAt(index);
        char ch2 = str2.charAt(index);

        // Check if characters match or if either character is '?'
        if (ch1 != '?' && ch2 != '?' && ch1 != ch2) {
            return false;
        }

        // Move to the next character
        return matchesRecursive(str1, str2, index + 1);
    }
}
