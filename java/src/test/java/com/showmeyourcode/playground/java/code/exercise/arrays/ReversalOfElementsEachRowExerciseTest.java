package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReversalOfElementsEachRowExerciseTest {

    @Test
    void shouldRevereArray() {
        int[][] arr =  new int[][]{{3, 2, 5},
                {1, 4, 4, 8, 13},
                {9, 1, 0, 2},
                {0, 2, 6, 3, -1, 8}};
        int[][] expected = new int[][]{
                {5,2,3},
                {13,8,4,4,1},
                {2,0,1,9},
                {8,-1,3,6,2,0}
        };

        ReversalOfElementsEachRowExercise.reverseRows(arr);

        assertArrayEquals(expected, arr);
    }
}
