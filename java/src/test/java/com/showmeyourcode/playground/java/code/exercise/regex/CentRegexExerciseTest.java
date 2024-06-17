package com.showmeyourcode.playground.java.code.exercise.regex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CentRegexExerciseTest {

    static Stream<Arguments> shouldParseCents() {
        return Stream.of(
                Arguments.of(
                        "$1.23", 123
                ),
                Arguments.of(
                        "$99.99", 9999
                ), Arguments.of(
                        "$12345678.90", 1234567890
                ),
                Arguments.of(
                        "$9.69", 969
                ),
                Arguments.of(
                        "$9.70", 970
                ),
                Arguments.of(
                        "$9.71", 971
                ),
                Arguments.of(
                        "$0.69", 69
                )
        );
    }

    private CentRegexExercise regex;

    @BeforeEach
    void setup() {
        regex = new CentRegexExercise();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1.23", "$1", "$1.23\n", "\n$1.23", "$9.69$4.3.7", "$9.692"})
    void shouldNotParse(String s) {
        assertNull(regex.toCents(s));
    }

    @ParameterizedTest
    @MethodSource
    void shouldParseCents(String price, int expectedCents) {
        assertEquals(Integer.valueOf(expectedCents), regex.toCents(price));
    }
}
