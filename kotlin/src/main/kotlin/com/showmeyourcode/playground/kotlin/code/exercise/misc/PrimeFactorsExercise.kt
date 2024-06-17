package com.showmeyourcode.playground.kotlin.code.exercise.misc

/**
 * Can this code be improved? Disclaimer: yes
 */
fun findPrimeFactors(n: Int): List<Int> {
    require(n > 1) { "Limit must be at least 2" }

    var inputNumber = n
    val factors = mutableListOf<Int>()
    while (inputNumber % 2 == 0) {
        factors.add(2)
        inputNumber /= 2
    }

    for (i in 3..inputNumber) {
        while (inputNumber % i == 0) {
            factors.add(i)
            inputNumber /= i
        }
    }

    return factors
}
