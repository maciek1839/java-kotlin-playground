package com.showmeyourcode.playground.java.overview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TailRecursionExampleTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                TailRecursionExample.main(new String[0])
        );
    }
}
