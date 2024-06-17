package com.showmeyourcode.playground.java.code.exercise.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reference: <a href="https://github.com/schananas/practical-reactor">Practical Reactor</a>
 */
@Slf4j
public class OperatorsExercise {

    private OperatorsExercise() {
    }

    public static Flux<Integer> numericalRangeSequence() {
        return Flux.range(1, 10);
    }

    public static Flux<Integer> numericalSequence() {
        return Flux.just(100, -1, 0, 78, 1);
    }

    public static void main(String[] args) {
        log.info("Transforming sequence...");
        numericalSequence()
                .filter(n -> n%2 ==0)
                .map(n-> n*2)
                .blockFirst();

        Mono.just("one")
                .flatMap(a-> Mono.just(a+" two"))
                .subscribe(a->log.info("Subscribe: {}", a));

        numericalRangeSequence()
                .subscribe(n->log.info("Stream: {}", n));
    }
}
