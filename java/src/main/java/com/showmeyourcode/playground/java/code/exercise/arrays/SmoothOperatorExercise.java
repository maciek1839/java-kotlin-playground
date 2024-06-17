package com.showmeyourcode.playground.java.code.exercise.arrays;

/**
 * To compute the smoothed array, we need to take the average of each value
 * signal[N], signal[N-1], and signal[N+1] for each element N in the array signal.
 * We'll handle the edge cases separately to average the first two elements for the first element of smooth
 * and the last two elements for the last element of smooth.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap47/progExercises47.html">Exercise source</a>
 */
public class SmoothOperatorExercise {

    private SmoothOperatorExercise() {
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
