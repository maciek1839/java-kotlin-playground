package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;

// Write a program that implements string matching,
// except now the character '?' in either string matches any single character at the same position in the other string.
// If both strings have '?' in the same position, the characters match each other. For example,
//
// https://chortle.ccsu.edu/java5/Notes/chap75/progExercises75.html
public class CharacterMatcherExercise {

    public static void main(String[] args) {
        // Test cases
        System.out.println(matches("MOON", "M??N")); // true
        System.out.println(matches("W?zar?", "?izard")); // true
        System.out.println(matches("???", "???")); // true
        System.out.println(matches("??????snake", "rattle?????")); // true
        System.out.println(matches("hello", "he??o")); // true
        System.out.println(matches("hello", "he??p")); // false
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
