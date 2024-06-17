package com.showmeyourcode.playground.java.code.exercise.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21"
    })
    void testFibonacci(int n, int expectedFibonacci) {
        int result = FibonacciExercise.fibonacci(n);
        assertEquals(expectedFibonacci, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21"
    })
    void testFibonacciTailRecursion(int n, int expectedFibonacci) {
        int result = FibonacciExercise.fibonacciTailRecursion(n);
        assertEquals(expectedFibonacci, result);
    }
}
