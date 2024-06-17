package com.showmeyourcode.playground.java.overview.async;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class Futures {

    private Futures() {
    }

    public static void main(String[] args) {
        // https://www.baeldung.com/java-future
        log.info("{} Future", Descriptions.INDENT1);
        log.info("The Future class represents a future result of an asynchronous computation.");

        // https://www.baeldung.com/java-completablefuture
        log.info("\n==> Completable Future");
        log.info("Java 8 introduced the CompletableFuture class.");
        log.info("""
                The CompletableFuture class implements the Future interface
                so that we can use it as a Future implementation but with additional completion logic.
                
                Completion Handling: Future relies on blocking methods like get() for result retrieval,
                while CompletableFuture provides non-blocking methods like thenAccept() for completion handling.
                
                Composition: CompletableFuture supports fluent API and allows chaining of multiple asynchronous operations,
                whereas Future does not.
                
                Reference: https://dev.to/codegreen/future-vs-completablefuture-classes-in-java-1n07
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

        completableFuture1.acceptEither(completableFuture2, res -> log.info("First completed future result - {}", res));
        sleep(2000);
    }

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("A thread was interrupted.", e);
            // An interruption is initiated by calling the interrupt() method on the thread object.
            // When a thread is interrupted, it sets its interrupted status to true.
            // If the thread is blocked in a wait(), sleep(), or join() method,
            // it will immediately throw an InterruptedException.
            //
            //  Common causes for thread interruption include:
            //  1. Graceful Termination: Requesting a thread to stop its execution in a controlled manner.
            //  2. Timeouts: Interrupting a thread that has been waiting or sleeping for too long.
            //  3. Shutdown Hooks: Interrupting threads during the shutdown of an application.

            // When handling an InterruptedException,
            // it is often advisable to set the interrupted flag back to true to ensure that the interruption is not lost.
            // This allows higher-level code to detect the interruption.

            /* Clean up whatever needs to be handled before interrupting  */
            Thread.currentThread().interrupt();
        }
    }

    static void executionThread(){
        log.info("Thread execution - {}", Thread.currentThread().getName());
    }

}
