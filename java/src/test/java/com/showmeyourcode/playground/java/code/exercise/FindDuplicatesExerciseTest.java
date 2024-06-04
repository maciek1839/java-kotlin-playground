package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FindDuplicatesExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> FindDuplicatesExercise.main(new String[]{}));
    }
}
