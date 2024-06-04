package com.showmeyourcode.playground.java.code.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DesignPatternsTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> DesignPatterns.main(new String[]{}));
    }
}
