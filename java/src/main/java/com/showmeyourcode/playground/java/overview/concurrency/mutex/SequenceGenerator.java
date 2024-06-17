package com.showmeyourcode.playground.java.overview.concurrency.mutex;

/**
 * In a multithreaded application, two or more threads may need to access a shared resource at the same time, resulting in unexpected behavior.
 * Examples of such shared resources are data-structures, input-output devices, files, and network connections.
 * <p>
 * We call this scenario a race condition. And, the part of the program which accesses the shared resource is known as the critical section.
 * So, to avoid a race condition, we need to synchronize access to the critical section.
 * <p>
 * A mutex (or mutual exclusion) is the simplest type of synchronizer – it ensures that only one thread can execute the critical section of a computer program at a time.
 * <p>
 * To access a critical section, a thread acquires the mutex, then accesses the critical section, and finally releases the mutex.
 * In the meantime, all other threads block till the mutex releases.
 * As soon as a thread exits the critical section, another thread can enter the critical section.
 * <p>
 * Reference: <a href="https://www.baeldung.com/java-mutex">Java Mutex</a>
 */
public class SequenceGenerator {

    private int currentValue = 0;

    @SuppressWarnings("java:S1130")
    public int getNextSequence() throws InterruptedException {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
