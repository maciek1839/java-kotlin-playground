package com.showmeyourcode.playground.java.effectivejava._5;

public class LazyInitializationInstanceField {

    // Simulated expensive or heavy object
    public class FieldType {
        FieldType() {
            System.out.println("FieldType initialized!");
        }

        public void doSomething() {
            System.out.println("FieldType is doing something.");
        }
    }

    // Volatile ensures visibility across threads
    private volatile FieldType field;

    // If you need to use lazy initialisation for performance on an instance field, use the double-check idiom.
    public FieldType getField() {
        FieldType result = field;
        if (result == null) {  // First check (no locking)
            synchronized(this) {
                if (field == null)  // Second check (with locking)
                    field = result = computeFieldValue();
            }
        }
        return result;
    }

    private FieldType computeFieldValue() {
        return new FieldType();
    }
}
