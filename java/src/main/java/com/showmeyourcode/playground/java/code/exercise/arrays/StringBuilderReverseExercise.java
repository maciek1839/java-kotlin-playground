package com.showmeyourcode.playground.java.code.exercise.arrays;

public class StringBuilderReverseExercise {

    private StringBuilderReverseExercise() {
    }

    static String reverse(String life) {
        // It would be good to remember a difference between StringBuilder and StringBuffer.
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = life.length() - 1; i >= 0; i--) {
            stringBuilder.append(life.charAt(i));
        }
        return stringBuilder.toString();
    }
}
