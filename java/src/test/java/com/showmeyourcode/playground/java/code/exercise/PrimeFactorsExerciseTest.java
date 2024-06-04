package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PrimeFactorsExerciseTest {
    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> PrimeFactorsExercise.main(new String[]{}));
    }
}
