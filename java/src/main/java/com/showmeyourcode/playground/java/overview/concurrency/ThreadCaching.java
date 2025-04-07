package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCaching {

    private static volatile boolean running = true; // Volatile to ensure visibility across threads
    private static int sharedCounter = 0; // Shared counter accessed by multiple threads

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Use fixed thread pool to manage threads

        // Task to increment counter while running
        Callable<Integer> task = () -> {
            int counter = 0;
            while (running) {
                synchronized (ThreadCaching.class) {  // Ensure thread-safety for shared counter
                    counter++;
                    sharedCounter = counter;  // Update shared counter
                }
            }
            return counter; // Return the number of increments done
        };

        // Submit the task to ExecutorService
        Future<Integer> future = executor.submit(task);

        // Main thread simulates doing some work
        Thread.sleep(1000);
        running = false;  // Stop the worker thread
        System.out.println("Main thread updated running = false");

        // Wait for the worker thread to finish and get the result
        Integer result = future.get();
        System.out.println("Worker thread completed. Counter increments: " + result);
        System.out.println("Shared counter final value: " + sharedCounter);

        // Shutdown the executor service
        executor.shutdown();
    }
}
