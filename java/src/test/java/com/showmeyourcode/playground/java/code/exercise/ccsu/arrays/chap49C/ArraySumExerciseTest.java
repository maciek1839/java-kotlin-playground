package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49C;

import com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47.TwoLargestElementsExercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ArraySumExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> ArraySumExercise.main(new String[]{}));
    }
}
