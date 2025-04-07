package com.showmeyourcode.playground.java.overview.concurrency;


import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ConcurrencyIssuesStarvationTest {

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
    void shouldRunMainUsingFairLockWithoutErrors() throws InterruptedException {
        var example = new P3_Starvation.FairLockDemo();
        for (int i = 1; i <= 3; i++) {
            final int threadId = i;
            executor.submit(() -> example.accessResource(threadId));
        }

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(() -> example.getResource() == 3);
    }
}
