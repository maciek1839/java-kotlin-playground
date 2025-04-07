package com.showmeyourcode.playground.kotlin.code.exercise.string

object HasRepeatedChar {
    // Time complexity: O(n)
    fun hasRepeatedChar(str: String): Boolean {
        val frequency = str.groupingBy { it }.eachCount()
        return frequency.any { it.value > 1 }
    }

    // Time complexity: O(n)
    fun hasRepeatedChar2(str: String): Boolean {
        val frequency = str.groupingBy { it }.eachCount()

        frequency.forEach {
            if (it.value > 1) {
                return true
            }
        }

        return false
    }

    fun hasRepeatedChar3(str: String): Boolean {
        val cleanedStr = str.lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
        return cleanedStr.groupingBy { it }.eachCount().any { it.value > 1 }
    }
}
