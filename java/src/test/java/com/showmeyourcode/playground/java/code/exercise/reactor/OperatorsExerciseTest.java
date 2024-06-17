package com.showmeyourcode.playground.java.code.exercise.reactor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OperatorsExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> OperatorsExercise.main(new String[]{}));
    }
}
