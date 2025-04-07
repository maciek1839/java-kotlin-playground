package com.showmeyourcode.playground.kotlin.code.exercise.string

object ReverseString {
    fun reverse(input: String): String {
        return input.reversed()
    }

    // Iterative approach
    fun reverse2(str: String): String {
        var reversed = ""
        str.forEach {
            reversed = it + reversed
        }
        return reversed
    }

    // Recursive approach
    fun reverse3(str: String): String {
        if (str.isEmpty()) {
            return str
        }

        return reverse(str.drop(1)) + str.first()
    }

    // Kotlin fold
    fun reverse4(str: String): String {
        return str.foldRight("") { char, reversed -> reversed + char }
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    // Reverse in place
    fun reverse5(str: String): String {
        val chars = str.toMutableList()

        var leftIndex = 0
        var rightIndex = chars.lastIndex

        while (leftIndex <= rightIndex) {
            val temp = chars[leftIndex]
            chars[leftIndex] = chars[rightIndex]
            chars[rightIndex] = temp

            leftIndex++
            rightIndex--
        }

        return chars.joinToString(transform = { it.toString() }, separator = "")
    }
}
