package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FactorialCalculationTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> FactorialCalculation.main(new String[]{}));
    }
}
