package com.showmeyourcode.playground.java.effectivejava;

import com.showmeyourcode.playground.java.Exercises;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EffectiveJavaOverviewTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> Exercises.main(new String[]{}));
    }
}
