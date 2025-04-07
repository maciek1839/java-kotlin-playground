package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IsSubstringTest : AnnotationSpec() {
    @Test
    fun `should verify if the string contains the given substring`() {
        forAll(
            row("flower", "flower", true),
            row("flower is growing fast", "growing", true),
            row("This is my jeep", "This", true),
            row("Home!@&#sweet home", "sweet", true),
            // Test cases where the second string is NOT a substring of the first
            row("flower", "roses", false),
            row("apple pie", "banana", false),
            row("This is my jeep", "car", false),
            row("Home!@&#sweet home", "salty", false),
            // Edge cases:
            row("", "empty", false),
            row("nonempty", "", true),
            row("hello", "e", true),
            row("hello", "z", false),
            row("abc123!@#", "123", true)
        ) { input, substring, expected ->
            IsSubstring.isSubstring(input, substring) shouldBe expected
            IsSubstring.isSubstring2(input, substring) shouldBe expected
            IsSubstring.isSubstring3(input, substring) shouldBe expected
            IsSubstring.isSubstring4(input, substring) shouldBe expected
        }
    }
}
