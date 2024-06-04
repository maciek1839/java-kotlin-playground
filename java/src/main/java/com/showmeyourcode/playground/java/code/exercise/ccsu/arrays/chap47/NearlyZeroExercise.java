package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

public class NearlyZeroExercise {

    public static void main(String[] args) {
        int[] data = {3, 1, 5, 7, 4, 12, -3, 8, -2};

        // Find the element nearest to zero
        int closestToZero = findClosestToZero(data);

        // Write out the element nearest to zero
        System.out.println("Element nearest to zero: " + closestToZero);
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
