package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReverseTesterExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> ReverseTesterExercise.main(new String[]{}));
    }
}
