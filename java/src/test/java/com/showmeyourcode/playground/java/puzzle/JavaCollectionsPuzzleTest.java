package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JavaCollectionsPuzzleTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                JavaCollectionsPuzzle.main(new String[0])
        );
    }
}
