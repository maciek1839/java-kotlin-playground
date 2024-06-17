package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.Arrays;
import java.util.Collections;

public class SortInReverseOrderStreamExercise {

    private SortInReverseOrderStreamExercise() {
    }

    public static Integer[] reverseWithStreams(Integer[] array) {
        return Arrays.stream(array)
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);
    }
}
