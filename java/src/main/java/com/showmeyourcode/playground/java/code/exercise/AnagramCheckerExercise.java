package com.showmeyourcode.playground.java.code.exercise;

import java.util.Arrays;

// Both methods are effective, but the frequency count method generally has better performance with a time complexity
// of O(n), while the sorting method has a time complexity of O(nlogn).
class AnagramCheckerExercise {

    public static void main(String[] args) {
        AnagramCheckerExercise checker = new AnagramCheckerExercise();

        System.out.println(checker.isAnagramSorting("anagram", "nagaram")); // Output: true
        System.out.println(checker.isAnagramSorting("rat", "car"));         // Output: false
        System.out.println(checker.isAnagramSorting("listen", "silent"));   // Output: true
        System.out.println(checker.isAnagramSorting("hello", "bello"));     // Output: false

        System.out.println(checker.isAnagramFrequency("anagram", "nagaram")); // Output: true
        System.out.println(checker.isAnagramFrequency("rat", "car"));         // Output: false
        System.out.println(checker.isAnagramFrequency("listen", "silent"));   // Output: true
        System.out.println(checker.isAnagramFrequency("hello", "bello"));     // Output: false
    }

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
