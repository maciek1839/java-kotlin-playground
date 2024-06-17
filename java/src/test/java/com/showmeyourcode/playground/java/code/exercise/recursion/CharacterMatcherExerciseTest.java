package com.showmeyourcode.playground.java.code.exercise.recursion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterMatcherExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "hello, hello",
            "hello, he??o",
            "MOON, M??N",
            "???, ???",
            "'',''",
            "?, ?",
            "??, ??",
            "?, a",
            "a, ?",
            "W?zar?, ?izard",
            "??????snake, rattle?????"
    })
    void shouldMatch(String str1, String str2){
        assertTrue(CharacterMatcherExercise.matches(str1, str2));
    }

    @ParameterizedTest
    @CsvSource({
            "hello, he??p"
    })
    void shouldNotMatch(String str1, String str2){
        assertFalse(CharacterMatcherExercise.matches(str1, str2));
    }

    @Test
    void shouldNotMatchDifferentStringsLength() {
        assertFalse(CharacterMatcherExercise.matches("hello", "helloo"));
    }
}
