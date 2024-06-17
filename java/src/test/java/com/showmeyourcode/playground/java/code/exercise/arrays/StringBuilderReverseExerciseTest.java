package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringBuilderReverseExerciseTest {

    static Stream<Arguments> shouldRevereString() {
        return Stream.of(
                Arguments.of("life", "efil")
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldRevereString(String str, String expected) {
        assertEquals(expected, StringBuilderReverseExercise.reverse(str));
    }
}
