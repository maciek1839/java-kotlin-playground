package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap47;

public class SumOfEvenOddAllElementsExercise {
    public static void main ( String[] args )
    {
        int[] data = {3, 2, 5, 7, 9, 12, 97, 24, 54};

        // Calculate the sums
        int totalSum = calculateTotalSum(data);
        int evenSum = calculateEvenSum(data);
        int oddSum = calculateOddSum(data);

        // Write out the three sums
        System.out.println("Sum of all elements: " + totalSum);
        System.out.println("Sum of even elements: " + evenSum);
        System.out.println("Sum of odd elements: " + oddSum);
    }

    public static int calculateTotalSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int calculateEvenSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (isEven(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static int calculateOddSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (!isEven(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
