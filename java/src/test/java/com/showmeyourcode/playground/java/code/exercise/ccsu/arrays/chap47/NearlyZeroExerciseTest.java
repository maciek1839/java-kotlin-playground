package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NearlyZeroExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> NearlyZeroExercise.main(new String[]{}));
    }
}
