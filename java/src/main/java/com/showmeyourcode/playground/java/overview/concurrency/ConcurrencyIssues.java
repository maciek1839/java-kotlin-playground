package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Concurrency Issues
 * 1. Race Condition
 *      - Issue: Multiple threads access shared data simultaneously, leading to inconsistent results.
 *      - Solution: Use synchronized, ReentrantLock, Atomic variables, or volatile.
 * 2. Deadlock
 *      - Issue: Two or more threads are blocked forever, each waiting for the other to release a lock.
 *      - Solution: Lock ordering, try-lock mechanisms, avoid nested locks.
 * 3. Starvation
 *      - Issue: A thread waits indefinitely because other higher-priority threads keep executing.
 *      - Solution: Use fair locks (ReentrantLock(true)), thread priority adjustments.
 * 4. Thread Interference
 *      - Issue: Two threads modify shared data concurrently, causing unpredictable results.
 *      - Solution: Synchronization, Atomic variables, thread-safe collections.
 * 5. Memory Visibility Issue
 *      - Issue: Changes made by one thread are not visible to other threads.
 *      - Solution: Use volatile, synchronized, Lock, or Concurrent utilities.
 * 6. Incorrect Use of wait() and notify()
 *      - Issue: Calling wait() outside a synchronized block or missing notify().
 *      - Solution: Always use wait() and notify() inside a synchronized block.
 * 7. Resource Contention
 *      - Issue: Multiple threads compete for limited resources (e.g., database connections).
 *      - Solution: Connection pooling, throttling, efficient resource allocation.
 */
public class ConcurrencyIssues {
}

class P1_RaceCondition {

    /**
     * If you only need atomic updates to an integer, AtomicInteger is the better choice.
     * If your logic involves multiple dependent operations (e.g., checking and then updating a value),
     * synchronized might be necessary to ensure atomicity.
     *
     * synchronized vs 	AtomicInteger
     *
     * synchronized:
     * - Slower due to thread blocking and context switching.
     * - Uses intrinsic locks (monitor locks).
     * - High overhead, due to acquiring and releasing locks.
     *
     * AtomicInteger:
     * - Faster due to non-blocking CAS (Compare-And-Swap).
     * - Uses lock-free algorithms (CAS).
     * - Low overhead, since it avoids locks entirely.
     */
    static class CounterAtomic {
        private AtomicInteger count = new AtomicInteger(0);
        // synchronization issue
        // use synchronized or AtomicInteger to fix
        // private int count = 0;

        public void increment() {
            count.incrementAndGet(); // Not thread-safe
        }

        public int getCount() {
            return count.get();
        }
    }

    // A reentrant lock is one where a process can claim the lock multiple times without blocking on itself.
    // It's useful in situations where it's not easy to keep track of whether you've already grabbed a lock.
    // If a lock is non re-entrant you could grab the lock, then block when you go to grab it again,
    // effectively deadlocking your own process.
    // https://stackoverflow.com/questions/1312259/what-is-the-re-entrant-lock-and-concept-in-general/1312282#1312282
    static class CounterReentrantLock {
        private int count = 0;
        private final ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        public int getCount() {
            return count;
        }
    }

    static class LoggerWithSemaphore {
        private final Semaphore semaphore = new Semaphore(2); // Max 2 threads writing at once

        public void logMessage(String message) {
            try {
                semaphore.acquire(); // Acquire a permit (wait if needed)
                System.out.println(Thread.currentThread().getName() + " writing: " + message);
                Thread.sleep(100); // Simulate file writing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " finished writing.");
                semaphore.release(); // Release the permit
            }
        }
    }

    // CyclicBarrier is used to make sure a group of threads wait for each other
    // at a particular point before continuing execution.
    // It's commonly used when you need to wait for a set number of threads to reach
    // a synchronization point (i.e., a barrier) before proceeding.
    static class SynchronizedMethodsCyclicBarrier {

        private final CyclicBarrier barrier;

        public SynchronizedMethodsCyclicBarrier(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void method1() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier for method1.");
                barrier.await(); // Wait for other threads at the barrier
                System.out.println(Thread.currentThread().getName() + " is executing method1");
                Thread.sleep(500);  // Simulating some work
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void method2() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier for method2.");
                barrier.await(); // Wait for other threads at the barrier
                System.out.println(Thread.currentThread().getName() + " is executing method2");
                Thread.sleep(500);  // Simulating some work
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void method3() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier for method3.");
                barrier.await(); // Wait for other threads at the barrier
                System.out.println(Thread.currentThread().getName() + " is executing method3");
                Thread.sleep(500);  // Simulating some work
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class CountDownLatchExample {

        // Worker class representing each thread's task
        static class Worker implements Runnable {
            private final CountDownLatch latch;

            Worker(CountDownLatch latch) {
                this.latch = latch;
            }

            @Override
            public void run() {
                try {
                    // Simulate some work by sleeping
                    System.out.println(Thread.currentThread().getName() + " is working...");
                    Thread.sleep(2000);  // Simulate work with sleep
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // Decrement the latch count after the task is finished
                    System.out.println(Thread.currentThread().getName() + " finished work.");
                    latch.countDown();  // This will decrease the latch count by 1
                }
            }
        }
    }
}

class P2_Deadlock {

