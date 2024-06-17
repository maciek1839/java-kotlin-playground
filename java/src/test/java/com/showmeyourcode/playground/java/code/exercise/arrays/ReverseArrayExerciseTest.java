package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayExerciseTest {

    static List<Arguments> commonExamplesAsInt = Arrays.asList(
            Arguments.of(
                    new int[]{1, 2, 3, 4, 5},
                    new int[]{5, 4, 3, 2, 1}
            ),
            Arguments.of(
                    new int[]{5, 4, 3, 2, 1},
                    new int[]{1, 2, 3, 4, 5}
            ),
            Arguments.of(
                    new int[]{99, 1, 45, 2},
                    new int[]{2,45,1,99}
            )
    );

    static Stream<Arguments> reverseWithTempArray() {
        return commonExamplesAsInt.stream();
    }

    static Stream<Arguments> reverseWithCollections() {
        // it's sorting in place so don't reuse variables
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3, 4, 5},
                        new Integer[]{5, 4, 3, 2, 1}
                ),
                Arguments.of(
                        new Integer[]{5, 4, 3, 2, 1},
                        new Integer[]{1, 2, 3, 4, 5}
                ),
                Arguments.of(
                        new Integer[]{99, 1, 45, 2},
                        new Integer[]{2, 45, 1, 99}
                )
        );
    }

    static Stream<Arguments> reverseInPlace() {
        // it's sorting in place so don't reuse variables
        return Stream.of(Arguments.of(
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{5, 4, 3, 2, 1}),
                Arguments.of(new int[]{5, 4, 3, 2, 1},
                        new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{99, 1, 45, 2},
                        new int[]{2,45,1,99})
        );
    }

    static Stream<Arguments> reverseRecursively() {
        // it's sorting in place so don't reuse variables
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
    void reverseWithTempArray(int[] nums, int[] expected) {
        assertArrayEquals(ReverseArrayExercise.reverseWithTempArray(nums), expected);
    }

    @ParameterizedTest
    @MethodSource
    void reverseWithCollections(Integer[] nums, Integer[] expected) {
        ReverseArrayExercise.reverseWithCollections(nums);
        assertArrayEquals(nums, expected);
    }

    @ParameterizedTest
    @MethodSource
    void reverseInPlace(int[] nums, int[] expected) {
        ReverseArrayExercise.reverseInPlace(nums);
        assertArrayEquals(nums, expected);
    }

    @ParameterizedTest
    @MethodSource
    void reverseRecursively(int[] nums, int[] expected) {
        ReverseArrayExercise.reverseRecursively(nums, 0, nums.length - 1);
        assertArrayEquals(nums, expected);
    }
}
