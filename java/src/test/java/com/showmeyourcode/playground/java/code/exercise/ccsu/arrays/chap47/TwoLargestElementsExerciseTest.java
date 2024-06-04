package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TwoLargestElementsExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> TwoLargestElementsExercise.main(new String[]{}));
    }
}
