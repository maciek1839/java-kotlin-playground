package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FilterArrayDuplicatesExerciseTest {

    @Test
    void shouldFilterOutDuplicates(){
        assertArrayEquals(new int[]{3,1,5},FilterArrayDuplicatesExercise.filterDuplicatesWithStream(new int[]{3,3,1,1,5,5,1,1}));
    }
}
