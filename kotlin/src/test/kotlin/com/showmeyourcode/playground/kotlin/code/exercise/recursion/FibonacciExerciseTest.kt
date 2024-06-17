package com.showmeyourcode.playground.kotlin.code.exercise.recursion

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FibonacciExerciseTest : AnnotationSpec() {
    @Test
    fun `should calculate fibonacci nth number using fibonacci function`() {
        forAll(
            row(0, 0),
            row(1, 1),
            row(2, 1),
            row(3, 2),
            row(4, 3),
            row(5, 5),
            row(6, 8),
            row(7, 13),
            row(8, 21),
            row(9, 34),
            row(10, 55)
        ) { input, expected ->
            fibonacci(input) shouldBe expected
        }
    }

    @Test
    fun `should calculate fibonacci using fibonacciTailRecursion function`() {
        forAll(
            row(0, 0),
            row(1, 1),
            row(2, 1),
            row(3, 2),
            row(4, 3),
            row(5, 5),
            row(6, 8),
            row(7, 13),
            row(8, 21),
            row(9, 34),
            row(10, 55)
        ) { input, expected ->
            fibonacciTailRecursion(input) shouldBe expected
        }
    }
}
