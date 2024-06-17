package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * What are differences between wait and sleep in Java?
 * <br>
 * Sleep() method belongs to Thread class.
 * Sleep() method does not release the lock on object during Synchronization.
 * Wait() method releases lock during Synchronization.
 * Wait() should be called only from Synchronized context.
 */
@Slf4j
public class ThreadWaitAndSleep {

    private static final Object LOCK = new Object();
    private static final int NUMBER_OF_THREADS = 4;
    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static void main(String[] args) {
        log.info("MAIN STARTED=========================");

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            executor.execute(() -> {
                try {
                    log.info("Thread: {} is starting.", Thread.currentThread().getName());
                    threadMethod();
                    log.info("Thread: {} is finishing.", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    log.error("ERROR! ", e);
                    Thread.currentThread().interrupt();
                }
            });
        }

        log.info("MAIN FINISHED========================");
        executor.close();
    }

    private static void threadMethod() throws InterruptedException {
        synchronized (LOCK) {
            String threadName = Thread.currentThread().getName();
            log.info("Accessing the critical section: {}", threadName);
            Thread.sleep(1000);

            log.info("Thread '{}' is woken after sleeping for 1 second", threadName);

            LOCK.wait(2000);

            log.info("Object '{}' is woken after waiting for 3 seconds.", LOCK);
        }
    }
}
