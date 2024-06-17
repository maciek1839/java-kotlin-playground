package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindMinAndMaxElementsExerciseTest {

    static Stream<Arguments> shouldFindMaxMinElements() {
        return Stream.of(
                Arguments.of(new int[]{12, 12, 11}, new int[]{12, 11}),
                Arguments.of(new int[]{3, 1, 5, 7, 4, 12, -3, 8, -3, -2}, new int[]{12, -3}),
                Arguments.of(new int[]{-1234, 12, 53, 17, 14, 12, -13, 18, -12, -11}, new int[]{53, -1234}),
                Arguments.of(new int[]{1234, 54243, 564, -234, -234, -2332, -234, -334, 133, 134, 555, 732, 454, 145}, new int[]{54243, -2332})
        );
    }

    static Stream<Arguments> shouldThrowExceptionWhenArgumentIsInvalid(){
        return Stream.of(
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{3})
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldFindMaxMinElements(int[] arr, int[] result) {
        assertArrayEquals(result, FindMinAndMaxElementsExercise.findMinAndMax(arr));
    }

    @ParameterizedTest
    @MethodSource
    void shouldThrowExceptionWhenArgumentIsInvalid(int[] arr){
        assertThrows(IllegalArgumentException.class, ()-> FindMinAndMaxElementsExercise.findMinAndMax(arr));
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull(){
        assertThrows(IllegalArgumentException.class, ()-> FindMinAndMaxElementsExercise.findMinAndMax(null));
    }
}
