package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTesterExerciseTest {

    static Stream<Arguments> shouldReverseString() {
        return Stream.of(
                Arguments.of(
                        "abc",
                        "cba"
                )
        );
    }

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> ReverseTesterExercise.main(new String[]{}));
    }

    @ParameterizedTest
    @MethodSource
    void shouldReverseString(String s, String expected) {
        assertEquals(expected, ReverseTesterExercise.reverseCharArray(s));
        assertEquals(expected, ReverseTesterExercise.reverseStringBuilder(s));
    }
}
