package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CodeSamplesTest {

    @Test
    void shouldRunJavaSamplesWithoutErrors(){
        assertDoesNotThrow(() ->
                CodeSamples.main(new String[0])
        );
    }
}
