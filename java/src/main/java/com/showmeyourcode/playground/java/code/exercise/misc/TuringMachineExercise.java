package com.showmeyourcode.playground.java.code.exercise.misc;

import lombok.extern.slf4j.Slf4j;


/**
 * To create a Turing machine in Java to solve the problem of determining the predecessor of a binary number (x-1) on a tape,
 * we need to implement a Turing machine simulator.
 * The Turing machine should be able to handle the binary number on the tape, process it, and output the predecessor.
 * <br>
 * Define the Turing Machine Components:
 * - States: Define the states of the Turing machine.
 * - Tape: The tape where the binary number is written.
 * - Head: The head that reads and writes on the tape.
 * - Transition Function: Defines the transition between states based on the input.
 * <br>
 * Transition Logic:
 * - If the binary number is 1, then the predecessor is 0.
 * - For any other binary number, you need to find the rightmost 1, change it to 0, and change all 0s to the right of it to 1s.
 */
@Slf4j
public class TuringMachineExercise {

    private enum MachineState {
        STATE_0,
        STATE_1,
        STATE_STOP
    }

    private static final char BLANK = ' ';
    private static final char ZERO = '0';
    private static final char ONE = '1';

    private final char[] tape;
    private int head;
    private MachineState state;

    TuringMachineExercise(String input) {
        this.tape = new char[input.length() + 2]; // Adding space for BLANKs
        for (int i = 0; i < tape.length; i++) {
            if (i < input.length()) {
                tape[i] = input.charAt(i);
            } else {
                tape[i] = BLANK;
            }
        }
        this.head = input.length() - 1; // Start from the end of the input
        this.state = MachineState.STATE_0; // Initial state
    }

    public void run() {
        while (!state.equals(MachineState.STATE_STOP)) {
            char currentSymbol = tape[head];
            switch (state) {
                case STATE_0:
                    if (currentSymbol == ONE) {
                        tape[head] = ZERO;
                        state = MachineState.STATE_STOP;
                    } else if (currentSymbol == ZERO) {
                        tape[head] = ONE;
                        head--;
                        state = MachineState.STATE_1;
                    }
                    break;
                case STATE_1:
                    if (currentSymbol == ZERO) {
                        tape[head] = ONE;
                        head--;
                    } else if (currentSymbol == ONE) {
                        tape[head] = ZERO;
                        state = MachineState.STATE_STOP;
                    } else if (currentSymbol == BLANK) {
                        state = MachineState.STATE_STOP;
                    }
                    break;
                default:
                    log.debug("Ignoring state: {}", state);
            }
        }
    }

    public String getTape() {
        return String.valueOf(tape).trim();
    }

    public void printTape() {
        var sb = new StringBuilder();
        for (char c : tape) {
            if (c == BLANK) break;
            sb.append(c);
        }
        log.info("{}", sb);
    }
}
