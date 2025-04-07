package com.showmeyourcode.playground.kotlin.code.exercise.string

object CountVowels {
    fun countVowels(input: String): Int {
        val vowels = "aeiouAEIOU"
        return input.count { it in vowels }
    }

    fun countVowelsRegex(input: String): Int {
        val regex = "[aeiouAEIOU]".toRegex()
        return regex.findAll(input).count()
    }
}
