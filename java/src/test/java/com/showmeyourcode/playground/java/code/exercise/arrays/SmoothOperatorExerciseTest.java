package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SmoothOperatorExerciseTest {

    static Stream<Arguments> shouldApplySmoothOperator() {
        return Stream.of(
                Arguments.of(
                        new int[]{5, 5, 4, 5, 6, 6, 7, 6, 5, 4, 1, 4},
                        new int[]{5, 4, 4, 5, 5, 6, 6, 6, 5, 3, 3, 2}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldApplySmoothOperator(int[] before, int[] after) {
        Assertions.assertArrayEquals(after, SmoothOperatorExercise.smoothSignal(before));
    }
}
