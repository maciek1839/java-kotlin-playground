package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TuringMachineExerciseTest {

    @Test
    void shouldPrintTapeWithoutErrors() {
        assertDoesNotThrow(() -> new TuringMachineExercise("1100").printTape());
    }

    @ParameterizedTest
    @CsvSource({
            "1,0",
            "10,01",
            "100,011",
            "1100,1011",
            "1110,1101"
    })
    void shouldFindPredecessor(String input, String predecessor) {
        var machine = new TuringMachineExercise(input);

        machine.run();

        assertEquals(predecessor, machine.getTape());
    }
}
