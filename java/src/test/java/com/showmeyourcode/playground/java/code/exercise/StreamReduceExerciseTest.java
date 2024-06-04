package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StreamReduceExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> StreamReduceExercise.main(new String[]{}));
    }
}
