package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class IntegerAssignmentPuzzleTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> IntegerAssignmentPuzzle.main(new String[]{}));
    }
}
