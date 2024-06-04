package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CharacterCounterExerciseTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() -> CharacterCounterExercise.main(new String[]{}));
    }
}
