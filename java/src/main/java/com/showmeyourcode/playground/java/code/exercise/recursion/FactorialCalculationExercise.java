package com.showmeyourcode.playground.java.code.exercise.recursion;

/**
 * Calculate factorial using recursion and tail recursion.
 * <br>
 * <br>
 * Factorial calculation in software programming refers to computing the product of all positive integers from 1 up to a given number n.
 * <br>
 * It is denoted by n! and is defined as: n!=n*(n-1)*(n-2)*...*2*1
 * <br>
 * <br>
 */
public class FactorialCalculationExercise {

    private FactorialCalculationExercise() {
    }

    public static int recursiveFactorial(int n) {
        if (n == 0) {
            return 1; // Base case: 0! = 1
        } else {
            return n * recursiveFactorial(n - 1); // Recursive case
        }
    }

    public static int tailRecursiveFactorial(int n, int accumulator) {
        if (n == 0) {
            return accumulator; // Base case: return the accumulated result
        } else {
            return tailRecursiveFactorial(n - 1, n * accumulator); // Tail recursive call
        }
    }
}
