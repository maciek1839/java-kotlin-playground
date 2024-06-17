package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Join method in Java allows one thread to wait until another thread completes its execution.
 * In simpler words, it means it waits for the other thread to die.
 * <br>
 * Whenever we want to stop a thread from running state by calling stop() method of Thread class in Java.
 * This method stops the execution of a running thread and removes it from the waiting threads pool and garbage collected.
 * A thread will also move to the dead state automatically when it reaches the end of its method. The stop() method is deprecated in Java due to thread-safety issues.
 * <br>
 * References:
 * <a href="https://www.tutorialspoint.com/how-can-we-stop-a-thread-in-java">How can we stop a thread?</a>
 * <a href="https://www.edureka.co/blog/join-method-java">Thread join</a>
 */
@Slf4j
public class ExampleThreadJoinStop extends Thread {

    private ExampleThreadJoinStop(@NotNull String name) {
        super(name);
    }

    @Override
    public void run() {
        int i = 0;
        do {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                log.error("Error! Thread: {}", getName(), e);
                Thread.currentThread().interrupt();
            }
            log.info("{} Thread ({})", i, Thread.currentThread().getName());
            i++;
        } while(i <= 4 && !isInterrupted());
    }

    // Java programs will continue to run while any non-daemon thread is running.
    // From the link below: "A daemon thread is a thread, that does not prevent the JVM from exiting when the program finishes but the thread is still running.
    // An example for a daemon thread is the garbage collection.
    // You can use the setDaemon() method to change the Thread daemon properties."
    public static void main(String args[]) throws InterruptedException {
        ExampleThreadJoinStop th1 = new ExampleThreadJoinStop("th1");
        ExampleThreadJoinStop th2 = new ExampleThreadJoinStop("th2");
        ExampleThreadJoinStop th3 = new ExampleThreadJoinStop("th3");
        th1.start();

        th1.join();

        th2.start();
        th3.start();

        th2.interrupt();
    }
}
