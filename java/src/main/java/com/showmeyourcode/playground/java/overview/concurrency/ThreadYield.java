package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * The yield() method of thread class causes the currently executing thread object to temporarily pause and allow other threads to execute.
 * <br>
 * As the official documentation suggests, yield() provides a mechanism to inform the 'scheduler'
 * that the current thread is willing to relinquish its current use of processor,
 * but it'd like to be scheduled back soon as possible.
 * <br>
 * The 'scheduler' is free to adhere or ignore this information and in fact,
 * has varying behavior depending upon the operating system.
 * <br>
 * References:
 * <a href="https://www.javatpoint.com/java-thread-yield-method">Yield method (javapoint)</a>
 * <a href="https://www.baeldung.com/java-thread-yield">Yield method (baeldung)</a>
 */
@Slf4j
public class ThreadYield {

    public static void main(String[] args) {
        Runnable r = () -> {
            int counter = 0;
            while (counter < 2) {
                log.info(Thread.currentThread()
                        .getName());
                counter++;
                Thread.yield();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
