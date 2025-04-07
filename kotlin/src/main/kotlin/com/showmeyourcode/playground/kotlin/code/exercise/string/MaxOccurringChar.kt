package com.showmeyourcode.playground.kotlin.code.exercise.string

object MaxOccurringChar {
    // Kotlin idiomatic solution
    fun maxOccurringChar(str: String): Char? {
        if (str.isBlank()) return null

        return str.toCharArray()
            .groupBy { it }
            .maxBy { it.value.size }
            .key
    }

    // Kotlin idiomatic solution
    fun maxOccurringChar2(str: String): Char? {
        if (str.isBlank()) return null

        return str.toList()
            .groupingBy { it }
            .eachCount()
            .maxBy { it.value }
            .key
    }

    fun maxOccurringChar3(str: String): Char? {
        if (str.isBlank()) return null

        val map = mutableMapOf<Char, Int>()

        str.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        return map.maxBy { it.value }.key
    }

    // Recursive naive approach
    // Time complexity: O(n^2)
    fun maxOccurringChar4(str: String): Char? {
        if (str.length == 1) {
            return str.first()
        }

        str.forEachIndexed { index, c ->
            str.substring(index + 1).forEach {
                if (c == it) {
                    return it
                }
            }
        }

        return null
    }
}
