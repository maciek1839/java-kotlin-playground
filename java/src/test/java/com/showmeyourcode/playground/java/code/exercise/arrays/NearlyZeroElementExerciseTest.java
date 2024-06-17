package com.showmeyourcode.playground.java.code.exercise.arrays;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NearlyZeroElementExerciseTest {

    static Stream<Arguments> shouldFindClosestToZero() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 5, 7, 4, 12, -3, 8, -2}, 1),
                Arguments.of(new int[]{-1234, 12, 53, 17, 14, 12, -13, 18, -12, -11}, -11),
                Arguments.of(new int[]{1234, 54243, 564, -234, -234, -2332, -234, -334, 133, 134, 555, 732, 454, 145}, 133)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldFindClosestToZero(int[] arr, int element) {
        assertEquals(element, NearlyZeroElementExercise.findClosestToZero(arr));
    }
}

