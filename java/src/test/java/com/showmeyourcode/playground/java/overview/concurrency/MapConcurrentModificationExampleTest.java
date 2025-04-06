package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;


import static org.junit.jupiter.api.Assertions.assertThrows;

class MapConcurrentModificationExampleTest {

    @Test
    void shouldThrowConcurrentModificationException(){
        assertThrows(ConcurrentModificationException.class,
                () ->
                MapConcurrentModificationExample.main(new String[0])
        );
    }
}
