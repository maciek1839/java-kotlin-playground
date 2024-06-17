package com.showmeyourcode.playground.java.code.exercise.misc;

public class RandomNumberInRangeExercise {

    private RandomNumberInRangeExercise() {
    }

    @SuppressWarnings({"java:S2140","java:S2245"})
    static int getRandomInRange(int upper, int lower) {
        // you can use java.util.Random.nextInt() as a single operation
        return (int) (Math.random() * (upper - lower)) + lower;
    }
}
