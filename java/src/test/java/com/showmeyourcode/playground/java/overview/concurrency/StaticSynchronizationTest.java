package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

class StaticSynchronizationTest {

    @Test
    void shouldRunMethodWithoutDeadlock(){
        StaticSynchronization.method();
    }
}
