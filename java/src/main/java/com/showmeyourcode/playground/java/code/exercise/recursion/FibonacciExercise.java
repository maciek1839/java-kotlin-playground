package com.showmeyourcode.playground.java.code.exercise.recursion;

/**
 * Calculate n Fibonacci sequence elements using recursion and tail recursion.
 */
public class FibonacciExercise {

    private FibonacciExercise() {
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0; // Base case: fibonacci(0) = 0
        } else if (n == 1) {
            return 1; // Base case: fibonacci(1) = 1
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2); // Recursive case
        }
    }

    public static int fibonacciTailRecursion(int n) {
        return fibonacciHelper(n, 0, 1);
    }

    private static int fibonacciHelper(int n, int number1, int number2) {
        if (n == 0) {
            return number1; // Base case: return the nth Fibonacci number
        } else {
            return fibonacciHelper(n - 1, number2, number1 + number2); // Tail recursive call
        }
    }
}
