package com.showmeyourcode.playground.java.code.pattern.creational;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Reference: https://stackoverflow.com/questions/34506466/singleton-with-or-without-holder-lazy-vs-eager-initialisation
public class Singleton {

    // Private static variable to hold the single instance
    private static Singleton instance;

    // eager initialisation
    @SuppressWarnings("java:S2440")
    private static class SingletonHolder {
        static {
            log.info("In SingletonHolder static block.");
        }

        private static final Singleton INSTANCE = new Singleton();
    }

    // Public static method to provide access to the single instance
    public static Singleton getInstanceLazy() {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }

    public static Singleton getInstanceEager() {
        return SingletonHolder.INSTANCE;
    }

    // Private constructor to prevent instantiation
    private Singleton() {
    }

    public static void main(String[] args) {
        log.info("Eager: {}", Singleton.getInstanceEager());
        log.info("Lazy: {}", Singleton.getInstanceLazy());
    }
}
