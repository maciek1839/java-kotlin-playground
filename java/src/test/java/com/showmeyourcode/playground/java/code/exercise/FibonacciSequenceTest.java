package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FibonacciSequenceTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> FibonacciSequence.main(new String[]{}));
    }
}
