package com.showmeyourcode.playground.kotlin.code.exercise.string

object IsAnagram {
    fun isAnagram(
        str1: String,
        str2: String
    ): Boolean {
        val a1 = str1.uppercase().filter { it.isLetter() }.groupBy { it }
        val a2 = str2.uppercase().filter { it.isLetter() }.groupBy { it }
        return a1 == a2
    }

    fun isAnagram2(
        str1: String,
        str2: String
    ): Boolean {
        return getCharFrequency(str1) == getCharFrequency(str2)
    }

    private fun getCharFrequency(str: String): Map<Char, List<Char>> {
        return str.lowercase()
            .filter { it.isLetterOrDigit() }
            .groupBy { it }
    }

    fun isAnagram3(
        str1: String,
        str2: String
    ): Boolean {
        return getCharFrequency3(str1) == getCharFrequency3(str2)
    }

    private fun getCharFrequency3(str: String): Map<Char, Int> {
        return str.lowercase()
            .filter { it.isLetterOrDigit() }
            .groupingBy { it }
            .eachCount()
    }
}
