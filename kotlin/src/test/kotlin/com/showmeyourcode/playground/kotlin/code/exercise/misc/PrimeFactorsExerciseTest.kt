package com.showmeyourcode.playground.kotlin.code.exercise.misc

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PrimeFactorsExerciseTest : AnnotationSpec() {
    @Test
    fun `should throw an exception when an argument is invalid`() {
        forAll(
            row(-10),
            row(1)
        ) { n ->
            shouldThrow<IllegalArgumentException> {
                findPrimeFactors(n)
            }
        }
    }

    @Test
    fun `should find prime factors`() {
        forAll(
            row(2, listOf(2)),
            row(3, listOf(3)),
            row(4, listOf(2, 2)),
            row(5, listOf(5)),
            row(6, listOf(2, 3)),
            row(7, listOf(7)),
            row(8, listOf(2, 2, 2)),
            row(9, listOf(3, 3)),
            row(10, listOf(2, 5)),
            row(12, listOf(2, 2, 3)),
            row(15, listOf(3, 5)),
            row(16, listOf(2, 2, 2, 2)),
            row(17, listOf(17)),
            row(18, listOf(2, 3, 3)),
            row(19, listOf(19)),
            row(20, listOf(2, 2, 5)),
            row(25, listOf(5, 5)),
            row(30, listOf(2, 3, 5)),
            row(36, listOf(2, 2, 3, 3)),
            row(49, listOf(7, 7)),
            row(50, listOf(2, 5, 5)),
            row(72, listOf(2, 2, 2, 3, 3)),
            row(100, listOf(2, 2, 5, 5)),
            row(121, listOf(11, 11)),
            row(144, listOf(2, 2, 2, 2, 3, 3)),
            row(169, listOf(13, 13)),
            row(196, listOf(2, 2, 7, 7)),
            row(225, listOf(3, 3, 5, 5)),
            row(250, listOf(2, 5, 5, 5)),
            row(300, listOf(2, 2, 3, 5, 5)),
            row(400, listOf(2, 2, 2, 2, 5, 5)),
            row(625, listOf(5, 5, 5, 5)),
            row(900, listOf(2, 2, 3, 3, 5, 5)),
            row(1000, listOf(2, 2, 2, 5, 5, 5))
        ) { n, expectedFactors ->
            findPrimeFactors(n) shouldBe expectedFactors
        }
    }
}
