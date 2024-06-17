package com.showmeyourcode.playground.java.overview.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MyAbstractTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> MyAbstract.main(new String[]{}));
    }

}
