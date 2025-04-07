package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ReverseStringTest : AnnotationSpec() {
    @Test
    fun `should reverse strings correctly`() {
        forAll(
            row("hello", "olleh"),
            row("Kotlin", "niltoK"),
            row("abc", "cba"),
            row("12345", "54321"),
            row("racecar", "racecar"),
            row("a", "a"),
            row("", "")
        ) { input, expected ->
            ReverseString.reverse(input) shouldBe expected
            ReverseString.reverse2(input) shouldBe expected
            ReverseString.reverse3(input) shouldBe expected
            ReverseString.reverse4(input) shouldBe expected
            ReverseString.reverse5(input) shouldBe expected
        }
    }
}
