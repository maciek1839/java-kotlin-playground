package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramCheckerExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> AnagramCheckerExercise.main(new String[]{}));
    }

    private final AnagramCheckerExercise checker = new AnagramCheckerExercise();

    @Test
    void testIsAnagramFrequency() {
        assertTrue(checker.isAnagramFrequency("anagram", "nagaram"));
        assertFalse(checker.isAnagramFrequency("rat", "car"));
        assertTrue(checker.isAnagramFrequency("listen", "silent"));
        assertFalse(checker.isAnagramFrequency("hello", "bello"));
        assertTrue(checker.isAnagramFrequency("a", "a"));
        assertFalse(checker.isAnagramFrequency("a", "b"));
        assertTrue(checker.isAnagramFrequency("abcdef", "fedcba"));
        assertFalse(checker.isAnagramFrequency("abcd", "abcde"));
    }

    @Test
    void testIsAnagramSorting() {
        assertTrue(checker.isAnagramSorting("anagram", "nagaram"));
        assertFalse(checker.isAnagramSorting("rat", "car"));
        assertTrue(checker.isAnagramSorting("listen", "silent"));
        assertFalse(checker.isAnagramSorting("hello", "bello"));
        assertTrue(checker.isAnagramSorting("a", "a"));
        assertFalse(checker.isAnagramSorting("a", "b"));
        assertTrue(checker.isAnagramSorting("abcdef", "fedcba"));
        assertFalse(checker.isAnagramSorting("abcd", "abcde"));
    }
}
