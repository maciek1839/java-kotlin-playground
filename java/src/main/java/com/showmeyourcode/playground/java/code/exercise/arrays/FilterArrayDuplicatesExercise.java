package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.stream.IntStream;

public class FilterArrayDuplicatesExercise {

    private FilterArrayDuplicatesExercise() {
    }

    public static int[] filterDuplicatesWithStream(int[] arr) {
        // order is retained
        return IntStream.of(arr).distinct().toArray();
    }
}
