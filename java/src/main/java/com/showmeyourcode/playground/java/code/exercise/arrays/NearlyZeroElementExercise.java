package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * Find an element closest to zero.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap47/progExercises47.html">Exercise source</a>
 */
public class NearlyZeroElementExercise {

    private NearlyZeroElementExercise() {
    }

    // Method to find the element nearest to zero in an array
    public static int findClosestToZero(int[] arr) {
        int closestToZero = arr[0]; // Assume the first element is closest to zero initially
        int minDifference = Math.abs(arr[0]); // Initialize minDifference with the absolute difference of the first element

        for (int num : arr) {
            int difference = Math.abs(num);
            if (difference < minDifference) {
                minDifference = difference;
                closestToZero = num;
            }
        }

        return closestToZero;
    }
}
