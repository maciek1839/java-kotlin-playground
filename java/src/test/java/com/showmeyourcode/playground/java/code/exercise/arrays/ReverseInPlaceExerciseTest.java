package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseInPlaceExerciseTest {

    static Stream<Arguments> shouldReverseInPlace() {
        return Stream.of(Arguments.of(
                new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 3, 2, 1}),
                Arguments.of(new int[]{5, 4, 3, 2, 1},
                new int[]{1, 2, 3, 4, 5}),
                        Arguments.of(new int[]{99, 1, 45, 2},
                new int[]{2,45,1,99})
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldReverseInPlace(int[] notSorted, int[] sorted) {
        ReverseInPlaceExercise.reverseArray(notSorted);
        assertArrayEquals(sorted, notSorted);
    }
}
