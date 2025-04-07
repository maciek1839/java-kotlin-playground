package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CapitalizeSentenceTest : AnnotationSpec() {
    @Test
    fun `should capitalize a sentence`() {
        forAll(
            row("flower", "Flower"),
            row("this is a house", "This Is A House")
        ) { input, expected ->
            CapitalizeSentence.capitalizeSentence(input) shouldBe expected
            CapitalizeSentence.capitalizeSentence2(input) shouldBe expected
        }
    }
}
