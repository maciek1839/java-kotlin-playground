package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortInReverseOrderStreamExerciseTest {

    static Stream<Arguments> reverseWithStreams() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3, 4, 5},
                        new Integer[]{5, 4, 3, 2, 1}
                ),
                Arguments.of(
                        new Integer[]{5, 4, 3, 2, 1},
                        new Integer[]{5, 4, 3, 2, 1}
                ),
                Arguments.of(
                        new Integer[]{99, 1, 45, 2},
                        new Integer[]{99,45,2,1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void reverseWithStreams(Integer[] nums, Integer[] expected) {
        assertArrayEquals(expected, SortInReverseOrderStreamExercise.reverseWithStreams(nums));
    }
}
