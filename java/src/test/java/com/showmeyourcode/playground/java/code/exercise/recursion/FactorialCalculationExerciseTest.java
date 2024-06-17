package com.showmeyourcode.playground.java.code.exercise.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialCalculationExerciseTest {


    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720"
    })
    void shouldCalculateRecursiveFactorial(int n, int expectedFactorial) {
        int result = FactorialCalculationExercise.recursiveFactorial(n);
        assertEquals(expectedFactorial, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720"
    })
    void shouldCalculateTailRecursiveFactorial(int n, int expectedFactorial) {
        int result = FactorialCalculationExercise.tailRecursiveFactorial(n, 1);
        assertEquals(expectedFactorial, result);
    }
}
