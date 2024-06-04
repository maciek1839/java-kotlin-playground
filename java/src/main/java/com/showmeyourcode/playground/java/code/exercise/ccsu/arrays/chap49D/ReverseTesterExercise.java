package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49D;

import java.util.Random;

public class ReverseTesterExercise {
    public static void main(String[] args) {
        String randomString = generateRandomString(1000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            reverseCharArray(randomString);
        }
        long endTime = System.nanoTime();
        long arrayTime = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            reverseStringBuilder(randomString);
        }
        endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;

        System.out.println("Array-based reverse time: " + arrayTime + " ns");
        System.out.println("StringBuilder-based reverse time: " + stringBuilderTime + " ns");
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private static String reverseCharArray(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    private static String reverseStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
