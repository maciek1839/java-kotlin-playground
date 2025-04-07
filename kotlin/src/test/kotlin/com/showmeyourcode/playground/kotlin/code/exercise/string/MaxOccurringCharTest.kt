package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MaxOccurringCharTest : AnnotationSpec() {
    @Test
    fun `should find the most occurring char`() {
        forAll(
            row("", null),
            row("a", 'a'),
            row("abcdefghijklmnaaaaa", 'a'),
            row("ab1c1d1e1f1g1", '1')
        ) { input, expected ->
            MaxOccurringChar.maxOccurringChar(input) shouldBe expected
            MaxOccurringChar.maxOccurringChar2(input) shouldBe expected
            MaxOccurringChar.maxOccurringChar3(input) shouldBe expected
            MaxOccurringChar.maxOccurringChar4(input) shouldBe expected
        }
    }
}
