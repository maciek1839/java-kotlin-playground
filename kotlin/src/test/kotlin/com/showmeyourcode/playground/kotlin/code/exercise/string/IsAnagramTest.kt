package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IsAnagramTest : AnnotationSpec() {
    @Test
    fun `should verify if it is an anagram`() {
        forAll(
            // True anagram cases
            row("listen", "silent", true),
            row("anagram", "nagaram", true),
            row("abcd", "dcba", true),
            // False anagram cases
            row("hello", "world", false),
            row("apple", "pie", false),
            row("race car", "car race", true),
            row("flower", "flwore", true),
            // Edge cases
            row("", "", true),
            row("a", "a", true),
            row("abc", "ab", false)
        ) { str1, str2, expected ->
            IsAnagram.isAnagram(str1, str2) shouldBe expected
            IsAnagram.isAnagram2(str1, str2) shouldBe expected
            IsAnagram.isAnagram3(str1, str2) shouldBe expected
        }
    }
}
