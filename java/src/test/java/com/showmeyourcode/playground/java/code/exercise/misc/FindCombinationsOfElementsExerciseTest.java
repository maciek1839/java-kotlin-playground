package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindCombinationsOfElementsExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'', ''",
            "a,a",
            "ab,'a,b,ab'",
            "abc,'a,b,ab,ac,abc,bc,c'"
    })
    void shouldFindAllCombinations(String input, String expectedString) {
        char[] arr = input.toCharArray();
        List<String> expected = Arrays.stream(expectedString.split(",")).filter(s->!s.isBlank()).toList();
        List<String> result = FindCombinationsOfElementsExercise.findAllCombinations(arr);
        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));
        assertTrue(result.containsAll(expected));
    }
}
