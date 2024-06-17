package com.showmeyourcode.playground.java.code.exercise.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FunctionalInterfaceExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> FunctionalInterfaceExercise.main(new String[]{}));
    }
}
