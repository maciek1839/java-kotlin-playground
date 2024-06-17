package com.showmeyourcode.playground.kotlin.code.exercise.recursion

fun recursiveFactorial(n: Int): Int =
    if (n == 1) {
        1
    } else {
        n * recursiveFactorial(n - 1)
    }

fun tailRecursionFactorial(
    n: Int,
    accumulator: Int = 1
): Int =
    if (n == 1) {
        accumulator
    } else {
        tailRecursionFactorial(n - 1, n * accumulator)
    }
