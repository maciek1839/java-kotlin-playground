package com.showmeyourcode.playground.java.code.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * This dynamic programming approach has a time complexity of O(n^2)
 * and a space complexity of O(n^2), making it more efficient for larger strings compared to the brute-force method.
 */
public class FindPalindromesDPExercise {

    // Function to find all palindromic substrings using dynamic programming
    public static Set<String> findPalindromicSubstrings(String input) {
        Set<String> palindromes = new HashSet<>();
        int n = input.length();
        boolean[][] dp = new boolean[n][n];

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            palindromes.add(String.valueOf(input.charAt(i)));
        }

        // Check for sub-strings of length 2.
        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                dp[i][i + 1] = true;
                palindromes.add(input.substring(i, i + 2));
            }
        }

        // Check for lengths greater than 2.
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i < n - length + 1; i++) {
                int j = i + length - 1;
                if (dp[i + 1][j - 1] && input.charAt(i) == input.charAt(j)) {
                    dp[i][j] = true;
                    palindromes.add(input.substring(i, j + 1));
                }
            }
        }

        return palindromes;
    }

    public static void main(String[] args) {
        String input = "ababa";

        Set<String> palindromes = findPalindromicSubstrings(input);

        System.out.println("Palindromic substrings in \"" + input + "\": " + palindromes);
    }
}
