package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HashCollisionExampleTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> HashCollisionExample.main(new String[]{}));
    }
}
