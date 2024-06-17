package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberInRangeExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "0, 10",
            "5, 15",
            "-10, 10",
            "-5, 5",
            "100, 1000"
    })
    void testGetRandomInRange(int lower, int upper) {
        int result = RandomNumberInRangeExercise.getRandomInRange(lower, upper);
        assertTrue(result >= lower && result <= upper, "Generated number is out of range");
    }
}
