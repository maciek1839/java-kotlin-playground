package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ThreadWaitAndSleepTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ThreadWaitAndSleep.main(new String[0])
        );
    }
}
