package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49C;

public class ArraySumExercise {
    public static void main(String[] args) {
        int[][] data = { {3, 2, 5},
                {1, 4, 4, 8, 13},
                {9, 1, 0, 2},
                {0, 2, 6, 3, -1, -8} };

        // Compute the sum of all elements
        int sum = computeSum(data);

        // Write out the sum
        System.out.println("Sum of all elements: " + sum);
    }

    // Method to compute the sum of all elements in a 2D array
    public static int computeSum(int[][] array) {
        int sum = 0;

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                sum += array[row][col];
            }
        }

        return sum;
    }
}
