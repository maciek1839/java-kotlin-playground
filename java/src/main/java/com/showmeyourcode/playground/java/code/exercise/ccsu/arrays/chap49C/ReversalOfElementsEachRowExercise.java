package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49C;

public class ReversalOfElementsEachRowExercise {
    static int[][] data = { {3, 2, 5},
            {1, 4, 4, 8, 13},
            {9, 1, 0, 2},
            {0, 2, 6, 3, -1, 8},
            {-1, -2, -3, 4, 5, 45},
            {56},
            {0, 1, 2, 3, 4, 5, 6, 7} };

    private static void printArray() {
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length - 1; col++)
                System.out.print(data[row][col] + ", ");
            System.out.println(data[row][data[row].length - 1]);
        }
    }

    private static void reverseRows() {
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

    public static void main(String[] args) {
        // print out the initial array
        System.out.println("Initial Array:");
        printArray();
        System.out.println();

        // reverse each row
        reverseRows();

        // print out the reversed array
        System.out.println("Reversed Array:");
        printArray();
    }
}
