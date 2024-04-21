package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationTest {

    @Test
    void shouldInitializeClassInstance(){
        var app = new Application();
        assertNotNull(app);
    }
}
