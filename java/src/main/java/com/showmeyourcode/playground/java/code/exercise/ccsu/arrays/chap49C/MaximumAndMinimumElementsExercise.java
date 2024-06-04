package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49C;

public class MaximumAndMinimumElementsExercise {

    public static void main(String[] args) {
        int[][] data = { {3, 2, 5},
                {1, 4, 4, 8, 13},
                {9, 1, 0, 2},
                {0, 2, 6, 3, -1, -8} };

        // Compute the maximum and minimum
        int[] maxMin = computeMaxMin(data);

        // Write out the results
        System.out.println("max = " + maxMin[0] + "; min = " + maxMin[1]);
    }

    // Method to compute the maximum and minimum of the elements in a 2D array
    public static int[] computeMaxMin(int[][] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if (array[row][col] > max) {
                    max = array[row][col];
                }
                if (array[row][col] < min) {
                    min = array[row][col];
                }
            }
        }

        return new int[]{max, min};
    }
}
