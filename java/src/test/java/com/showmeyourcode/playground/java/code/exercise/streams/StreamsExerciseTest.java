package com.showmeyourcode.playground.java.code.exercise.streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StreamsExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> StreamsExercise.main(new String[]{}));
    }
}
