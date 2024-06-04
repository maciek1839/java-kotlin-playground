package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ExercisesTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> Exercises.main(new String[]{}));
    }
}
