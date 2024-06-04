package com.showmeyourcode.playground.java.code.paradigm;

import com.showmeyourcode.playground.java.code.pattern.DesignPatterns;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProgrammingParadigmsTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> ProgrammingParadigms.main(new String[]{}));
    }
}
