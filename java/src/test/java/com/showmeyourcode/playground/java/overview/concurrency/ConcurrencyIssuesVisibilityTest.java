package com.showmeyourcode.playground.java.overview.concurrency;


import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ConcurrencyIssuesVisibilityTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @AfterEach
    void cleanup() {
        executor.shutdown();

        // Wait until all tasks are finished
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(executor::isTerminated);
    }

    @Test
    void shouldRunMainWithoutErrors()  {
        Assertions.assertDoesNotThrow(() ->{
            P4_MemoryVisibility.VisibilityProblem.main(new String[0]);
        });
    }
}
