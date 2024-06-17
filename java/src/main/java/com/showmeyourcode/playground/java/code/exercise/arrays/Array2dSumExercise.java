package com.showmeyourcode.playground.java.code.exercise.arrays;

public class Array2dSumExercise {

    private Array2dSumExercise() {
    }

    // Method to compute the sum of all elements in a 2D array
    public static int computeSum(int[][] array) {
        int sum = 0;

        for (int[] ints : array) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }

        return sum;
    }
}
