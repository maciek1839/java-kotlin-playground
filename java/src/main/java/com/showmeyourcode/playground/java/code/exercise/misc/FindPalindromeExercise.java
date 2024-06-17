package com.showmeyourcode.playground.java.code.exercise.misc;

/**
 * A palindrome is a sequence of characters that reads the same forward and backward.
 * This means that the order of characters is symmetric around its center, so it appears unchanged when reversed.
 *
 */
public class FindPalindromeExercise {

    private FindPalindromeExercise() {
    }

    public static boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanedStr = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        // Check if the cleaned string is equal to its reverse
        int length = cleanedStr.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleanedStr.charAt(i) != cleanedStr.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
