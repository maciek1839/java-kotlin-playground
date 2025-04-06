package com.showmeyourcode.playground.java.overview.files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReadingFilesOverviewTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ReadingFilesOverview.main(new String[0])
        );
    }
}
