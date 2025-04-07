package com.showmeyourcode.playground.java.overview.concurrency;

// The same thread can enter the inner synchronized (StaticSynchronization.class) without deadlocking.
// Your code works because nested synchronization on the same lock is allowed in Java.
// This behavior is called reentrant synchronization.
public class StaticSynchronization {

    static void method() {

        synchronized (StaticSynchronization.class) { // First lock acquisition
            System.out.println("A");
            synchronized (StaticSynchronization.class) { // Reentrant locking (same thread)
                System.out.println("B");
            } // Inner lock is released
        }// Outer lock is released
    }
}
