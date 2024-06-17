package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Array2dSumExerciseTest {

    @Test
    void shouldComputeSum(){
        assertEquals(61, Array2dSumExercise.computeSum(
                new int[][]{
                        new int[]{1,2,3,4,5},
                        new int[]{5,6,8,7,4,3,6,7}
                }
        ));
    }
}
