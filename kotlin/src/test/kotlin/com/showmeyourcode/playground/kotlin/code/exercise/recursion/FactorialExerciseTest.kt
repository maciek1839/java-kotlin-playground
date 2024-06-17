package com.showmeyourcode.playground.kotlin.code.exercise.recursion

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class FactorialExerciseTest : AnnotationSpec() {
    @Test
    fun `should calculate factorial using recursiveFactorial function`() {
        forAll(
            row(1, 1),
            row(2, 2),
            row(3, 6),
            row(4, 24),
            row(5, 120),
            row(6, 720),
            row(7, 5040)
        ) { input, expected ->
            recursiveFactorial(input) shouldBe expected
        }
    }

    @Test
    fun `should calculate factorial using tailRecursionFactorial function`() {
        forAll(
            row(1, 1),
            row(2, 2),
            row(3, 6),
            row(4, 24),
            row(5, 120),
            row(6, 720),
            row(7, 5040)
        ) { input, expected ->
            tailRecursionFactorial(input) shouldBe expected
        }
    }
}
