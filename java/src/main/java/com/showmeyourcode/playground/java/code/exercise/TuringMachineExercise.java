package com.showmeyourcode.playground.java.code.exercise;

// To create a Turing machine in Java to solve the problem of determining the predecessor of a binary number (x-1) on a tape, we need to implement a Turing machine simulator. The Turing machine should be able to handle the binary number on the tape, process it, and output the predecessor.
//
// Define the Turing Machine Components:
//- States: Define the states of the Turing machine.
//- Tape: The tape where the binary number is written.
//- Head: The head that reads and writes on the tape.
//- Transition Function: Defines the transition between states based on the input.
//
//Transition Logic:
//- If the binary number is 1, then the predecessor is 0.
//- For any other binary number, you need to find the rightmost 1, change it to 0, and change all 0s to the right of it to 1s.
public class TuringMachineExercise {
    private static final char BLANK = ' ';
    private static final char ZERO = '0';
    private static final char ONE = '1';

    private char[] tape;
    private int head;
    private String state;

    public TuringMachineExercise(String input) {
        this.tape = new char[input.length() + 2]; // Adding space for BLANKs
        for (int i = 0; i < tape.length; i++) {
            if (i < input.length()) {
                tape[i] = input.charAt(i);
            } else {
                tape[i] = BLANK;
            }
        }
        this.head = input.length() - 1; // Start from the end of the input
        this.state = "q0"; // Initial state
    }

    public void run() {
        while (!state.equals("HALT")) {
            char currentSymbol = tape[head];
            switch (state) {
                case "q0":
                    if (currentSymbol == ONE) {
                        tape[head] = ZERO;
                        state = "HALT";
                    } else if (currentSymbol == ZERO) {
                        tape[head] = ONE;
                        head--;
                        state = "q1";
                    }
                    break;

                case "q1":
                    if (currentSymbol == ZERO) {
                        tape[head] = ONE;
                        head--;
                    } else if (currentSymbol == ONE) {
                        tape[head] = ZERO;
                        state = "HALT";
                    } else if (currentSymbol == BLANK) {
                        state = "HALT";
                    }
                    break;
            }
        }
    }

    public void printTape() {
        for (char c : tape) {
            if (c == BLANK) break;
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test case
        String input = "1100"; // binary number
        TuringMachineExercise tm = new TuringMachineExercise(input);
        System.out.println("Input: " + input);
        tm.run();
        System.out.print("Output: ");
        tm.printTape();
    }
}
