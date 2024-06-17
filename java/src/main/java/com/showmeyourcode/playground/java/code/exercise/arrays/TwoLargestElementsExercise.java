package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * Find two largest elements of an array. If not found, return MAX and MIN.
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap47/progExercises47.html">Exercise source</a>
 */
public class TwoLargestElementsExercise {

    private TwoLargestElementsExercise() {
    }

    public static int[] findTwoLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements");
        }

        int largest1 = Integer.MIN_VALUE;
        int largest2 = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest1) {
                largest2 = largest1;
                largest1 = num;
            } else if (num > largest2 && num != largest1) {
                largest2 = num;
            }
        }

        return new int[]{largest1, largest2};
    }
}
