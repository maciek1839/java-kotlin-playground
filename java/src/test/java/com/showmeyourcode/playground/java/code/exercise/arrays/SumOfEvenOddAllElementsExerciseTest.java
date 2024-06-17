package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfEvenOddAllElementsExerciseTest {

    static Stream<Arguments> shouldSumAllElements() {
        return Stream.of(Arguments.of(
                        new int[]{3, 2, 5, 7, 9, 12, 97, 24, 54},
                        213),
                Arguments.of(new int[]{3, 1, 5, 7, 4, 12, -3, 8, -2},
                        35),
                Arguments.of(new int[]{-1234, 12, 53, 17, 14, 12, -13, 18, -12, -11},
                        -1144)
        );
    }

    static Stream<Arguments> shouldSumEvenElements() {
        return Stream.of(Arguments.of(
                        new int[]{3, 2, 5, 7, 9, 12, 97, 24, 54},
                        92),
                Arguments.of(new int[]{3, 1, 5, 7, 4, 12, -3, 8, -2},
                        22),
                Arguments.of(new int[]{-1234, 12, 53, 17, 14, 12, -13, 18, -12, -11},
                        -1190)
        );
    }

    static Stream<Arguments> shouldSumOddElements() {
        return Stream.of(Arguments.of(
                        new int[]{3, 2, 5, 7, 9, 12, 97, 24, 54},
                        121),
                Arguments.of(new int[]{3, 1, 5, 7, 4, 12, -3, 8, -2},
                        13),
                Arguments.of(new int[]{-1234, 12, 53, 17, 14, 12, -13, 18, -12, -11},
                        46)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldSumAllElements(int[] arr, int sum) {
        assertEquals(sum, SumOfEvenOddAllElementsExercise.calculateTotalSum(arr));
    }

    @ParameterizedTest
    @MethodSource
    void shouldSumEvenElements(int[] arr, int sum) {
        assertEquals(sum, SumOfEvenOddAllElementsExercise.calculateEvenSum(arr));
    }

    @ParameterizedTest
    @MethodSource
    void shouldSumOddElements(int[] arr, int sum) {
        assertEquals(sum, SumOfEvenOddAllElementsExercise.calculateOddSum(arr));
    }
}
