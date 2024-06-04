package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

// To compute the smoothed array, we need to take the average of each value signal[N], signal[N-1],
// and signal[N+1] for each element N in the array signal.
// We'll handle the edge cases separately to average the first two elements for the first element of smooth
// and the last two elements for the last element of smooth.
//
// https://chortle.ccsu.edu/java5/Notes/chap47/progExercises47.html
public class SmoothOperatorExercise {

    public static void main(String[] args) {
        int[] signal  = {5, 5, 4, 5, 6, 6, 7, 6, 5, 4, 1, 4};

        // Compute the smoothed array
        int[] smooth = smoothSignal(signal);

        // Write out the input
        System.out.print("signal: ");
        for (int num : signal) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Write out the result
        System.out.print("smooth: ");
        for (int num : smooth) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Method to compute the smoothed array
    public static int[] smoothSignal(int[] signal) {
        int[] smooth = new int[signal.length];

        // Compute the smoothed value for the first element
        smooth[0] = (signal[0] + signal[1]) / 2;

        // Compute the smoothed value for the last element
        smooth[signal.length - 1] = (signal[signal.length - 2] + signal[signal.length - 1]) / 2;

        // Compute the smoothed values for the middle elements
        for (int i = 1; i < signal.length - 1; i++) {
            smooth[i] = (signal[i - 1] + signal[i] + signal[i + 1]) / 3;
        }

        return smooth;
    }
}
