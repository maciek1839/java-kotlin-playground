package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RandomNumbersInRangeExerciseTest {
    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> RandomNumbersInRangeExercise.main(new String[]{}));
    }
}
