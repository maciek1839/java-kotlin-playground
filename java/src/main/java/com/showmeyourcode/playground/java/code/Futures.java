package com.showmeyourcode.playground.java.code;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Futures {

    public static void main() {
        // https://www.baeldung.com/java-future
        LOGGER.info("{} Future", Descriptions.INDENT1);
        LOGGER.info("The Future class represents a future result of an asynchronous computation.");

        // https://www.baeldung.com/java-completablefuture
        LOGGER.info("\n==> Completable Future");
        LOGGER.info("Java 8 introduced the CompletableFuture class.");
        LOGGER.info("""
                The CompletableFuture class implements the Future interface
                so that we can use it as a Future implementation but with additional completion logic.
                """);
    }
}
