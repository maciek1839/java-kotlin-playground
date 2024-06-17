package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ThreadYieldTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ThreadYield.main(new String[0])
        );
    }
}
