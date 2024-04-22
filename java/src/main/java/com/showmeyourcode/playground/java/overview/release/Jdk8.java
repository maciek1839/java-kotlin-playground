package com.showmeyourcode.playground.java.overview.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Jdk8 {

    private Jdk8() {
    }

    public static void main() {
        LOGGER.info("\n{} JDK 8", Descriptions.INDENT1);
        LOGGER.info("JDK 8 was released on 18th September 2014.");
        LOGGER.info("https://docs.oracle.com/javase/8/docs/");
        LOGGER.info("https://www.oracle.com/java/technologies/javase/8-whats-new.html");

        LOGGER.info(
                """
                                    
                        What are the characteristics of Java 8?
                        - Lambda expressions
                        - Method references
                        - Functional interfaces
                        - Stream API
                        - Default methods
                        - Base64 Encode Decode
                        - Static methods in interface
                        - Optional class
                        """
        );

        LOGGER.info("{} Java Programming Language", Descriptions.INDENT2);
        LOGGER.info("\n{} Lambda Expressions & Functional interfaces", Descriptions.INDENT3);
        LOGGER.info(
                """
                        One of the most appealing features of the functional interface
                        is creating objects using lambda expressions.
                                        
                        Remember! @FunctionalInterface annotation is used to ensure that
                        the functional interface can't have more than one abstract method.
                        """
        );
        LOGGER.info("{} Method references", Descriptions.INDENT3);
        LOGGER.info(
                """
                        Method references provide easy-to-read lambda expressions for methods that already have a name.
                        """
        );
        LOGGER.info("{} Default and static methods in interfaces", Descriptions.INDENT3);
        LOGGER.info(
                """
                        In Java 8, you may add non-abstract methods to interfaces,
                        allowing you to create interfaces with method implementation.
                                      
                        Remember! Default methods are only applicable to interfaces
                        """
        );
        LOGGER.info("{} Collections", Descriptions.INDENT2);
        LOGGER.info("\n{} Classes in the new java.util.stream package", Descriptions.INDENT3);
        LOGGER.info(
                """
                        The new package provide a Stream API to support functional-style operations on streams of elements.
                        The Stream API is integrated into the Collections API, which enables bulk operations on collections,
                        such as sequential or parallel map-reduce transformations.
                        """
        );

        LOGGER.info("{} Internationalization", Descriptions.INDENT2);
        LOGGER.info("\n{} New Calendar and Locale APIs", Descriptions.INDENT3);
        LOGGER.info(
                """
                        Under the package java.time, Java 8 offers a new date-time API.
                        The following are the most prominent classes among them:
                        - Local: Simplified date-time API with no timezone management complexity.
                        - Zoned: specialized date-time API that can handle several time zones.
                        """
        );
        LOGGER.info("{} Concurrency", Descriptions.INDENT2);
        LOGGER.info(
                "\n{} Classes and interfaces have been added to the java.util.concurrent package.",
                Descriptions.INDENT3
        );

        LOGGER.info("\n{} Misc", Descriptions.INDENT2);
        LOGGER.info("\n{} Optional Class", Descriptions.INDENT3);
        LOGGER.info(
                """
                        In Java 8, the “java.util” package included an optional class.
                        The public final class 'Optional' is used to handle NullPointerException in a Java program.
                        """
        );
        LOGGER.info("{} Base64 Encode Decode", Descriptions.INDENT3);
        LOGGER.info(
                """
                        For Base64 encoding, Java 8 has built-in encode and decode functions.
                        """
        );

        LOGGER.info(
                """
                                        
                        ----
                        Other references:
                        - https://www.interviewbit.com/blog/java-8-features/
                        """
        );
    }
}
