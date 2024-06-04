package com.showmeyourcode.playground.java.code.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinMaxFinderExerciseTest {

    private final MinMaxFinderExercise finder = new MinMaxFinderExercise();

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> MinMaxFinderExercise.main(new String[]{}));
    }

    @Test
    void testFindMin() {
        assertEquals(-1, finder.findMin(new int[]{3, 5, 1, 2, 4, 8, -1}));
        assertEquals(1, finder.findMin(new int[]{3, 5, 1, 2, 4, 8}));
        assertEquals(-5, finder.findMin(new int[]{-1, -2, -3, -4, -5}));
        assertEquals(0, finder.findMin(new int[]{0}));
        assertEquals(-10, finder.findMin(new int[]{-10, 0, 10, 20, -5}));
    }

    @Test
    void testFindMax() {
        assertEquals(8, finder.findMax(new int[]{3, 5, 1, 2, 4, 8, -1}));
        assertEquals(8, finder.findMax(new int[]{3, 5, 1, 2, 4, 8}));
        assertEquals(-1, finder.findMax(new int[]{-1, -2, -3, -4, -5}));
        assertEquals(0, finder.findMax(new int[]{0}));
        assertEquals(20, finder.findMax(new int[]{-10, 0, 10, 20, -5}));
    }

    @Test
    void testFindMinWithEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            finder.findMax(new int[]{});
        });
    }

    @Test
    void testFindMaxWithEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            finder.findMax(new int[]{});
        });
    }

    @Test
    void testFindMinWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            finder.findMax(null);
        });
    }

    @Test
    void testFindMaxWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            finder.findMax(null);
        });
    }
}
