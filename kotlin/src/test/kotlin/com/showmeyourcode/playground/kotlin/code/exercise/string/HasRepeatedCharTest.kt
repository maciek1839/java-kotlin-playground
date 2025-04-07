package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HasRepeatedCharTest : AnnotationSpec() {
    @Test
    fun `should check if has repeated chars`() {
        forAll(
            row("Hello", true),
            row("World", false),
            row("Kotlin", false),
            row("AEIOU", false),
            row("12345", false)
        ) { input, expected ->
            HasRepeatedChar.hasRepeatedChar(input) shouldBe expected
            HasRepeatedChar.hasRepeatedChar2(input) shouldBe expected
            HasRepeatedChar.hasRepeatedChar3(input) shouldBe expected
        }
    }
}
