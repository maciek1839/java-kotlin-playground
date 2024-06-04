package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SumOfListExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> SumOfListExercise.main(new String[]{}));
    }
}
