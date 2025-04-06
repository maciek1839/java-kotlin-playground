package com.showmeyourcode.playground.java.puzzle;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * In multithreaded computing, the ABA problem occurs during synchronization,
 * when a location is read twice, has the same value for both reads,
 * and "value is the same" is used to indicate "nothing has changed".
 * However, another thread can execute between the two reads and change the value,
 * do other work, then change the value back,
 * thus fooling the first thread into thinking "nothing has changed" even though the second thread did work that violates that assumption.
 *
 * References: <https://pl.wikipedia.org/wiki/Problem_ABA>
 *
 * How ABA Happens
 * Thread 1 reads a value A.
 * Thread 2 changes the value from A → B and then back to A.
 * Thread 1 sees that the value is still A and proceeds, unaware that an intermediate change occurred.
 *
 * Where ABA Occurs
 * Lock-free data structures (e.g., stacks, queues).
 * Memory management (e.g., garbage collection, reference counting).
 * Optimistic concurrency control (e.g., databases, transactional memory).
 */
public class AbaProblem {

    static AtomicInteger atomicValue = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int oldValue = atomicValue.get();
            System.out.println("Thread 1 read value: " + oldValue);

            // Simulate a delay before performing CAS
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            //Compare-And-Swap (CAS)
            boolean success = atomicValue.compareAndSet(oldValue, 2);
            System.out.println("Thread 1 CAS success: " + success);
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            atomicValue.compareAndSet(1, 3);
            System.out.println("Thread 2 changed value to 3");

            atomicValue.compareAndSet(3, 1);
            System.out.println("Thread 2 changed value back to 1");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ABAProblemSolution {
    static AtomicStampedReference<Integer> atomicValue = new AtomicStampedReference<>(1, 0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            int oldValue = atomicValue.get(stampHolder);
            int oldStamp = stampHolder[0];

            System.out.println("Thread 1 read value: " + oldValue + " with stamp: " + oldStamp);

            // Simulate a delay before performing CAS
            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            boolean success = atomicValue.compareAndSet(oldValue, 2, oldStamp, oldStamp + 1);
            System.out.println("Thread 1 CAS success: " + success);
        });

        Thread thread2 = new Thread(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}

            int[] stampHolder = new int[1];
            int currentValue = atomicValue.get(stampHolder);
            int currentStamp = stampHolder[0];

            atomicValue.compareAndSet(currentValue, 3, currentStamp, currentStamp + 1);
            System.out.println("Thread 2 changed value to 3 with new stamp");

            atomicValue.compareAndSet(3, 1, currentStamp + 1, currentStamp + 2);
            System.out.println("Thread 2 changed value back to 1 with new stamp");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
