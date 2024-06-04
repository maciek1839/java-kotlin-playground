package com.showmeyourcode.playground.java.code.exercise.ccsu.recursion.chap75;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterMatcherExerciseTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() -> CharacterMatcherExercise.main(new String[]{}));
    }

    @Test
    void testExactMatch() {
        assertTrue(CharacterMatcherExercise.matches("hello", "hello"));
    }

    @Test
    void testQuestionMarkMatch() {
        assertTrue(CharacterMatcherExercise.matches("MOON", "M??N"));
        assertTrue(CharacterMatcherExercise.matches("W?zar?", "?izard"));
        assertTrue(CharacterMatcherExercise.matches("???", "???"));
        assertTrue(CharacterMatcherExercise.matches("??????snake", "rattle?????"));
    }

    @Test
    void testPartialMatch() {
        assertTrue(CharacterMatcherExercise.matches("hello", "he??o"));
    }

    @Test
    void testNoMatch() {
        assertFalse(CharacterMatcherExercise.matches("hello", "he??p"));
    }

    @Test
    void testDifferentLengths() {
        assertFalse(CharacterMatcherExercise.matches("hello", "helloo"));
    }

    @Test
    void testEmptyStrings() {
        assertTrue(CharacterMatcherExercise.matches("", ""));
    }

    @Test
    void testOnlyQuestionMarks() {
        assertTrue(CharacterMatcherExercise.matches("?", "?"));
        assertTrue(CharacterMatcherExercise.matches("??", "??"));
        assertTrue(CharacterMatcherExercise.matches("?", "a"));
        assertTrue(CharacterMatcherExercise.matches("a", "?"));
    }
}
