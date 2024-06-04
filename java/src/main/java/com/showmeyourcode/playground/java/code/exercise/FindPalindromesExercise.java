package com.showmeyourcode.playground.java.code.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * ### Approach: Check All Substrings ###
 * - Generate All Possible Substrings: Iterate through all possible substrings of the input string.
 * - Check Each Substring for Palindrome: Verify if each substring is a palindrome.
 * - Store Palindromes: Store and/or print the palindromic substrings.
 * ### Performance ###
 * - The time complexity of this approach is
 * O(N^3) in the worst case, where O(N) is the length of the string.
 * This is because generating all substrings takes O(N^2) time,
 *  and checking each substring for being a palindrome takes O(n) time.
 * - The space complexity is O(N^2) for storing the substrings.
 * ### Optimized Approach ###
 * For larger strings, consider more efficient algorithms like dynamic programming or expanding around the center to reduce the time complexity.
 */
public class FindPalindromesExercise {
    // Function to check if a given string is a palindrome
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Function to find all palindromic substrings
    public static Set<String> findPalindromicSubstrings(String input) {
        Set<String> palindromes = new HashSet<>();

        int n = input.length();

        // Generate all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = input.substring(i, j);
                // Check if the current substring is a palindrome
                if (isPalindrome(substring)) {
                    palindromes.add(substring);
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
