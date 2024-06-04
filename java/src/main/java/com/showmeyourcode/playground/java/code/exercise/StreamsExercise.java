package com.showmeyourcode.playground.java.code.exercise;

import java.util.stream.IntStream;

public class StreamsExercise {

    public static void main(String[] args) {
        long result = IntStream.range(1, 20)
                .mapToObj(x -> IntStream.of(IntStream.range(0, x).sum(),
                        IntStream.range(0, x + 1).sum()))
                .map(IntStream::sum)
                .filter(x -> Math.sqrt(x) % 1 == 0)
                .count();
        System.out.println("Result: " + result);
    }
}
