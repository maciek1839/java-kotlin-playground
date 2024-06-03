package com.showmeyourcode.playground.java.code.async;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import java.util.concurrent.CompletableFuture;

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

        // https://www.educative.io/answers/what-is-completablefutureaccepteither-in-java
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            executionThread();
            return "Educative";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            executionThread();
            return "Edpresso";
        });

        completableFuture1.acceptEither(completableFuture2, res -> System.out.println("First completed future result - " + res));
        sleep(2000);
    }

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void executionThread(){
        System.out.println("Thread execution - " + Thread.currentThread().getName());
    }

}
