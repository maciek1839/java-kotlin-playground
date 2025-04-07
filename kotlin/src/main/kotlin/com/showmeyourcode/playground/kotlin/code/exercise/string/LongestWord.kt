package com.showmeyourcode.playground.kotlin.code.exercise.string

object LongestWord {
    fun findLongestWord(str: String): String {
        return str.map { if (it.isLetter()) it else ' ' }
            .joinToString(separator = "")
            .split(" ")
            .filterNot { it.isBlank() }
            .maxBy { it.length }
    }
}
