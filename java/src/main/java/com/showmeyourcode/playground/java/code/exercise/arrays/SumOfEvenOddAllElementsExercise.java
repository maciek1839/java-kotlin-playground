package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap47/progExercises47.html">Exercise source</a>
 */
public class SumOfEvenOddAllElementsExercise {

    private SumOfEvenOddAllElementsExercise() {
    }

    public static int calculateTotalSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int calculateEvenSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (isEven(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static int calculateOddSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (!isEven(num)) {
                sum += num;
            }
        }
        return sum;
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
