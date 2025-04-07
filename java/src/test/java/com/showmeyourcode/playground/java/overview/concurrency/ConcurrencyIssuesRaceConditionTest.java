package com.showmeyourcode.playground.java.overview.concurrency;


import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrencyIssuesRaceConditionTest {

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
    void shouldRunCounterMethodWithAtomicIntegerCorrectly() throws InterruptedException {
        var counter = new P1_RaceCondition.CounterAtomic();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        // t1.join(); means that the main thread waits for t1 to finish,
        // but t2 continues running concurrently
        t1.join();
        t2.join();

        assertEquals(2000, counter.getCount());
    }

    @Test
    void shouldRunCounterMethodWithReentrantLockCorrectly() {
        var counter = new P1_RaceCondition.CounterReentrantLock();

        Runnable operation = () ->{
            for(int i=0;i<1000;i++){
                counter.increment();
            }
        };

        for (int i = 0; i < 3; i++) {
            executor.submit(operation);
        }

        Awaitility.await()
                .atMost(Duration.ofSeconds(5))
                .until(()->counter.getCount() == 3000);
    }

    @Test
    void shouldRunLogMethodWithSemaphoreCorrectly() {
        var logger = new P1_RaceCondition.LoggerWithSemaphore();

        // Atomic counter to track active threads
        AtomicInteger activeThreads = new AtomicInteger(0);

        Runnable loggingTask = () -> {
            try {
                // Increase count when a thread starts
                activeThreads.incrementAndGet();
                logger.logMessage("Logging some data...");
            } finally {
                // Decrease count when a thread finishes
                activeThreads.decrementAndGet();
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.submit(loggingTask);
        }

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(() -> activeThreads.get() == 0);
    }

    @Test
    void shouldRunMethodsUsingCyclicBarrierCorrectly() {
        var counter = new AtomicInteger(0);
        // We want 3 threads to wait at the barrier before proceeding
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            // Action to take after all threads have reached the barrier
            System.out.println("All threads reached the barrier, now starting execution.");
            counter.incrementAndGet();
        });
        var syncMethods = new P1_RaceCondition.SynchronizedMethodsCyclicBarrier(barrier);

        // Creating threads that will execute different methods
        Runnable task1 = syncMethods::method1;
        Runnable task2 = syncMethods::method2;
        Runnable task3 = syncMethods::method3;

        for (int i = 0; i < 3; i++) {
            executor.submit(task1);
        }
        for (int i = 0; i < 3; i++) {
            executor.submit(task2);
        }
        for (int i = 0; i < 3; i++) {
            executor.submit(task3);
        }

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until(() -> counter.get() == 3);
    }

    @Test
    void shouldRunMethodsUsingCountDownLatchCorrectly() {
        int numberOfThreads = 3;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Assertions.assertDoesNotThrow(() ->{
            for (int i = 0; i < numberOfThreads; i++) {
                new Thread(new P1_RaceCondition.CountDownLatchExample.Worker(latch), "Worker-" + i).start();
            }
        });

        Awaitility.await()
                .atMost(Duration.ofSeconds(5))
                .until(()->latch.getCount() == 0);
    }
}
