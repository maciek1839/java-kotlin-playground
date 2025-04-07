package com.showmeyourcode.playground.java.overview.concurrency;

/**
 * In Java, methods that involve blocking or waiting on some external resource are the ones that can be interrupted.
 * The interruption status can be checked and responded to in these methods.
 * Here are the common methods that can be interrupted: Thread.sleep(long millis), Object.wait(), Thread.join(),
 * BlockingQueue Methods (e.g., take(), poll()), Future.get(), I/O Operations (e.g., read() or write()).
 *
 *
 * Check for Interruption: If your thread is doing work that can be interrupted (like waiting, sleeping, or waiting for resources),
 * periodically check if the thread has been interrupted using Thread.interrupted() or isInterrupted().
 * Clean Up: If the thread is interrupted, ensure it exits gracefully by performing necessary clean-up tasks.
 * Throw InterruptedException: In methods that can throw InterruptedException (like Thread.sleep(), wait(), etc.),
 * propagate the interruption by either re-interrupting the current thread or handling it appropriately.
 */
public class ConcurrencyInterruption {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                // Simulate a task that can be interrupted
                for (int i = 0; i < 10; i++) {
                    if (Thread.interrupted()) {
                        // Exit gracefully if the thread has been interrupted
                        System.out.println("Thread was interrupted during processing.");
                        return;
                    }

                    System.out.println("Processing task " + (i + 1));
                    Thread.sleep(1000); // Simulate work
                }
            } catch (InterruptedException e) {
                // Handle thread interruption during sleep or other blocking operations
                System.out.println("Thread was interrupted while sleeping.");
                Thread.currentThread().interrupt(); // Re-interrupt the thread
            }
        });

        thread.start();

        // Interrupt the thread after 3 seconds
        try {
            Thread.sleep(3000);
            System.out.println("Main thread interrupting the worker thread.");
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
