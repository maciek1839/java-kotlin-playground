package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SieveOfEratosthenesTest : AnnotationSpec() {
    @Test
    fun `should return correct prime numbers`() {
        forAll(
            row(10 to listOf(2, 3, 5, 7)),
            row(20 to listOf(2, 3, 5, 7, 11, 13, 17, 19)),
            row(1 to emptyList()),
            row(2 to listOf(2))
        ) { (input, expected) ->
            sieveOfEratosthenes(input) shouldBe expected
        }
    }

    @Test
    fun `verify first 10 primes`() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
        primes.take(expectedPrimes.size).toList() shouldBe expectedPrimes
    }

    @Test
    fun `verify primes up to 50`() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)
        primes.takeWhile { it <= 50 }.toList() shouldBe expectedPrimes
    }

    @Test
    fun `verify primes using parameterized tests`() {
        forAll(
            row(5 to listOf(2, 3, 5, 7, 11)),
            row(10 to listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)),
            row(15 to listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
        ) { (count, expected) ->
            primes.take(count).toList() shouldBe expected
        }
    }
}
