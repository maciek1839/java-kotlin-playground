package com.showmeyourcode.playground.kotlin.code.exercise.string

object EncodeCaesarCipher {
    fun encodeCaesarCipher(
        str: String,
        shift: Int
    ): String {
        return str.map { char ->
            when {
                char.isUpperCase() -> 'A' + (char - 'A' + shift) % 26
                char.isLowerCase() -> 'a' + (char - 'a' + shift) % 26
                char.isDigit() -> '0' + (char - '0' + shift) % 10
                else -> char
            }
        }.joinToString("")
    }
}
