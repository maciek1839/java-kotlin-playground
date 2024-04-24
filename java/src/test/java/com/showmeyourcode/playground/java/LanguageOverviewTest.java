package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LanguageOverviewTest {

    @Test
    void shouldRunApplicationWithoutErrors(){
        assertDoesNotThrow(() ->
                LanguageOverview.main(new String[0])
        );
    }
}
