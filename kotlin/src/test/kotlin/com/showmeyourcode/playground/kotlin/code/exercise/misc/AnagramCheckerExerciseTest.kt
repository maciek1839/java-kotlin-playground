package com.showmeyourcode.playground.kotlin.code.exercise.misc

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class AnagramCheckerExerciseTest : AnnotationSpec() {
    @Test
    fun `should check if two strings are anagram`() {
        forAll(
            row("aba", "baa", true),
            row("hello", "ollhe", true),
            row("listen", "silent", true),
            row("restful", "fluster", true),
            row("abcdefg", "gfedcba", true),
            row("aab", "abb", false),
            row("hello", "ollhh", false),
            row("hello", "world", false),
            row("abc", "def", false)
        ) { s1, s2, isAnagram ->
            isAnagram(s1, s2) shouldBe isAnagram
        }
    }

    @Test
    fun `should check if two strings are anagram using grouping`() {
        forAll(
            row("aba", "baa", true),
            row("hello", "ollhe", true),
            row("listen", "silent", true),
            row("restful", "fluster", true),
            row("abcdefg", "gfedcba", true),
            row("aab", "abb", false),
            row("hello", "ollhh", false),
            row("hello", "world", false),
            row("abc", "def", false)
        ) { s1, s2, isAnagram ->
            isAnagramUsingGrouping(s1, s2) shouldBe isAnagram
        }
    }
}
