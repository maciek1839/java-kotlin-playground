package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap46;

public class ReverseOrderExercise {
    public static void main (String[] args) {
        int[] val = {0, 1, 2, 3};

        System.out.println("Original Array: "
                + val[0] + " " + val[1] + " " + val[2] + " " + val[3]);

        reverseArray(val);

        System.out.println("Reversed Array: "
                + val[0] + " " + val[1] + " " + val[2] + " " + val[3]);
    }

    // Method to reverse the elements of an array
    public static void reverseArray(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
