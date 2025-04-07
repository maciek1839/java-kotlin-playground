package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CountVowelsTest : AnnotationSpec() {
    @Test
    fun `should count vowels`() {
        forAll(
            row("Hello", 2),
            row("World", 1),
            row("Kotlin", 2),
            row("AEIOU", 5),
            row("12345", 0),
            row("aAeEiIoOuU", 10)
        ) { input, expected ->
            CountVowels.countVowelsRegex(input) shouldBe expected
            CountVowels.countVowels(input) shouldBe expected
        }
    }
}
