package com.showmeyourcode.playground.java.code.exercise.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringEqualXExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'X', 'X'",
            "'aaaXaaaX', 'abcXcbaX'",
            "'XaXbXcX', 'XtXoXpXdef'",
            "'', ''",
    })
    void shouldBeEqual(String str1, String str2) {
        assertTrue(StringEqualXExercise.equalX(str1, str2));
    }

    @ParameterizedTest
    @CsvSource({
            "'XaXbXcX', 'XtXoXpXdXf'",
            "'XXXX', 'XX'",
            "'aXaXbXcX', 'XtXoXpX'",
            "'X', ''",
            "'', 'X'",
            "'', 'noX'",
            "'noX', ''"
    })
    void shouldNotBeEqual(String str1, String str2) {
        assertFalse(StringEqualXExercise.equalX(str1, str2));
    }
}
