package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinMaxFinderExerciseTest : AnnotationSpec() {
    @Test
    fun `should find the minimum value in an array`() {
        forAll(
            row(arrayOf(1, 2, 3, 4, 5), 1),
            row(arrayOf(5, 4, 3, 2, 1), 1),
            row(arrayOf(-1, -2, -3, -4, -5), -5),
            row(arrayOf(100), 100),
            row(arrayOf(3, 5, 7, 2, 8), 2)
        ) { input, expected ->
            findMin(input) shouldBe expected
        }
    }

    @Test
    fun `should return an error if an array is empty when finding the minimum element`() {
        val exception =
            shouldThrow<IllegalArgumentException> {
                findMin(arrayOf())
            }
        exception.message shouldBe "Array must not be empty"
    }

    @Test
    fun `should find the maximum value in an array`() {
        forAll(
            row(intArrayOf(1, 2, 3, 4, 5), 5),
            row(intArrayOf(5, 4, 3, 2, 1), 5),
            row(intArrayOf(-1, -2, -3, -4, -5), -1),
            row(intArrayOf(100), 100),
            row(intArrayOf(3, 5, 7, 2, 8), 8)
        ) { input, expected ->
            findMax(input) shouldBe expected
        }
    }

    @Test
    fun `should return an error if an array is empty when finding the maximum element`() {
        val exception =
            shouldThrow<IllegalArgumentException> {
                findMax(intArrayOf())
            }
        exception.message shouldBe "Array must not be empty"
    }
}
