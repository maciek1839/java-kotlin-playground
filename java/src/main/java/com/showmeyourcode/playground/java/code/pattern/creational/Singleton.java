package com.showmeyourcode.playground.java.code.pattern.creational;

// Reference: https://stackoverflow.com/questions/34506466/singleton-with-or-without-holder-lazy-vs-eager-initialisation
class ExampleFactory {
    // Private static variable to hold the single instance
    private static ExampleFactory instance;

    // eager initialisation
    private static class SingletonHolder {
        static {
            System.out.println("In SingletonHolder static block.");
        }
        private static final ExampleFactory INSTANCE = new ExampleFactory();
    }

    // Private constructor to prevent instantiation
    private ExampleFactory() {
    }

    // Public static method to provide access to the single instance
    public static ExampleFactory getInstanceLazy() {
        if (instance == null) {
            synchronized (ExampleFactory.class) {
                if (instance == null) {
                    instance = new ExampleFactory();
                }
            }
        }
        return instance;
    }

    public static ExampleFactory getInstanceEager() {
        return SingletonHolder.INSTANCE;
    }
}

public class Singleton {

    public static void main() {
        ExampleFactory.getInstanceEager();
        ExampleFactory.getInstanceLazy();
    }
}
