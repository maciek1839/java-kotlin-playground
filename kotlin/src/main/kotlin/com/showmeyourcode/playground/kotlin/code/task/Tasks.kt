package com.showmeyourcode.playground.kotlin.code.task

object Tasks {
    fun main() {
        println("Tail Recursive Factorial of 5: ${factorial(5)}") // Output: 120

        println("Tail Recursive Fibonacci of 6: ${fibonacci(6)}") // Output: 8
        println("Tail Recursive Fibonacci of 6: ${nonTailRecursiveFibonacci(6)}") // Output: 8
    }
}

tailrec fun factorial(
    n: Int,
    accumulator: Int = 1
): Int {
    return if (n == 0) {
        accumulator
    } else {
        factorial(n - 1, n * accumulator)
    }
}

tailrec fun fibonacci(
    n: Int,
    a: Int = 0,
    b: Int = 1
): Int {
    return when (n) {
        0 -> a
        1 -> b
        else -> fibonacci(n - 1, b, a + b)
    }
}

fun nonTailRecursiveFibonacci(n: Int): Int {
    return when (n) {
        0 -> 0
        1 -> 1
        else -> nonTailRecursiveFibonacci(n - 1) + nonTailRecursiveFibonacci(n - 2)
    }
}
