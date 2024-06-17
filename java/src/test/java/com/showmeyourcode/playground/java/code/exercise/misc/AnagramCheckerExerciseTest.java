package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnagramCheckerExerciseTest {

    private final AnagramCheckerExercise checker = new AnagramCheckerExercise();

    @ParameterizedTest
    @CsvSource({
            "anagram, nagaram, true",
            "rat, car, false",
            "listen, silent, true",
            "hello, bello, false",
            "a, a, true",
            "a, b, false",
            "abcdef, fedcba, true",
            "abcd, abcde, false"
    })
    void shouldCheckAnagramUsingFrequency(String s1, String s2, boolean expected) {
        assertEquals(expected, checker.isAnagramFrequency(s1, s2));
    }

    @ParameterizedTest
    @CsvSource({
            "anagram, nagaram, true",
            "rat, car, false",
            "listen, silent, true",
            "hello, bello, false",
            "a, a, true",
            "a, b, false",
            "abcdef, fedcba, true",
            "abcd, abcde, false"
    })
    void shouldCheckAnagramBySorting(String s1, String s2, boolean expected) {
        assertEquals(expected, checker.isAnagramSorting(s1, s2));
    }
}
