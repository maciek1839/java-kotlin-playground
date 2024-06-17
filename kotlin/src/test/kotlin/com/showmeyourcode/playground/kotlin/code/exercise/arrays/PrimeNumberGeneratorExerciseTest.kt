package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PrimeNumberGeneratorExerciseTest : AnnotationSpec() {
    @Test
    fun `should generate prime numbers`() {
        forAll(
            row(0, emptyList()),
            row(1, emptyList()),
            row(2, listOf(2)),
            row(10, listOf(2, 3, 5, 7)),
            row(20, listOf(2, 3, 5, 7, 11, 13, 17, 19)),
            row(30, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)),
            row(40, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)),
            row(50, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)),
            row(60, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59)),
            row(70, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67)),
            row(80, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79)),
            row(90, listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89)),
            row(
                100,
                listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
            )
        ) { input, expected ->
            generatePrimeNumbers(input) shouldBe expected
        }
    }
}
