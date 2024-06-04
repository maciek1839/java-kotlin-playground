package com.showmeyourcode.playground.java.code.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * This method involves expanding around each character (and each pair of characters) to find all palindromic substrings.
 *
 * Performance:
 * Time Complexity: O(n^2)
 * Each character is expanded up to the length of the string.
 * Space Complexity: O(1) additional space (excluding the space required for the output set).
 *
 * This method is efficient in practice due to its simplicity and minimal space usage.
 * Although it doesn't improve the theoretical worst-case time complexity compared to the dynamic programming approach,
 * it is often preferred for its straightforward implementation and efficiency.
 */
public class FindPalindromesExpandCenterApproachExercise {
    // Function to expand around the center and add palindromes to the set
    private static void expandAroundCenter(String s, int left, int right, Set<String> palindromes) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            palindromes.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }

    // Function to find all palindromic substrings
    public static Set<String> findPalindromicSubstrings(String input) {
        Set<String> palindromes = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            // Odd length palindromes
            expandAroundCenter(input, i, i, palindromes);
            // Even length palindromes
            expandAroundCenter(input, i, i + 1, palindromes);
        }

        return palindromes;
    }

    public static void main(String[] args) {
        String input = "ababa";

        Set<String> palindromes = findPalindromicSubstrings(input);

        System.out.println("Palindromic substrings in \"" + input + "\": " + palindromes);
    }
}
