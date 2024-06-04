package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49C;

public class SumOfEachColumnExercise {

    public static void main(String[] args) {
        int[][] data = { {3, 2, 5},
                {1, 4, 4, 8, 13},
                {9, 1, 0, 2},
                {0, 2, 6, 3, -1, -8} };

        // Compute the column sums
        int[] columnSums = computeColumnSums(data);

        // Print the column sums
        for (int sum : columnSums) {
            System.out.println("Sum of column: " + sum);
        }
    }

    // Method to compute the sum of each column of a 2D array
    public static int[] computeColumnSums(int[][] array) {
        // Determine the length of the longest row
        int maxColumns = 0;
        for (int[] row : array) {
            if (row.length > maxColumns) {
                maxColumns = row.length;
            }
        }

        // Create an array to hold the column sums
        int[] columnSums = new int[maxColumns];

        // Compute the column sums
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                columnSums[col] += array[row][col];
            }
        }

        return columnSums;
    }
}
