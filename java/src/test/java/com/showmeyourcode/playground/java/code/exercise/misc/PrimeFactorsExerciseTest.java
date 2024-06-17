package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfIntegers;
import static com.showmeyourcode.playground.java.CommonTestUtil.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeFactorsExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "2, '2'",
            "3, '3'",
            "4, '2,2'",
            "5, '5'",
            "6, '2,3'",
            "7, '7'",
            "8, '2,2,2'",
            "9, '3,3'",
            "10, '2,5'",
            "12, '2,2,3'",
            "15, '3,5'",
            "16, '2,2,2,2'",
            "17, '17'",
            "18, '2,3,3'",
            "19, '19'",
            "20, '2,2,5'",
            "25, '5,5'",
            "30, '2,3,5'",
            "36, '2,2,3,3'",
            "49, '7,7'",
            "50, '2,5,5'",
            "72, '2,2,2,3,3'",
            "100, '2,2,5,5'",
            "121, '11,11'",
            "144, '2,2,2,2,3,3'",
            "169, '13,13'",
            "196, '2,2,7,7'",
            "225, '3,3,5,5'",
            "250, '2,5,5,5'",
            "300, '2,2,3,5,5'",
            "400, '2,2,2,2,5,5'",
            "625, '5,5,5,5'",
            "900, '2,2,3,3,5,5'",
            "1000, '2,2,2,5,5,5'"
    })
    void shouldReturnPrimeFactors(int n, String expectedPrimeFactors) {
        assertEquals(toList(parseRowOfIntegers(expectedPrimeFactors)), PrimeFactorsExercise.generatePrimeFactors(n));
    }
}
