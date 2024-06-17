package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfIntegers;
import static com.showmeyourcode.playground.java.CommonTestUtil.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeNumberGeneratorExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "0,''",
            "1,''",
            "2,'2'",
            "10,'2, 3, 5, 7'",
            "20,'2, 3, 5, 7, 11, 13, 17, 19'",
            "30,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29'",
            "40,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37'",
            "50,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47'",
            "60,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59'",
            "70,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67'",
            "80,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79'",
            "90,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89'",
            "100,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97'"
    })
    void shouldCalculatePrimeNumbers(int toInclusive, String expectedPrimeNumbers) {
        Integer[] expected = parseRowOfIntegers(expectedPrimeNumbers);

        assertEquals(toList(expected), PrimeNumberGeneratorExercise.primeNumbersBruteForce(toInclusive));
    }

    @ParameterizedTest
    @CsvSource({
            "0,''",
            "1,''",
            "2,'2'",
            "10,'2, 3, 5, 7'",
            "20,'2, 3, 5, 7, 11, 13, 17, 19'",
            "30,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29'",
            "40,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37'",
            "50,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47'",
            "60,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59'",
            "70,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67'",
            "80,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79'",
            "90,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89'",
            "100,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97'"
    })
    void shouldCalculatePrimeNumbersUsingStreams(int toInclusive, String expectedPrimeNumbers) {
        Integer[] expected = parseRowOfIntegers(expectedPrimeNumbers);

        assertEquals(toList(expected), PrimeNumberGeneratorExercise.primeNumbersStream(toInclusive));
    }

    @ParameterizedTest
    @CsvSource({
            "1,''",
            "2,'2'",
            "10,'2, 3, 5, 7'",
            "20,'2, 3, 5, 7, 11, 13, 17, 19'",
            "30,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29'",
            "40,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37'",
            "50,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47'",
            "60,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59'",
            "70,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67'",
            "80,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79'",
            "90,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89'",
            "100,'2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97'"
    })
    void shouldCalculatePrimeNumbersUsingSieveOfEratosthenes(int toInclusive, String expectedPrimeNumbers) {
        Integer[] expected = parseRowOfIntegers(expectedPrimeNumbers);

        assertEquals(toList(expected), PrimeNumberGeneratorExercise.sieveOfEratosthenes(toInclusive));
    }
}
