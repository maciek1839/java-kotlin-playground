package com.showmeyourcode.playground.kotlin.code.exercise.string

object GetDuplicatedArguments {
    // Time complexity: O(n)
    // Determine the frequency of each argument and then filter arguments with frequency > 1
    fun getDuplicatedArguments(input: String): List<String> {
        val cleanedStr = input.lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
        val duplicates =
            cleanedStr.groupingBy { it }.eachCount()
                .filter { it.value > 1 } // Only keep the characters that appear more than once
                .keys
                .map { it.toString() } // Convert each Char to String
        return duplicates
    }
}
