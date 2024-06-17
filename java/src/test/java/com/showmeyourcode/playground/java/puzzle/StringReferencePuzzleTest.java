package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StringReferencePuzzleTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                StringReferencePuzzle.main(new String[0])
        );
    }
}
