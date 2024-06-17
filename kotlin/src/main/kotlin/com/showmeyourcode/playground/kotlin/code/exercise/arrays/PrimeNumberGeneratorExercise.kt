package com.showmeyourcode.playground.kotlin.code.exercise.arrays

fun generatePrimeNumbers(toInclusive: Int): List<Int> {
    val primes = mutableListOf<Int>()

    fun isPrime(number: Int): Boolean {
        if (number < 2) return false
        for (i in 2 until number) {
            if (number % i == 0) return false
        }
        return true
    }

    for (i in 2..toInclusive) {
        if (isPrime(i)) {
            primes.add(i)
        }
    }
    return primes
}
