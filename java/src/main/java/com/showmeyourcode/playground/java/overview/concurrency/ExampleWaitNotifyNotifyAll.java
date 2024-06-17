package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * Simply put, calling wait() forces the current thread to wait until some other thread invokes notify() or notifyAll() on the same object.
 * <br>
 * wait()
 * The wait() method causes the current thread to wait indefinitely until another thread either invokes notify() for this object or notifyAll().
 * <br>
 * wait(long timeout)
 * Using this method, we can specify a timeout after which a thread will be woken up automatically.
 * A thread can be woken up before reaching the timeout using notify() or notifyAll().
 * <br>
 * notify()
 * notify() is used to wake any thread in the wait set whereas notifyAll() is used to wake up all the threads in the waiting set. On a general basis, notifyAll() is used. Even if you are not sure which to use, then you can go ahead and use notifyAll().
 * <br>
 *
 * References:
 * <a href="https://www.baeldung.com/java-wait-notify">Java wait notify</a>
 * <a href="https://www.digitalocean.com/community/tutorials/java-thread-wait-notify-and-notifyall-example">DigitalOcean Java Thread wait notify notifyall</a>
 *
 */
class ExampleWaitNotifyNotifyAll {

    @Slf4j
    static class WaitNotifyWorker {
        // Check a variable if part1 is done.
        // volatile used to prevent threads from
        // storing local copies of variable
        volatile boolean tripIsStarted = false;

        synchronized void startTrip() {
            log.info("Thread ({}) Welcome to India!", Thread.currentThread().getName());
            tripIsStarted = true;
            log.info("Thread ({}) about to surrender lock", Thread.currentThread().getName());
            // notify the waiting thread, if any
            // notify();
            // uncomment 'notifyAll', and you see that all remaining threads were run again!
            // There is no process starvation!!!
             notifyAll();
        }

        // method synchronized on this
        // i.e. current object of demo
        synchronized void finishTrip() {
            // loop to prevent spurious wake-up
            while (!tripIsStarted) {
                try {
                    log.info("Thread ({}) waiting", Thread.currentThread().getName());
                    // wait till notify is called
                    wait();
                    log.info("Thread ({}) running again", Thread.currentThread().getName());
                }
                catch (Exception e) {
                    log.error("An exception occurred! ", e);
                    Thread.currentThread().interrupt();
                }
            }
            log.info("Thread ({}). Do visit Taj Mahal", Thread.currentThread().getName());
        }
    }

    // It can happen that using notify() one of threads will suffer starvation
    public static void main(String[] args) {
        // Make an instance of demo class
        WaitNotifyWorker obj = new WaitNotifyWorker();

        Thread t1 = new Thread(obj::startTrip);

        Thread t2 = new Thread(obj::finishTrip);
        Thread t3 = new Thread(obj::finishTrip);

        // Start t2 and then t1
        t2.start();
        t3.start();
        t1.start();
    }
}
