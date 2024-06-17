package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * Find min and max elements of an array.
 */
public class FindMinAndMaxElementsExercise {

    private FindMinAndMaxElementsExercise() {
    }

    public static int[] findMinAndMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("An array is empty or has less than 2 elements.");
        }

        int min = arr[0];
        int max = arr[0];

        for (int e : arr) {
            if (e > max) {
                max = e;
            } else if (e < min) {
                min = e;
            }
        }

        return new int[]{max, min};
    }
}
