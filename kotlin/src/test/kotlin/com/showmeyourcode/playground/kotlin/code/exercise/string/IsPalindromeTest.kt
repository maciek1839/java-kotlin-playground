package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IsPalindromeTest : AnnotationSpec() {
    @Test
    fun `should verify if it is a palindrome`() {
        forAll(
            // True palindrome cases
            row("madam", true),
            row("racecar", true),
            // row("A man, a plan, a canal, Panama", true),
            // row("Was it a car or a cat I saw?", true),
            // row("No 'x' in Nixon", true),
            // False palindrome cases
            row("hello", false),
            row("flower", false),
            row("apple pie", false),
            row("race car", true),
            // Edge cases
            row("", true),
            row("a", true)
            // row("ab", false)
        ) { input, expected ->
            IsPalindrome.isPalindrome(input) shouldBe expected
            IsPalindrome.isPalindrome2(input) shouldBe expected
            IsPalindrome.isPalindrome3(input) shouldBe expected
        }
    }
}
