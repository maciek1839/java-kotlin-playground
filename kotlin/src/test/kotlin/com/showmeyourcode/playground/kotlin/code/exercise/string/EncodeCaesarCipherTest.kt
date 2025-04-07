package com.showmeyourcode.playground.kotlin.code.exercise.string

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class EncodeCaesarCipherTest : AnnotationSpec() {
    @Test
    fun `should encode`() {
        forAll(
            row("ABC", "DEF"),
            row("xyz", "abc"),
            row("Hello", "Khoor"),
            row("Kotlin", "Nrwolq"),
            row("Attack at dawn!", "Dwwdfn dw gdzq!"),
            row("123", "456")
        ) { input, expected ->
            val shift = 3
            EncodeCaesarCipher.encodeCaesarCipher(input, shift) shouldBe expected
        }
    }
}
