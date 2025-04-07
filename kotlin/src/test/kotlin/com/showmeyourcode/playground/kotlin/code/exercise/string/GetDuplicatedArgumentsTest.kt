package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class GetDuplicatedArgumentsTest : AnnotationSpec() {
    @Test
    fun `should check if has duplicated args`() {
        forAll(
            row("Hello", listOf("l")),
            row("aAeEiIoOuU", listOf("a", "e", "i", "o", "u")),
            // No repeated characters
            row("World", emptyList()),
            row("Kotlin", emptyList()),
            row("AEIOU", emptyList()),
            row("12345", emptyList())
        ) { input, expected ->
            GetDuplicatedArguments.getDuplicatedArguments(input) shouldBe expected
        }
    }
}
