package com.showmeyourcode.playground.java.code.exercise.misc;

import java.util.Arrays;

// Both methods are effective, but the frequency count method generally has better performance with a time complexity
// of O(n), while the sorting method has a time complexity of O(nlogn).

/**
 * An anagram is a word or phrase formed by rearranging the letters of another word or phrase,
 * typically using all the original letters exactly once.
 * In simpler terms, two words are anagrams if they contain the same characters with the same frequencies, but in a different order.
 */
class AnagramCheckerExercise {

    public boolean isAnagramFrequency(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26]; // Since we're dealing with lowercase English letters

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }


    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }
}
