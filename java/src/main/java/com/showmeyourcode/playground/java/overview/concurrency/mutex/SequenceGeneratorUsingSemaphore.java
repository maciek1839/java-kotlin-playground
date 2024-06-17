package com.showmeyourcode.playground.java.overview.concurrency.mutex;

import java.util.concurrent.Semaphore;

/**
 * The Semaphore class was also introduced in Java 1.5.
 * <br>
 * While in case of a mutex only one thread can access a critical section, Semaphore allows a fixed number of threads to access a critical section.
 * Therefore, we can also implement a mutex by setting the number of allowed threads in a Semaphore to one.
 */
public class SequenceGeneratorUsingSemaphore extends SequenceGenerator {

    private final Semaphore mutex = new Semaphore(1);

    @Override
    public int getNextSequence() throws InterruptedException {
        try {
            mutex.acquire();
            return super.getNextSequence();
        } finally {
            mutex.release();
        }
    }
}
