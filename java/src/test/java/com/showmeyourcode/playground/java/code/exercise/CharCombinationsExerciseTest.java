package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CharCombinationsExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> CharCombinationsExercise.main(new String[]{}));
    }
}
