package com.showmeyourcode.playground.kotlin.code.exercise.string

object SurroundedByPlus {
    fun isSurroundedByPlus(input: String): Boolean {
        // Ensure that there are letters in the string to check
        if (input.isEmpty() || input.none { it.isLetter() }) {
            return false
        }

        // Loop through each character in the string.
        for (i in input.indices) {
            if (input[i].isLetter()) {
                // Check if the letter is surrounded by '+' on both sides (taking care of boundaries).
                val prevIsPlus = i > 0 && input[i - 1] == '+'
                val nextIsPlus = i < input.length - 1 && input[i + 1] == '+'

                // If the letter is not surrounded by '+' characters, return false.
                if (!(prevIsPlus && nextIsPlus)) {
                    return false
                }
            }
        }

        // If all letters are correctly surrounded, return true.
        return true
    }
}
