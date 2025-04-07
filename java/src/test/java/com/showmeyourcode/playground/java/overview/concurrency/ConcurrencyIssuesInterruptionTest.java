package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConcurrencyIssuesInterruptionTest {

    @Test
    void shouldRunMainWithoutErrors()  {
        Assertions.assertDoesNotThrow(() ->{
            ConcurrencyInterruption.main(new String[0]);
        });
    }
}
