package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SurroundedByPlusTest : AnnotationSpec() {
    @Test
    fun `should check if letters are surrounded by plus correctly`() {
        forAll(
            row("+a+b+c+", true),
            row("+a++b+c+", true),
            row("a+b+c+", false),
            row("+a+b+c", false),
            row("++++", false),
            row("+a+b+c+d+e+", true)
        ) { input, expected ->
            SurroundedByPlus.isSurroundedByPlus(input) shouldBe expected
        }
    }
}
