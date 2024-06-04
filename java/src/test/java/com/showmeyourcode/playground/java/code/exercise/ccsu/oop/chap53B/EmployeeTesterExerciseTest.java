package com.showmeyourcode.playground.java.code.exercise.ccsu.oop.chap53B;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmployeeTesterExerciseTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() -> EmployeeTesterExercise.main(new String[]{}));
    }
}
