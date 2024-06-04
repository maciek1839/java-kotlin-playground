package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap46;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SumExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> SumExercise.main(new String[]{}));
    }
}