    static class DeadlockPreventionUsingTryLockWithTimeoutExample {

        private final Lock lockA = new ReentrantLock();
        private final Lock lockB = new ReentrantLock();

        public void runTest() throws InterruptedException {
            // Thread 1
            Thread t1 = new Thread(() -> {
                try {
                    if (acquireLocksWithTimeout(lockA, lockB)) {
                        System.out.println("Thread 1 acquired both locks.");
                        // Simulate some work
                        Thread.sleep(1000);
                        System.out.println("Thread 1 releasing both locks.");
                        lockA.unlock();
                        lockB.unlock();
                    } else {
                        System.out.println("Thread 1 could not acquire both locks within the timeout.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Thread 2
            Thread t2 = new Thread(() -> {
                try {
                    if (acquireLocksWithTimeout(lockB, lockA)) {
                        System.out.println("Thread 2 acquired both locks.");
                        // Simulate some work
                        Thread.sleep(1000);
                        System.out.println("Thread 2 releasing both locks.");
                        lockA.unlock();
                        lockB.unlock();
                    } else {
                        System.out.println("Thread 2 could not acquire both locks within the timeout.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        }

        private boolean acquireLocksWithTimeout(Lock firstLock, Lock secondLock) throws InterruptedException {
            boolean acquiredFirstLock = firstLock.tryLock(500, TimeUnit.MILLISECONDS);  // Try to acquire the first lock
            boolean acquiredSecondLock = false;

            if (acquiredFirstLock) {
                try {
                    // Try to acquire the second lock
                    acquiredSecondLock = secondLock.tryLock(500, TimeUnit.MILLISECONDS);
                    if (!acquiredSecondLock) {
                        // If second lock could not be acquired, release the first lock
                        firstLock.unlock();
                    }
                } finally {
                    // If we acquired both, we return true
                    if (acquiredFirstLock && acquiredSecondLock) {
                        return true;
                    } else {
                        // If any of the locks was not acquired, return false
                        return false;
                    }
                }
            }
            return false; // Return false if the first lock couldn't be acquired
        }
    }

    // Standard Java lock for concurrent read/write
    // Allows multiple readers and one writer at a time
    //
    // Multiple readers can read at the same time.
    // !!! But writers must wait until all readers unlock !!!
    static class DeadlockPreventionUsingReadWriteLockExample {
        private int sharedResource = 0;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        // Read operation
        void read(int readerId) {
            lock.readLock().lock();
            try {
                System.out.println("Reader " + readerId + " is reading: " + sharedResource);
                Thread.sleep(500); // Simulate reading time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
            }
        }

        // Write operation
        void write(int writerId) {
            lock.writeLock().lock();
            try {
                sharedResource++;
                System.out.println("Writer " + writerId + " updated resource to: " + sharedResource);
                Thread.sleep(2000); // Simulate writing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.writeLock().unlock();
            }
        }

        public int getSharedResource() {
            return sharedResource;
        }
    }
}

class P3_Starvation {

    static class FairLockDemo {
        private int resource = 0;
        private final ReentrantLock lock = new ReentrantLock(true); // Fair lock

        public void accessResource(int threadId) {
            try {
                lock.lock();
                resource++;
                System.out.println("Thread " + threadId + " is accessing the resource...");
                Thread.sleep(500); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Thread " + threadId + " is releasing the lock...");
                lock.unlock();
            }
        }

        public int getResource() {
            return resource;
        }
    }
}

class P4_MemoryVisibility{
    /**
     * In Java, each thread does not have a local cache per se in the traditional sense,
     * but it does maintain its own working memory (a cache of variables) as part of how modern CPUs work (e.g., the processor cache).
     * The working memory of a thread is where it keeps copies of variables that it is currently working on.
     *
     * Because threads can have their own copies of variables,
     * there is a potential issue with visibility—one thread may modify a variable,
     * but other threads may not immediately see that modification.
     * This is where volatile comes in to ensure visibility across threads.
     */
   static class VisibilityProblem {
        private static volatile boolean running = true;

        public static void main(String[] args) {
            Thread thread = new Thread(() -> {
                while (running) {
                    // Do some work
                }
                System.out.println("Stopped.");
            });

            thread.start();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            running = false; // May not be visible to `thread` if 'volatile' is not used
        }
    }
}
