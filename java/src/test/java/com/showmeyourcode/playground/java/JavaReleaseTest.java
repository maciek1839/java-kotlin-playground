package com.showmeyourcode.playground.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JavaReleaseTest {

    @Test
    void shouldRunJavaReleaseOverviewWithoutErrors(){
        assertDoesNotThrow(() ->
                JavaRelease.main(new String[0])
        );
    }
}
