package com.showmeyourcode.playground.java.puzzle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Produced: " + i);
                queue.put(i); // Blocks if queue is full
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        int consumedElements = 0;
        try {
            while (consumedElements < 10) {
                Integer value = queue.take(); // Blocks if queue is empty
                System.out.println("Consumed: " + value);
                Thread.sleep(1000);
                consumedElements++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * The Producer-Consumer problem is a classic multi-threading problem where:
 *
 * Producers generate data and add it to a shared buffer (queue).
 * Consumers remove and process data from the buffer.
 * Synchronization is required to ensure correct coordination between producers and consumers.
 *
 * Why Is It a Problem?
 * Race Conditions – Multiple threads accessing the buffer can lead to data corruption.
 * Deadlocks – Improper synchronization can cause threads to wait indefinitely.
 * Busy Waiting – Consumers constantly checking for new items waste CPU resources.
 *
 * What is a Race Condition in Java?
 * A race condition occurs when multiple threads access and modify shared data simultaneously, leading to unexpected and incorrect behavior. It happens when:
 *
 * Two or more threads read and write shared variables at the same time.
 * The final result depends on the execution order of the threads.
 * No proper synchronization is used to ensure thread safety.
 */
public class ProducerConsumerProblem {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }
}
