package com.showmeyourcode.playground.kotlin.code.exercise.string

object IsPalindrome {
    fun isPalindrome(str: String): Boolean {
        var characterRemoved = false
        var leftIndex = 0
        var rightIndex = str.lastIndex
        while (leftIndex <= rightIndex) {
            if (str[leftIndex] != str[rightIndex]) {
                if (characterRemoved) {
                    return false
                }
                characterRemoved = true
                when {
                    str[leftIndex + 1] == str[rightIndex] -> leftIndex++
                    str[leftIndex] == str[rightIndex - 1] -> rightIndex--
                    else -> return false
                }
            }
            leftIndex++
            rightIndex--
        }
        return true
    }

    // Recursive solution
    fun isPalindrome2(
        str: String,
        characterRemoved: Boolean = false
    ): Boolean {
        // Helper function to clean and prepare string
        fun cleanString(input: String): String {
            return input.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        }

        val cleanedStr = cleanString(str)

        // Base cases for recursion
        if (cleanedStr.isEmpty() || cleanedStr.length == 1) {
            return true
        }

        // Check if the first and last characters are the same
        return if (cleanedStr.first() == cleanedStr.last()) {
            // Continue with the inner substring
            isPalindrome2(cleanedStr.substring(1 until cleanedStr.length - 1), characterRemoved)
        } else {
            // If characters don't match and we haven't removed a character yet
            if (characterRemoved) {
                false
            } else {
                // Try removing the left or the right character and check both cases
                val removeLeftResult = isPalindrome2(cleanedStr.substring(1 until cleanedStr.length), true)
                val removeRightResult = isPalindrome2(cleanedStr.substring(0 until cleanedStr.length - 1), true)

                removeLeftResult || removeRightResult
            }
        }
    }

    // recursive solution 2
    fun isPalindrome3(
        str: String,
        characterRemoved: Boolean = false
    ): Boolean {
        val revStr = str.reversed()
        if (revStr == str) return true
        if (characterRemoved) return false

        // Remove a single non-matching character and re-compare
        val removeIndex = str.commonPrefixWith(revStr).length
        if (removeIndex + 1 > str.length) return false // reached end of string
        val reducedStr = str.removeRange(removeIndex, removeIndex + 1)
        return if (isPalindrome3(reducedStr, true)) {
            true
        } else {
            val reducedRevStr = revStr.removeRange(removeIndex, removeIndex + 1)
            isPalindrome3(reducedRevStr, true)
        }
    }
}
