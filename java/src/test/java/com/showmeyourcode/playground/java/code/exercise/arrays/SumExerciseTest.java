package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfIntegers;
import static com.showmeyourcode.playground.java.CommonTestUtil.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SumExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10',65",
            "'10,9,8,7,6,5,4,3,2,1,1',56",
            "'2,4,1,3,3',13",
            "'5,2,1,3,8,7,9,9',44",
            "'10,5,-15,3,7,-7',3",
            "'1,-1', 0"
    })
    void shouldSumUsingStream(String row, int expectedSum) {
        Integer[] numbers = parseRowOfIntegers(row);

        assertEquals(expectedSum, SumExercise.sumUsingStream(toList(numbers)));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10',65",
            "'10,9,8,7,6,5,4,3,2,1,1',56",
            "'2,4,1,3,3',13",
            "'5,2,1,3,8,7,9,9',44",
            "'10,5,-15,3,7,-7',3",
            "'1,-1', 0"
    })
    void shouldSumUsingStreamReduce(String row, int expectedSum) {
        Integer[] numbers = parseRowOfIntegers(row);

        assertEquals(expectedSum, SumExercise.sumUsingStreamReduce(toList(numbers)));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10',65",
            "'10,9,8,7,6,5,4,3,2,1,1',56",
            "'2,4,1,3,3',13",
            "'5,2,1,3,8,7,9,9',44",
            "'10,5,-15,3,7,-7',3",
            "'1,-1', 0"
    })
    void shouldSumUsingRecursive(String row, int expectedSum) {
        Integer[] numbers = parseRowOfIntegers(row);

        assertEquals(expectedSum, SumExercise.sumRecursive(toList(numbers)));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10',65",
            "'10,9,8,7,6,5,4,3,2,1,1',56",
            "'2,4,1,3,3',13",
            "'5,2,1,3,8,7,9,9',44",
            "'10,5,-15,3,7,-7',3",
            "'1,-1', 0"
    })
    void shouldSumUsingTailRecursion(String row, int expectedSum) {
        Integer[] numbers = parseRowOfIntegers(row);

        assertEquals(expectedSum, SumExercise.sumTailRecursion(toList(numbers)));
    }
}
