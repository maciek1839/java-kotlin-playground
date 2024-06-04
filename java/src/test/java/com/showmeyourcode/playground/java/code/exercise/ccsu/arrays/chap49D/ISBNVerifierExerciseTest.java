package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49D;

import com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47.NearlyZeroExercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ISBNVerifierExerciseTest {

    @Test
    void shouldRunMainWithoutErrors(){
        assertDoesNotThrow(() -> ISBNVerifierExercise.main(new String[]{}));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-4000-3136-2"})
    void testMultipleISBNs(String isbn) {
        assertTrue(ISBNVerifierExercise.isValidISBN(isbn));
    }
}
