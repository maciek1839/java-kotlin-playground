package com.showmeyourcode.playground.kotlin.code.exercise.misc

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SubstringFinderExerciseTest : AnnotationSpec() {
    @Test
    fun `should find all substring indexes`() {
        forAll(
            row("Hello, world! Hello again!", "Hello", listOf(0, 14)),
            row("Hello, world!", "Java", emptyList()),
            row("Java programming", "Java", listOf(0)),
            row("Java programming", "programming", listOf(5)),
            row("", "non-empty", emptyList()),
            row("aaaaa", "a", listOf(0, 1, 2, 3, 4)),
            row("a", "a", listOf(0)),
            row("a", "b", emptyList()),
            row("abcXdefXghiX", "X", listOf(3, 7, 11))
        ) { input, substring, expectedIndexes ->
            findAllSubstringIndexes(input, substring) shouldBe expectedIndexes
        }
    }
}
