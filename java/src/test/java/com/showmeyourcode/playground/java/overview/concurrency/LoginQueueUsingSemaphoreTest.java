package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoginQueueUsingSemaphoreTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                LoginQueueUsingSemaphore.main(new String[0])
        );
    }
}
