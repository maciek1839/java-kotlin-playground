package com.showmeyourcode.playground.java.code.concurrency;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Concurrency {

    private static Object mutex = new Object();
    private static ReentrantLock fairMutex = new ReentrantLock(true);

    private static Semaphore binarySemaphore = new Semaphore(1);
    private static Semaphore fairSemaphore = new Semaphore(1, true);
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(){
        // https://www.baeldung.com/java-mutex
        LOGGER.info("{} Concurrency", Descriptions.INDENT1);
        LOGGER.info("Concurrency means multiple computations are happening at the same time.");

        yieldSleepWaitJoin();

        LOGGER.info("\n==> Race condition");
        // https://www.baeldung.com/cs/race-conditions
        LOGGER.info("""
                In a multithreaded application, two or more threads may need to access
                a shared resource at the same time, resulting in unexpected behavior.
                
                We call this scenario a race condition. And, a segment of code that is executed
                by multiple concurrent threads or processes is known as the critical section.
                So, to avoid a race condition, we need to synchronize access to the critical section.
                
                By definition, a race condition is a condition of a program
                where its behavior depends on relative timing or interleaving of multiple threads or processes.
                
                Prevent race conditions:
                - Volatile Keyword: Use the volatile keyword for shared variables,
                  ensuring changes made by one thread are visible to others.
                - Synchronize the write and access methods on the shared variables
                """
        );

        // https://naveen-metta.medium.com/atomic-operations-in-java-mastering-thread-safety-and-concurrency-7c3360ec0bc5
        LOGGER.info("""
                Consider a scenario where multiple threads are simultaneously updating a shared counter variable.
                If these operations are not performed atomically, race conditions may occur,
                leading to inconsistent or incorrect results. Atomic operations provide a solution
                to this problem by ensuring that a sequence of read-modify-write operations
                is executed without interruption from other threads.
                
                The atomic execution of a process means that it enjoys the following two properties:
                All-or-nothing: the concrete process is either executed completely, or not at all;
                """);


        LOGGER.info("\n==> Mutex");
        // https://stackoverflow.com/questions/20120324/how-does-java-determine-which-thread-should-proceed-when-using-synchronized
        // https://www.baeldung.com/java-binary-semaphore-vs-reentrant-lock
        // https://stackoverflow.com/questions/17683575/binary-semaphore-vs-a-reentrantlock
        // https://stackoverflow.com/questions/44964979/what-is-fairness-in-multi-threading-programming
        LOGGER.info("""
                A mutex (or mutual exclusion) is the simplest type of synchronizer –
                it ensures that only one thread can execute the critical section of a computer program at a time.
                
                Mutex is a specific kind of binary semaphore that is used to provide a locking mechanism.
                It stands for Mutual Exclusion Object.
                
                To access a critical section, a thread acquires the mutex, then accesses the critical section,
                and finally releases the mutex. In the meantime, all other threads block till the mutex releases.
                As soon as a thread exits the critical section, another thread can enter the critical section.
                The threads hit the lock in random order and may as well leave it the same way.
                The important thing from the JVM's viewpoint is to keep the cores busy working on your program's code.
                
                A synchronized block makes no guarantees about fairness - any of the waiting threads may
                in theory be chosen to execute. If you really want a fair lock (fifo),
                switch to use the newer locking mechanisms introduced in java 5+.
                See for example the documentation for ReentrantLock.
                However, that this results in overall degraded performance and so is not recommended.
                The constructor for this class accepts an optional fairness parameter.
                When set true, under contention, locks favor granting access to the longest-waiting thread.
                Otherwise this lock does not guarantee any particular access order.
                
                Fairness basically resembles to the likelihood that different threads are able to advance whatever they are doing.
                100% fairness means:
                - all threads should be advancing their work in almost equal portions;
                - 0% fairness means that one single thread might be advancing all the time,
                    and all other threads never (or almost never) make any progress.
                """
        );

        // https://www.techtarget.com/searchnetworking/definition/mutex
        LOGGER.info("""
                In computer programming, a mutual exclusion (mutex) is a program object that
                prevents multiple threads from accessing the same shared resource simultaneously.
                """
        );

        LOGGER.info("'synchronized' keyword, which is the simplest way to implement a mutex in Java.");
        LOGGER.info("""
                Every object in Java has an intrinsic lock associated with it.
                The synchronized method and the synchronized block use this intrinsic lock to restrict the access
                of the critical section to only one thread at a time.
                """);

        synchronizedMethod();
        synchronizedBlock();

        LOGGER.info("\n=> ReentrantLock");
        // https://stackoverflow.com/questions/1312259/what-is-the-re-entrant-lock-and-concept-in-general
        LOGGER.info("ReentrantLock and synchronized are examples of mutexes in Java.");
        LOGGER.info("""
                A reentrant lock is one where a process can claim the lock multiple times without blocking on itself.
                
                A use case for re-entrant locking:
                - You have some computation involving an algorithm that traverses a graph (perhaps with cycles in it).
                 A traversal may visit the same node more than once due to the cycles or due to multiple paths to the same node.
                - Your computation can't retain complete information on what nodes you've visited,
                or you're using a data structure that doesn't allow 'have I been here before' questions to be answered quickly.
                """);
        reentrantLock();

        LOGGER.info("\n==> Semaphore");
        LOGGER.info("""
                A semaphore is a synchronization object that maintains a set of permits.
                Each permit may be acquired by a thread.
                
                A semaphore is essentially a counter. You have a number things you want to count.
                
                A semaphore is a signaling mechanism and mutex is a locking mechanism.
                """);

        binarySemaphore();
        fairSemaphore();
        semaphore();

        // https://stackoverflow.com/questions/62814/difference-between-binary-semaphore-and-mutex
        LOGGER.info("\n==> Mutex vs Semaphore");
        LOGGER.info("""
                A mutex can be released only by the thread that had acquired it.
                A binary semaphore can be signaled by any thread (or process).
                
                Mutex has ownership. The thread which locks a Mutex must also unlock it.
                
                The correct use of a semaphore is for signaling from one task to another.
                A mutex is meant to be taken and released.
                
                """);
        LOGGER.info("""
                When to use mutex and semaphore?
                
                Semaphores allow multiple threads or processes to access a shared resource with limited capacity,
                while mutexes only allow one thread or process to access a shared resource at a time.
                
                So use mutex when you have a single resource. Use semaphore when you have duplicated resources.
                
                -> Is a key to a toilet. One person can have the key - occupy the toilet - at the time.
                When finished, the person gives (frees) the key to the next person in the queue.
                -> Is the number of free identical toilet keys.
                Example, say we have four toilets with identical locks and keys.
                The semaphore count - the count of keys - is set to 4 at beginning (all four toilets are free),
                then the count value is decremented as people are coming in. If all toilets are full, ie.
                There are no free keys left, the semaphore count is 0.
                Now, when eq. one person leaves the toilet, semaphore is increased to 1 (one free key),
                and given to the next person in the queue.
                """);


        LOGGER.info("\n==> Monitor");
        LOGGER.info("""
                It is a synchronization mechanism that controls concurrent access to an object.
                
                In Java terminology a monitor is a mutex lock which is implicitly associated with an object.
                When the synchronized keyword is applied to classes or methods an implicit mutex lock is created around the code,
                which ensures that only one thread at a time can execute it.
                This is called a monitor lock or just a monitor.
                """);


        LOGGER.info("\n==> Deadlocks");
        // https://davidvlijmincx.com/posts/how-to-use-java-semaphore/
        LOGGER.info("""
                Deadlocks occur when two or more threads are blocked waiting forever for each other to release resources.
                Deadlocks can occur when there is a circular dependency between resources.
                """);

        // https://www.geeksforgeeks.org/conditions-for-deadlock-in-operating-system/
        LOGGER.info("""
                Causes of deadlocks:
                - Mutual Exclusion
                    - This condition requires that at least one resource be held in a non-shareable mode,
                    which means that only one process can use the resource at any given time.
                - Hold and Wait
                    - The hold and wait condition specifies that a process must be holding
                    at least one resource while waiting for other processes to release resources
                    that are currently held by other processes.
                - No Preemption
                    - Preemption is the act of taking a resource from a process before it has finished its task.
                    According to the no preemption condition, resources cannot be taken forcibly from a process
                    a process can only release resources voluntarily after completing its task.
                - Circular Wait
                    - This condition implies that circular processes must exist,
                    with each process waiting for a resource held by the next process in the chain.
                    In our scenario, Process A is waiting for Resource 2, which is being held by Process B.
                """);

        // https://dip-mazumder.medium.com/mastering-java-concurrency-deadlock-causes-and-prevention-e4f08f2fe33c
        LOGGER.info("""
                Preventing deadlocks:
                - Lock Ordering
                    - Lock ordering is a simple but effective deadlock prevention technique.
                    It works by requiring all threads to acquire locks in the same order.
                - Use Timeout
                    - Using a timeout is another way to prevent deadlock.
                    When acquiring a lock, a thread can specify a timeout.
                    If the lock is not acquired within the specified time, the thread will give up and try again later.
                """);

        releaseWithTimeout();

        LOGGER.info("\n==> Starvation");
        // https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html
        // https://medium.com/javarevisited/starvation-of-threads-in-java-e3d6bcfeb770
        // https://www.linkedin.com/pulse/starvation-multithreading-understanding-causes-pavan-pothuganti-faknc
        LOGGER.info("""
               Starvation describes a situation where a thread is unable to
               gain regular access to shared resources and is unable to make progress.
               This happens when shared resources are made unavailable for long periods by "greedy" threads.
               
               Causes Of Starvation:
               - High Priority Running Thread
                - Higher-priority threads continuously utilizing resources can lead to starvation for lower-priority threads.
               - Synchronized Block
                - Java’s synchronized code block provides no guarantee on the entry sequence for waiting threads.
                  This lack of order may result in a thread being blocked indefinitely, waiting to enter the block.
               - Unfair Scheduling Policies
                - Some operating systems or Java Virtual Machine (JVM) implementations
                  may employ unfair scheduling policies, favoring certain threads over others.
               - Threads Waiting on an Object
                - The wait() method, when used on an object, may lead to indefinite waiting,
                  as the notify() method does not guarantee awakening a specific waiting thread.
              
               Prevent starvation:
               - Avoid long-running or blocking tasks
               - Use Fair Locks
                   - Employ fair locks, such as Java’s ReentrantLock with the fairness parameter set to true,
                      to ensure that the longest waiting thread gains access to resources, preventing starvation.
               - Adjust Thread Priorities Carefully
                   - Be cautious when setting thread priorities,
                    as overreliance can lead to priority inversion and, consequently, starvation.
               - Use Timeout Mechanisms
                   - Implement timeout mechanisms when acquiring resources,
                      allowing threads to take alternative actions
                      if a resource cannot be acquired within a specified time.
               """);

        LOGGER.info("\n==> Livelock");
        LOGGER.info("""
                Livelock: A situation in which two or more processes continuously change their states in response
                to changes in the other process(es) without doing any useful work
                """);


        countDownLatchDemo();
    }

    private static void countDownLatchDemo() {
        LOGGER.info("=> CountDownLatch");
        try {
            CountDownLatchDemo.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void yieldSleepWaitJoin() {
        // https://stackoverflow.com/questions/10679676/does-thread-yield-lose-the-lock-on-object-if-called-inside-a-synchronized-meth
        // https://stackoverflow.com/questions/26798073/difference-between-wait-and-yield
        // https://www.baeldung.com/java-thread-yield
        // https://www.geeksforgeeks.org/java-concurrency-yield-sleep-and-join-methods/
        LOGGER.info("\n==> Yield");
        LOGGER.info("""
                The yield() method of thread class causes the currently executing thread object
                to temporarily pause and allow other threads to execute.
                So, yield is just a hint to the JVM that current thread wants to take a rest and nothing else,
                it is up to the thread scheduler to decide what to do.
                """);

        Runnable r = () -> {
            int counter = 0;
            while (counter < 2) {
                LOGGER.info(Thread.currentThread()
                        .getName());
                counter++;
                Thread.yield();
            }
        };
        new Thread(r).start();
        new Thread(r).start();

        LOGGER.info("\n==> Wait");
        LOGGER.info("""
                Calling wait() forces the current thread to wait until some other thread invokes
                notify() or notifyAll() on the same object.
                """);

        LOGGER.info("\n==> Sleep");
        LOGGER.info("""
                This method causes the currently executing thread to sleep for the specified number of milliseconds.
                Thread. sleep causes the current thread to suspend execution for a specified period.
                This is an efficient means of making processor time available to the other threads
                of an application or other applications that might be running on a computer system.
                Thread.sleep is a blocking operation. It blocks the thread for given amount of milliseconds.
                
                Calling Thread.sleep sleeps whatever thread executes that method.
                Thread.sleep is blocking.
                
                Sleep vs Wait
                - sleep() does not release the lock it holds on the Thread,
                - wait() releases the lock it holds on the object.
                - A wait can be "woken up" by another thread calling notify on the monitor which is being waited on whereas a sleep cannot.
                """);

        LOGGER.info("\n==> Join");
        LOGGER.info("""
                The join() method of a Thread instance is used to join the start of a thread’s execution
                to the end of another thread’s execution such that a thread does not start running
                until another thread ends.
                """);

    }

    private static void releaseWithTimeout() {
        // https://davidvlijmincx.com/posts/how-to-use-java-semaphore/
        // Will wait to acquire a permit for five seconds
        try {
            // If no permit is available then this method will return immediately with the value false.
            semaphore.tryAcquire(5, TimeUnit.SECONDS);
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void semaphore() {
        try {
            semaphore.acquire();
            LOGGER.info("I am busy with another semaphore...");
        } catch (InterruptedException e) {
            // exception handling code
        } finally {
            semaphore.release();
        }
    }

    private static void fairSemaphore() {
        try {
            fairSemaphore.acquire();
            LOGGER.info("I am busy with a fair semaphore...");
        } catch (InterruptedException e) {
            // exception handling code
        } finally {
            fairSemaphore.release();
        }
    }

    private static void binarySemaphore() {
        try {
            binarySemaphore.acquire();
            LOGGER.info("I am busy with a semaphore...");
        } catch (InterruptedException e) {
            // exception handling code
        } finally {
            binarySemaphore.release();
        }
    }

    private static void reentrantLock() {
            fairMutex.lock();
            try {
                LOGGER.info("I am busy again...");
            } finally {
                fairMutex.unlock();
            }
    }

    private static void synchronizedBlock() {
        synchronized (mutex) {
            LOGGER.info("I am super busy now...");
        }
    }

    private synchronized static void  synchronizedMethod(){
        LOGGER.info("I am busy...");
    }
}
