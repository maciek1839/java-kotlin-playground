package com.showmeyourcode.playground.java.code.exercise.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * How to correctly implement a transaction with a shared resource?
 */
@Slf4j
public class DeadlockReleaseExampleExercise {

    // Initialize semaphore with 1 permit
    private final Semaphore semaphore = new Semaphore(1);

    private DeadlockReleaseExampleExercise() {
    }

    public void performTransaction() {
        try {
            // Try to acquire semaphore with timeout
            if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                try {
                    // Perform database/API transaction
                    log.info("Transaction performed successfully");
                } finally {
                    // Release semaphore
                    semaphore.release();
                }
            } else {
                // Timeout occurred, release any held resources and abort transaction
                log.warn("Timeout occurred, aborting transaction");
                // Rollback transaction if necessary
            }
        } catch (InterruptedException e) {
            log.error("An exception occurred during performing a transaction.", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        DeadlockReleaseExampleExercise example = new DeadlockReleaseExampleExercise();
        example.performTransaction();
    }
}
