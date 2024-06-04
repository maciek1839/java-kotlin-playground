package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

public class TwoLargestElementsExercise {
    public static void main(String[] args) {
        int[] data = {3, 1, 5, 7, 4, 12, -3, 8, -2};

        // Find the two largest elements
        int[] largestElements = findTwoLargest(data);

        // Write out the two largest
        System.out.println("Two largest elements: " + largestElements[0] + ", " + largestElements[1]);
    }

    // Method to find the two largest elements in an array
    public static int[] findTwoLargest(int[] arr) {
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
