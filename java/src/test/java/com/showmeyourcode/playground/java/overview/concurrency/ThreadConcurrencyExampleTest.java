package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ThreadConcurrencyExampleTest {
    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ThreadConcurrencyExample.main(new String[0])
        );
    }
}
