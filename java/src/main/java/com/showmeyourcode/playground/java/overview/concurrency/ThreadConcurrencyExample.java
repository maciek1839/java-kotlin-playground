package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConcurrencyExample {

    private static volatile boolean running = true; // Volatile to ensure visibility across threads
    private static int sharedCounter = 0; // Shared counter accessed by multiple threads

    // ReentrantLock for controlling access to the shared counter
    private static final ReentrantLock lock = new ReentrantLock();

    // Semaphore to limit concurrent access to a section of code
    private static final Semaphore semaphore = new Semaphore(1); // Only 1 thread can access critical section at a time

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Use fixed thread pool to manage threads

        // Create a CountDownLatch with a count of 1, indicating the main thread waits for one task to finish
        CountDownLatch latch = new CountDownLatch(1);

        // Task to increment counter while running
        Callable<Integer> task = () -> {
            int counter = 0;

            // Acquire the semaphore before accessing the critical section
            semaphore.acquire();  // Block if semaphore is already acquired by another thread
            try {
                while (running) {
                    // Use ReentrantLock to ensure thread-safety for shared counter
                    lock.lock();
                    try {
                        counter++;
                        sharedCounter = counter; // Update shared counter
                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                semaphore.release();  // Release the semaphore after exiting critical section
            }

            latch.countDown();  // Decrease the count of the latch to signal completion
            return counter; // Return the number of increments done
        };

        // Submit the task to ExecutorService
        Future<Integer> future = executor.submit(task);

        // Main thread simulates doing some work
        Thread.sleep(1000);
        running = false;  // Stop the worker thread
        System.out.println("Main thread updated running = false");

        // Wait for the worker thread to finish using CountDownLatch
        latch.await();  // Wait until the latch count reaches zero

        // After the latch is decremented (worker thread is done), retrieve the result
        Integer result = future.get();
        System.out.println("Worker thread completed. Counter increments: " + result);
        System.out.println("Shared counter final value: " + sharedCounter);

        // Shutdown the executor service
        executor.shutdown();
    }
}
