package com.showmeyourcode.playground.java.overview.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MyInterfaceTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> MyInterface.main(new String[]{}));
    }
}
