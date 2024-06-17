package com.showmeyourcode.playground.java.code.exercise.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Write examples of standard Java functional interfaces.
 */
@Slf4j
public class FunctionalInterfaceExercise {

    public static void main(String[] args) {
        Consumer<String> printConsumer = x -> log.info("Print: {}", x);
        printConsumer.accept("java");

        BiConsumer<Integer, String> printBiConsumer = (number, x) -> log.info("Print2: {}",String.format("%s %d", x, number));
        printBiConsumer.accept(1, "java");

        Supplier<String> printSupplier = () -> "java supplier";
        log.info("Print3: {}", printSupplier.get());

        Predicate<String> printPredicate = "java"::equals;
        log.info("Print4: {}", printPredicate.test("javx"));

        Runnable printRunnable = () -> log.info("java runnable");
        printRunnable.run();
    }
}
