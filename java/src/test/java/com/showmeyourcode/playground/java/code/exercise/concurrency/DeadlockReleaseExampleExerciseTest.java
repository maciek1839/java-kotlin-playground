package com.showmeyourcode.playground.java.code.exercise.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DeadlockReleaseExampleExerciseTest {
    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> DeadlockReleaseExampleExercise.main(new String[]{}));
    }
}
