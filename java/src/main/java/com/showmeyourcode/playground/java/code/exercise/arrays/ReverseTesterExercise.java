package com.showmeyourcode.playground.java.code.exercise.arrays;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

/**
 * Modify the program ReverseTester of the program so that it includes a method
 * that creates a random string 1000 characters long.
 * In the main method, reverse that string 1000 times.
 * Merely call reverse and then discard the string, done print it out.
 * (For debugging purposes, start out with 100 reversals of a short string, or something else manageable.)
 * <p>
 * Time how long this takes. Now change the program so it does the same thing but with the version of reverse that uses Strings only. Time how long that version of the program takes.
 */
@Slf4j
public class ReverseTesterExercise {
    private ReverseTesterExercise() {
    }

    public static void main(String[] args) {
        int stringLength = (int) (Instant.now().toEpochMilli() % 1000 + 100);
        String randomString = generateRandomString(stringLength);
        log.info("String length: {}", stringLength);

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
        log.info("Array-based reverse time: {}ns", arrayTime);
        log.info("StringBuilder-based reverse time: {}ns", stringBuilderTime);
    }

    private static String generateRandomString(int length) {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    public static String reverseCharArray(String str) {
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

    public static String reverseStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
