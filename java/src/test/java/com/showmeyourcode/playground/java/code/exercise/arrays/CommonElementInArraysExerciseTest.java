package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonElementInArraysExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> CommonElementInArraysExercise.main(new String[]{}));
    }

    @Test
    void shouldFindCommonElements() {
        // Define input
        int[][] lists = {
                {1, 2, 3},
                {2, 4, 6},
                {5, 5, 5}
        };

        // Call the method under test
        Map<Integer, List<Integer>> result = CommonElementInArraysExercise.getIndicesOfSharedElements(lists);

        // Define expected output
        Map<Integer, List<Integer>> expectedMap = Map.of(
                1, List.of(0),
                2, List.of(1, 0),
                3, List.of(2),
                4, List.of(1),
                6, List.of(2),
                5, List.of(0, 1, 2)
        );

        // Assert equality
        assertEquals(expectedMap, result);
    }
}
