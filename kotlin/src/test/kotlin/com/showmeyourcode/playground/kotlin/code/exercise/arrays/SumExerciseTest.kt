package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SumExerciseTest : AnnotationSpec() {
    @Test
    fun `should sum IntArray elements using build-in method`() {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5), 15),
            row(intArrayOf(-1, -2, -3, -4, -5), -15),
            row(intArrayOf(100), 100),
            row(intArrayOf(3, 5, 7, 2, 8), 25)
        ) { input, expected ->
            sum(input) shouldBe expected
        }
    }

    @Test
    fun `should sum array elements using reduce`() {
        forAll(
            row(arrayOf(1, 2, 3, 4, 5), 15),
            row(arrayOf(-1, -2, -3, -4, -5), -15),
            row(arrayOf(100), 100),
            row(arrayOf(3, 5, 7, 2, 8), 25)
        ) { input, expected ->
            sum(input) shouldBe expected
        }
    }
}
