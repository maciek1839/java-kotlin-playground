package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApplicationTest {

    @Test
    void shouldInitializeClassInstance(){
        assertDoesNotThrow(() ->
                Application.main(new String[0])
        );
    }
}
