package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

public class ReversalOfElementsExercise {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        // Reverse the array
        int[] result = reverseArray(data);

        // Write out the result
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Method to reverse an array
    public static int[] reverseArray(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }
}
