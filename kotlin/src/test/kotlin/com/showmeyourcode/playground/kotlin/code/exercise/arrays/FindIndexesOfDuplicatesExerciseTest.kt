package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FindIndexesOfDuplicatesExerciseTest : AnnotationSpec() {
    @Test
    fun `should find indexes of duplicates`() {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5, 5, 3, 3, 10), mapOf(5 to listOf(4, 5), 3 to listOf(2, 6, 7))),
            row(intArrayOf(5, 4, 3, 5, 2, 1, 5), mapOf(5 to listOf(0, 3, 6))),
            row(intArrayOf(-1, -2, -3, -4, -5), mapOf()),
            row(intArrayOf(100, 100), mapOf(100 to listOf(0, 1))),
            row(intArrayOf(3, 5, 7, 2, 8), mapOf())
        ) { input, expected ->
            findIndexesOfDuplicates(input) shouldBe expected
        }
    }

    @Test
    fun `should find indexes of duplicates using groupBy`() {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5, 5, 3, 3, 10), mapOf(5 to listOf(4, 5), 3 to listOf(2, 6, 7))),
            row(intArrayOf(5, 4, 3, 5, 2, 1, 5), mapOf(5 to listOf(0, 3, 6))),
            row(intArrayOf(-1, -2, -3, -4, -5), mapOf()),
            row(intArrayOf(100, 100), mapOf(100 to listOf(0, 1))),
            row(intArrayOf(3, 5, 7, 2, 8), mapOf())
        ) { input, expected ->
            findIndexesOfDuplicatesUsingGroupBy(input) shouldBe expected
        }
    }
}
