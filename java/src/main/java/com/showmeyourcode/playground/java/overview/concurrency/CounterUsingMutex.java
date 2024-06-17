package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

@Slf4j
public class CounterUsingMutex {
    private final Semaphore mutex;
    private int count;

    CounterUsingMutex() {
        mutex = new Semaphore(1);
        count = 0;
    }

    public static void main(String[] args) {
        int count = 5;
        ExecutorService executorService
                = Executors.newFixedThreadPool(count);
        CounterUsingMutex counter = new CounterUsingMutex();
        IntStream.range(0, count)
                .forEach(user -> executorService.execute(() -> {
                    try {
                        counter.increase();
                    } catch (InterruptedException e) {
                        log.error("An error occurred! ", e);
                        Thread.currentThread().interrupt();
                    }
                }));
        log.info("All threads started. Counter: {} Are threads waiting to acquire? {}", counter.getCount(), counter.hasQueuedThreads());

        // The shutdown() method does one thing: prevents clients to send more work to the executor service.
        // This means all the existing tasks will still run to completion unless other actions are taken.
        // The default implementation invokes shutdown() and waits for tasks to complete
        executorService.close();

        log.info("Counter: {} Are threads waiting to acquire? {}", counter.getCount(), counter.hasQueuedThreads());
    }

    void increase() throws InterruptedException {
        mutex.acquire();
        log.info("Increasing ({}) Are threads waiting to acquire? {}", Thread.currentThread().getName(), hasQueuedThreads());
        this.count = this.count + 1;
        Thread.sleep(1000);
        log.info("Done ({})", Thread.currentThread().getName());
        mutex.release();
    }

    int getCount() {
        return this.count;
    }

    boolean hasQueuedThreads() {
        return mutex.hasQueuedThreads();
    }
}
