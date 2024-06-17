package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CounterUsingMutexTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ExampleThreadJoinStop.main(new String[0])
        );
    }
}
