package com.showmeyourcode.playground.java.code.exercise;

public class FibonacciSequence {

    public static void main(String [] args) {
        TailRecursiveFibonacci.main();
        RecursiveFibonacci.main();
    }
}

class RecursiveFibonacci {
    public static void main() {
        System.out.println("Fibonacci of 6: " + fibonacci(6)); // Output: 8
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
}

class TailRecursiveFibonacci {
    public static void main() {
        System.out.println("Tail Recursive Fibonacci of 6: " + fibonacci(6)); // Output: 8
    }

    public static int fibonacci(int n) {
        return fibonacciHelper(n, 0, 1);
    }

    private static int fibonacciHelper(int n, int a, int b) {
        if (n == 0) {
            return a; // Base case: return the nth Fibonacci number
        } else {
            return fibonacciHelper(n - 1, b, a + b); // Tail recursive call
        }
    }
}
