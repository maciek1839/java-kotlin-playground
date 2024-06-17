package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseArrayExercise {

    private ReverseArrayExercise() {
    }

    public static int[] reverseWithTempArray(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        return reversedArray;
    }

    public static void reverseWithCollections(Integer[] array) {
        List<Integer> list = Arrays.asList(array);
        Collections.reverse(list);
    }

    public static void reverseInPlace(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static void reverseRecursively(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        reverseRecursively(array, start + 1, end - 1);
    }
}
