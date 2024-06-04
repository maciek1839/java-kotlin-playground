package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;
// Modify the recursive definition of equals so that two strings are equalX if both strings have the same number of X characters and those characters are in all the same positions in both strings. The strings may be different lengths, but the longer string can have no Xs in its extra length.
//
// https://chortle.ccsu.edu/java5/Notes/chap75/progExercises75.html
public class StringEqualXExercise {

    public static boolean equalX(String str1, String str2) {
        // If both strings are empty, they are equalX
        if (str1.isEmpty() && str2.isEmpty()) {
            return true;
        }

        // If one string is empty, check if the other string contains any 'X'
        if (str1.isEmpty()) {
            return str2.chars().noneMatch(ch -> ch == 'X');
        }
        if (str2.isEmpty()) {
            return str1.chars().noneMatch(ch -> ch == 'X');
        }

        // Check the first characters
        char ch1 = str1.charAt(0);
        char ch2 = str2.charAt(0);

        if (ch1 == 'X' && ch2 == 'X') {
            return equalX(str1.substring(1), str2.substring(1));
        }
        if (ch1 == 'X' || ch2 == 'X') {
            return false;
        }
        return equalX(str1.substring(1), str2.substring(1));
    }
}
