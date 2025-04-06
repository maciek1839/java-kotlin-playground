package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProducerConsumerProblemTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() ->
                ProducerConsumerProblem.main(new String[0])
        );
    }
}
