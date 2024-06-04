package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringEqualXExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'X', 'X', true",
            "'aaaXaaaX', 'abcXcbaX', true",
            "'XaXbXcX', 'XtXoXpXdef', true",
            "'XaXbXcX', 'XtXoXpXdXf', false",
            "'XXXX', 'XX', false",
            "'aXaXbXcX', 'XtXoXpX', false",
            "'', '', true",
//            "'', 'noX', true",
//            "'noX', '', true",
            "'X', '', false",
            "'', 'X', false"
    })
    void testEqualX(String str1, String str2, boolean expected) {
        if (expected) {
            assertTrue(StringEqualXExercise.equalX(str1, str2));
        } else {
            assertFalse(StringEqualXExercise.equalX(str1, str2));
        }
    }
}
