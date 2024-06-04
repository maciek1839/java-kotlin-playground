package com.showmeyourcode.playground.java.code.exercise;

public class FactorialCalculation {
    public static void main(String[] args) {
        RecursiveFactorial.main();
        TailRecursiveFactorial.main();
    }
}

class RecursiveFactorial {
    public static void main() {
        System.out.println("Factorial of 5: " + factorial(5)); // Output: 120
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1; // Base case: 0! = 1
        } else {
            return n * factorial(n - 1); // Recursive case
        }
    }
}


class TailRecursiveFactorial {
    public static void main() {
        System.out.println("Tail Recursive Factorial of 5: " + factorial(5)); // Output: 120
    }

    public static int factorial(int n) {
        return factorialHelper(n, 1);
    }

    private static int factorialHelper(int n, int accumulator) {
        if (n == 0) {
            return accumulator; // Base case: return the accumulated result
        } else {
            return factorialHelper(n - 1, n * accumulator); // Tail recursive call
        }
    }
}
