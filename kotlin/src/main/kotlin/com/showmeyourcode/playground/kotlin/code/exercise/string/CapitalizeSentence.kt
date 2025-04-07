package com.showmeyourcode.playground.kotlin.code.exercise.string

object CapitalizeSentence {
    // Kotlin idiomatic solution
    fun capitalizeSentence(str: String): String {
        return str
            .split(" ")
            .joinToString(" ") { string ->
                string.replaceFirstChar { it.uppercase() }
            }
    }

    // Iterative solution
    fun capitalizeSentence2(str: String): String {
        val words = mutableListOf<String>()

        str.split(" ").forEach {
            words.add(it[0].uppercase() + it.substring(1))
        }

        return words.joinToString(" ")
    }
}
