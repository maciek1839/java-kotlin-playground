package com.showmeyourcode.playground.java.code.exercise.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmployeeTesterExerciseTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() -> EmployeeTesterExercise.main(new String[]{}));
    }
}
