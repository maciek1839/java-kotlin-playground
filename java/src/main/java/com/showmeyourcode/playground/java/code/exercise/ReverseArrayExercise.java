package com.showmeyourcode.playground.java.code.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseArrayExercise {

    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        int[] array3 = {1, 2, 3, 4, 5};
        Integer[] array4 = {1, 2, 3, 4, 5};
        int[] array5 = {1, 2, 3, 4, 5};

        Integer[] resul1 = reverseWithStreams(array1);
        reverseInPlace(array2);
        int[] result2 = reverseWithTempArray(array3);
        reverseWithCollections(array4);
        reverseRecursively(array5,0, array5.length - 1);
        for (int i : resul1) {
            System.out.print(i + " ");
        }
        for (int i : result2) {
            System.out.print(i + " ");
        }
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
        array = list.toArray(array);
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

    public static Integer[] reverseWithStreams(Integer[] array) {
        return Arrays.stream(array)
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);
    }
}
