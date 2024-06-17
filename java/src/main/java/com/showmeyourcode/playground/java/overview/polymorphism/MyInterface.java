package com.showmeyourcode.playground.java.overview.polymorphism;


import com.showmeyourcode.playground.kotlin.common.Logging;

// Functional Interface can contain any number of default and static methods but only one abstract method.
@FunctionalInterface
interface MyInterface {

    void abstractMethodRequiredForFunctionalInterface();

    static void staticMethod(){}

    default void defaultMethod(){
        staticMethod();
        privateMethod();
    }

    private void privateMethod(){}

    @SuppressWarnings("unused")
    static void main(String[] args) {
        MyInterface myInterface = () -> Logging.INSTANCE.getLOGGER().info("THIS IS MY ABSTRACT METHOD.");
        myInterface.abstractMethodRequiredForFunctionalInterface();
        myInterface.defaultMethod();
    }
}
