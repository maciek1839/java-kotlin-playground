package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedMap;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Jdk21 {
    public static void main() {
        LOGGER.info("{} JDK 21", Descriptions.INDENT1);
        LOGGER.info("JDK 21 reached General Availability on 19th September 2023.");
        LOGGER.info("https://www.oracle.com/java/technologies/javase/21-relnote-issues.html");

        switchExpressions();
        sequencedCollections();
        recordPatterns();
        virtualThreads();
    }

    private static void virtualThreads() {
        // https://belief-driven-design.com/looking-at-java-21-virtual-threads-bd181/
        // https://www.baeldung.com/java-virtual-thread-vs-thread
        LOGGER.info("\n{} Virtual threads", Descriptions.INDENT2);
        LOGGER.info("""
                These threads are typically mapped 1:1 to kernel threads scheduled by the OS.
                OS threads are quite 'heavy'.
                
                Virtual threads are managed by the JVM.
                Therefore, their allocation doesn’t require a system call,
                and they’re free of the operating system’s context switch.
                """);

        Runnable fn = () -> LOGGER.warn("I am alive!");

        //Creating Platform Threads
        new Thread(fn).start();

        //Creating Virtual Threads
        Thread.ofVirtual().start(fn);
    }

    private static void recordPatterns() {
        // https://belief-driven-design.com/looking-at-java-21-record-patterns-b5282/
        LOGGER.info("\n{} Record patterns", Descriptions.INDENT2);
        LOGGER.info("Records are a special purpose class to easily aggregate data in a shallowly immutable fashion.");

        Object maybePoint = new Point(1,1);
        if (maybePoint instanceof Point(int x, int y)) {
            LOGGER.info("Point => " + x + "/" + y);
        }
    }

    private static void sequencedCollections() {
        // https://www.baeldung.com/java-21-sequenced-collections
        LOGGER.info("\n{} Sequenced Collections", Descriptions.INDENT2);
        LOGGER.info("""
                This feature injects new interfaces into the existing hierarchy,
                offering a seamless mechanism to access the first and last elements
                of a collection using built-in default methods.
                Moreover, it provides support to obtain a reversed view of the collection.
                """);

        SequencedMap<Integer, Integer> sequencedMap = new LinkedHashMap<>(
                Map.of(5, 5, 1, 1, 3, 3, 4, 4)
        );

        LOGGER.info("Sequenced collection: {} reversed: {}", sequencedMap, sequencedMap.reversed());
    }

    private static void switchExpressions() {
        // https://belief-driven-design.com/looking-at-java-21-switch-pattern-matching-14648/
        // https://softwaremill.com/java-21-switch-the-power-on/
        LOGGER.info("\n{} Switch expressions", Descriptions.INDENT2);
        LOGGER.info("{} Pattern Matching", Descriptions.INDENT3);
        LOGGER.info("""
                Before pattern matching, switch cases supported only simple testing of
                a selector expression that needs to match a constant value exactly.
                                
                """
        );

        Object obj = new Point(1, 2);
        switchWithInstanceOf(obj);
        LOGGER.info("""
                                
                Guard clauses are a way to further refined the base condition of a case.
                They’re appended to the label before the : (colon) or -> (arrow) and are separated
                by the when keyword: case <pattern> where <guard clause>.
                """);
        LOGGER.info("Switch guard value: {}", switchWithGuard(12));
    }

    private static void switchWithInstanceOf(Object obj) {
        switch (obj) {
            case Integer i -> LOGGER.info("It is an integer - {}", i);
            case String s -> LOGGER.info("It is a string - {}", s);
            case Point p -> LOGGER.info("It is a Point - {}", p);
            default -> LOGGER.info("It is none of the known data types");
        }
    }

    private static String switchWithGuard(Object val) {
        return switch (val) {
            case null -> "n/a";
            case Integer i when i > 0 -> "It's a number!";
            case String s when !s.isEmpty() -> "Not empty String";
            default -> "I don't know what it is";
        };
    }
}

record Point(int x, int y) {
}
