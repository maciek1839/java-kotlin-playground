package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Jdk11 {

    private Jdk11(){}

    public static void main(){
        LOGGER.info("\n{} JDK 11", Descriptions.INDENT1);
        LOGGER.info("JDK 11 was released on 25th September 2018.");
        LOGGER.info("https://www.oracle.com/java/technologies/javase/11-relnote-issues.html");

        LOGGER.info("\n{} Code examples from previous and current release", Descriptions.INDENT2);

        MyInterface myInterface = () -> LOGGER.info("THIS IS MY ABSTRACT METHOD.");
        myInterface.abstractMethodRequiredForFunctionalInterface();
        myInterface.myMethod();
    }
}

// Functional Interface can contain any number of default and static methods but only one abstract method.
@FunctionalInterface
interface MyInterface {

    void abstractMethodRequiredForFunctionalInterface();

    static void staticMethod(){}

    default void myMethod(){
        staticMethod();
        privateMethod();
    }

    private void privateMethod(){}
}
