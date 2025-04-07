package com.showmeyourcode.playground.java.overview.concurrency;


import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrencyIssuesDeadlockTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @AfterEach
    void cleanup() {
        executor.shutdown();

        // Wait until all tasks are finished
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(executor::isTerminated);
    }

    // An example of manually creating threads instead of using an executor.
    // This way of manage concurrency is not recommended.
    @Test
    void shouldRunMainUsingTryLockWithoutErrors() throws InterruptedException {
            var example = new P2_Deadlock.DeadlockPreventionUsingTryLockWithTimeoutExample();
            example.runTest();
    }

    @Test
    void shouldRunMainUsingReadWriteLockWithoutErrors(){
        var clazz = new P2_Deadlock.DeadlockPreventionUsingReadWriteLockExample();
        ExecutorService executor = Executors.newFixedThreadPool(12); // 10 readers + 2 writers

        // Start 10 reader threads
        for (int i = 1; i <= 10; i++) {
            int readerId = i;
            executor.submit(() -> clazz.read(readerId));
        }

        // Start 2 writer threads
        for (int i = 1; i <= 2; i++) {
            int writerId = i;
            executor.submit(() -> clazz.write(writerId));
        }

        Awaitility.await()
                .atMost(Duration.ofSeconds(5))
                .until(()->clazz.getSharedResource() == 2);

        // Shutdown executor gracefully
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
