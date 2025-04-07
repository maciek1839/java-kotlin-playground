package com.showmeyourcode.playground.java.effectivejava._5;

// JVM Optimizations:
// The JVM will initialize the FieldHolder class and the static field variable only when it's first accessed.
// This is a form of lazy initialization.
// The class loading mechanism guarantees that the initialization is done only once and that it is thread-safe.
public class LazyInitializationStaticField {

    // If you need to use lazy initialisation for performance on a static field, use the lazy initialisation holder class idiom.
    public static class FieldType {
        FieldType() {
            System.out.println("FieldType initialized!");
        }

        public void doSomething() {
            System.out.println("FieldType is doing something.");
        }
    }

    // The JVM guarantees that this inner class is only loaded once — on first use
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();

        private static FieldType computeFieldValue() {
            System.out.println("Computing FieldType...");
            return new FieldType();
        }
    }

    // This method triggers the lazy initialization
    public static FieldType getField() {
        return FieldHolder.field;
    }
}
