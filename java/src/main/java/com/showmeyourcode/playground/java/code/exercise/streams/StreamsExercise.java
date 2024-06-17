package com.showmeyourcode.playground.java.code.exercise.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * 1. Use a IntStream to write a simple computation.
 * 2. Have a look on an example stream and explain the output.
 */
@Slf4j
public class StreamsExercise {

    private StreamsExercise() {
    }

    public static void main(String[] args) {
        long result = IntStream.range(1, 20)
                .mapToObj(x -> IntStream.of(IntStream.range(0, x).sum(),
                        IntStream.range(0, x + 1).sum()))
                .map(IntStream::sum)
                .filter(x -> Math.sqrt(x) % 1 == 0)
                .count();
        log.info("Result: {}", result);
    }
}
