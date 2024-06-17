package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * Reverse an array in place.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap46/progExercises46.html">Exercise source</a>
 */
public class ReverseInPlaceExercise {

    private ReverseInPlaceExercise() {
    }

    // Method to reverse the elements of an array
    public static void reverseArray(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
