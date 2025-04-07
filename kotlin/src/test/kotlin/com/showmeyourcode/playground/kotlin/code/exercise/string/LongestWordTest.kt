package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LongestWordTest : AnnotationSpec() {
    @Test
    fun `should count vowels`() {
        forAll(
            row("flower", "flower"),
            row("flower is growing fast", "growing"),
            row("This is my jeep", "This"),
            row("Home!@&#sweet home", "sweet")
        ) { input, expected ->
            LongestWord.findLongestWord(input) shouldBe expected
        }
    }
}
