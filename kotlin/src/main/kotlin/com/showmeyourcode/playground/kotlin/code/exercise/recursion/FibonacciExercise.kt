package com.showmeyourcode.playground.kotlin.code.exercise.recursion

fun fibonacci(n: Int): Int {
    return when (n) {
        0 -> 0
        1 -> 1
        else -> fibonacci(n - 2) + fibonacci(n - 1)
    }
}

fun fibonacciTailRecursion(
    n: Int,
    a: Int = 0,
    b: Int = 1
): Int {
    return when (n) {
        0 -> a
        1 -> b
        else -> fibonacciTailRecursion(n - 1, b, a + b)
    }
}
