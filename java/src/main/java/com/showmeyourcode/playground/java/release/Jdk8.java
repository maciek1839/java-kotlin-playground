package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Jdk8 {

    private Jdk8() {
    }

    public static void main() {
        log.info("\n{} JDK 8", Descriptions.INDENT1);
        log.info("JDK 8 was released on 18th September 2014.");
        log.info("https://docs.oracle.com/javase/8/docs/");
        log.info("https://www.oracle.com/java/technologies/javase/8-whats-new.html");

        log.info(
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

        log.info("{} Java Programming Language", Descriptions.INDENT2);
        log.info("\n{} Lambda Expressions & Functional interfaces", Descriptions.INDENT3);
        log.info(
                """
                        One of the most appealing features of the functional interface
                        is creating objects using lambda expressions.
                                        
                        Remember! @FunctionalInterface annotation is used to ensure that
                        the functional interface can't have more than one abstract method.
                        """
        );
        log.info("{} Method references", Descriptions.INDENT3);
        log.info(
                """
                        Method references provide easy-to-read lambda expressions for methods that already have a name.
                        """
        );
        log.info("{} Default and static methods in interfaces", Descriptions.INDENT3);
        log.info(
                """
                        In Java 8, you may add non-abstract methods to interfaces,
                        allowing you to create interfaces with method implementation.
                                      
                        Remember! Default methods are only applicable to interfaces
                        """
        );
        log.info("{} Collections", Descriptions.INDENT2);
        log.info("\n{} Classes in the new java.util.stream package", Descriptions.INDENT3);
        log.info(
                """
                        The new package provide a Stream API to support functional-style operations on streams of elements.
                        The Stream API is integrated into the Collections API, which enables bulk operations on collections,
                        such as sequential or parallel map-reduce transformations.
                        """
        );

        log.info("{} Internationalization", Descriptions.INDENT2);
        log.info("\n{} New Calendar and Locale APIs", Descriptions.INDENT3);
        log.info(
                """
                        Under the package java.time, Java 8 offers a new date-time API.
                        The following are the most prominent classes among them:
                        - Local: Simplified date-time API with no timezone management complexity.
                        - Zoned: specialized date-time API that can handle several time zones.
                        """
        );
        log.info("{} Concurrency", Descriptions.INDENT2);
        log.info(
                "\n{} Classes and interfaces have been added to the java.util.concurrent package.",
                Descriptions.INDENT3
        );

        log.info("\n{} Misc", Descriptions.INDENT2);
        log.info("\n{} Optional Class", Descriptions.INDENT3);
        log.info(
                """
                        In Java 8, the “java.util” package included an optional class.
                        The public final class 'Optional' is used to handle NullPointerException in a Java program.
                        """
        );
        log.info("{} Base64 Encode Decode", Descriptions.INDENT3);
        log.info(
                """
                        For Base64 encoding, Java 8 has built-in encode and decode functions.
                        """
        );

        log.info(
                """
                                        
                        ----
                        Other references:
                        - https://www.interviewbit.com/blog/java-8-features/
                        """
        );
    }
}
