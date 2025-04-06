package com.showmeyourcode.playground.kotlin.code.exercise.arrays

/**
 * Task: Implement the Sieve of Eratosthenes algorithm.
 *
 * Description:
 * The Sieve of Eratosthenes is an efficient algorithm to find all prime numbers up to a given limit `n`.
 * A prime number is a natural number greater than 1 that is not divisible by any number other than 1 and itself.
 *
 */
fun sieveOfEratosthenes(n: Int): List<Int> {
    if (n < 2) return emptyList()
    val primes = BooleanArray(n + 1) { true }
    primes[0] = false
    primes[1] = false

    for (i in 2..n) {
        if (primes[i]) {
            for (j in i * 2..n step i) {
                primes[j] = false
            }
        }
    }
    return primes.withIndex().filter { it.value }.map { it.index }
}

// A sequence that returns values through its iterator.
// The values are evaluated lazily, and the sequence is potentially infinite.
val primes: Sequence<Int> =
    sequence {
        var numbers = generateSequence(2) { it + 1 }

        while (true) {
            val prime = numbers.first()
            yield(prime)
            numbers =
                numbers.drop(1)
                    .filter { it % prime != 0 }
        }
    }
