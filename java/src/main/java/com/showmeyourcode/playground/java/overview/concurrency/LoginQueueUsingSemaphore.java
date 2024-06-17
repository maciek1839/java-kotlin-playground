package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * When to use semaphore and mutex?
 * A mutex is used for serial access to a resource while a semaphore limits access to a resource up to a set number.
 * You can think of a mutex as a semaphore with an access count of 1.
 * Whatever you set your semaphore count to, that may threads can access the resource before the resource is blocked.
 * References:
 * <a href="https://www.baeldung.com/java-semaphore">Java semaphore</a>
 * <a href="https://stackoverflow.com/questions/771347/what-is-mutex-and-semaphore-in-java-what-is-the-main-difference">Mutex vs Semaphore</a>
 */
@Slf4j
public class LoginQueueUsingSemaphore {

    private final Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        log.debug("Trying logging in '{}' ...", Thread.currentThread().getName());
        // you can use acquire method which is blocking
        return semaphore.tryAcquire();
    }

    void logout() {
        log.info("Logging out '{}' ...", Thread.currentThread().getName());
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }

    @SneakyThrows
    public static void main(String[] args) {
        int slots = 4;
        ExecutorService executor = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots/2);

        IntStream.range(0, slots)
                .forEach(user -> executor.execute(new Worker(loginQueue)));


        // The shutdown() method does one thing: prevents clients to send more work to the executor service.
        // This means all the existing tasks will still run to completion unless other actions are taken.
        // The default implementation invokes shutdown() and waits for tasks to complete
        executor.close();

        // Reference: https://stackoverflow.com/questions/18425026/shutdown-and-awaittermination-which-first-call-have-any-difference
        try {
            if (!executor.awaitTermination(3500, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            throw e;
        }
    }

    @Slf4j
    record Worker(LoginQueueUsingSemaphore queue) implements Runnable {

        @SneakyThrows
            @Override
            public void run() {
                log.info("The thread '{}' is starting. Available slots: {}", Thread.currentThread().getName(), queue.availableSlots());
                while (!queue.tryLogin()) {
                    log.trace("The resource is not available. Keep trying...");
                }
                log.info("The thread '{}' is working. Available slots: {}", Thread.currentThread().getName(), queue.availableSlots());
                Thread.sleep(Duration.ofMillis(2000));
                queue.logout();
            }
        }
}
