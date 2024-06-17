package com.showmeyourcode.playground.java.code.exercise.arrays;

public class ReversalOfElementsEachRowExercise {

    private ReversalOfElementsEachRowExercise() {
    }

    static void reverseRows(int[][] data) {
        for (int row = 0; row < data.length; row++) {
            int startIdx = 0;
            int endIdx = data[row].length - 1;
            while (startIdx < endIdx) {
                int temp = data[row][startIdx];
                data[row][startIdx] = data[row][endIdx];
                data[row][endIdx] = temp;
                startIdx++;
                endIdx--;
            }
        }
    }
}
