package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap46;

public class SumExercise {
    public static void main ( String[] args ) {
        int[] valA = {13, -22, 82, 17};
        int[] valB = new int[valA.length];

        // Put values into valB so that the sum of the values
        // in corresponding cells of valA and valB is 25.
        calculateValB(valA, valB);

        System.out.println("valB: "
                + valB[0] + " " + valB[1] + " " + valB[2] + " " + valB[3]);

        System.out.println("sum:  "
                + (valA[0] + valB[0]) + " " + (valA[1] + valB[1]) + " "
                + (valA[2] + valB[2]) + " " + (valA[3] + valB[3]));
    }

    // Function to calculate values for valB
    public static void calculateValB(int[] valA, int[] valB) {
        for (int i = 0; i < valA.length; i++) {
            valB[i] = 25 - valA[i];
        }
    }
}
