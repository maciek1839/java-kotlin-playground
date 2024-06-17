package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsDuplicateExerciseTest {

    static Stream<Arguments> shouldDetectDuplicates() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 1}),
                Arguments.of(new int[]{11, 11}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1}),
                Arguments.of(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}),
                Arguments.of(new int[]{-1, 0, 1, -1, 2, 3, 4, 5, 6, 7, 8, 9})
        );
    }

    static Stream<Arguments> shouldNotDetectDuplicates() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{1}),
                Arguments.of(new int[]{-2, 2})
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldDetectDuplicates(int[] nums) {
        assertTrue(ContainsDuplicateExercise.containsDuplicate(nums));
    }

    @ParameterizedTest
    @MethodSource
    void shouldNotDetectDuplicates(int[] nums) {
        assertFalse(ContainsDuplicateExercise.containsDuplicate(nums));
    }
}
