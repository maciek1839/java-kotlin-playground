package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubstringFinderExerciseTest {

    private final SubstringFinderExercise finder = new SubstringFinderExercise();

    @Test
    void testFindSubstring() {
        assertAll(
                () -> assertEquals(7, finder.findSubstring("Hello, world!", "world")),
                () -> assertEquals(-1, finder.findSubstring("Hello, world!", "Java")),
                () -> assertEquals(0, finder.findSubstring("Java programming", "Java")),
                () -> assertEquals(5, finder.findSubstring("Java programming", "programming")),
                () -> assertEquals(-1, finder.findSubstring("", "non-empty")),
                () -> assertEquals(0, finder.findSubstring("", "")),
                () -> assertEquals(0, finder.findSubstring("a", "a")),
                () -> assertEquals(-1, finder.findSubstring("a", "b"))
        );
    }

    @Test
    void testFindSubstringWithNull() {
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring("test", null));
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring(null, null));
    }

    @Test
    void testFindAllSubstrings() {
        assertAll(
                () -> assertEquals(List.of(0, 14), finder.findAllSubstrings("Hello, world! Hello again!", "Hello")),
                () -> assertEquals(List.of(), finder.findAllSubstrings("Hello, world!", "Java")),
                () -> assertEquals(List.of(0), finder.findAllSubstrings("Java programming", "Java")),
                () -> assertEquals(List.of(5), finder.findAllSubstrings("Java programming", "programming")),
                () -> assertEquals(List.of(), finder.findAllSubstrings("", "non-empty")),
                () -> assertEquals(List.of(0, 1, 2, 3, 4, 5), finder.findAllSubstrings("aaaaa", "")),
                () -> assertEquals(List.of(0), finder.findAllSubstrings("a", "a")),
                () -> assertEquals(List.of(), finder.findAllSubstrings("a", "b")),
                () -> assertEquals(List.of(3, 7, 11), finder.findAllSubstrings("abcXdefXghiX", "X"))
        );
    }

    @Test
    void testFindAllSubstringsWithNull() {
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings("test", null));
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings(null, null));
    }
}
