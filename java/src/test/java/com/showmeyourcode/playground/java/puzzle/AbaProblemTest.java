package com.showmeyourcode.playground.java.puzzle;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ABAProblemTest {

    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }

    @Test
    void testABAProblemOccurs() {
        String output = captureOutput(() -> AbaProblem.main(new String[]{}));

        assertTrue(output.contains("Thread 1 CAS success: true"),
                "ABA problem should occur, allowing CAS to succeed incorrectly.");
    }

    @Test
    void testABAProblemFixed() {
        String output = captureOutput(() ->ABAProblemSolution.main(new String[]{}));

        assertTrue(output.contains("Thread 1 CAS success: false"),
                "ABA fix should prevent CAS from succeeding incorrectly.");
    }
}
