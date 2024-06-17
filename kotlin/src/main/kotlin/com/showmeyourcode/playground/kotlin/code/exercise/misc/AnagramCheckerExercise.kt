package com.showmeyourcode.playground.kotlin.code.exercise.misc

fun isAnagram(
    str1: String,
    str2: String
): Boolean {
    if (str1.length != str2.length) {
        return false
    }

    // Create mutable maps to count characters in each string
    val charCount1 = mutableMapOf<Char, Int>()
    val charCount2 = mutableMapOf<Char, Int>()

    // Populate charCount1 with character counts from str1
    for (char in str1) {
        charCount1[char] = charCount1.getOrDefault(char, 0) + 1
    }

    // Populate charCount2 with character counts from str2
    for (char in str2) {
        charCount2[char] = charCount2.getOrDefault(char, 0) + 1
    }

    // Compare character counts in both maps
    return charCount1 == charCount2
}

fun isAnagramUsingGrouping(
    s1: String,
    s2: String
): Boolean {
    if (s1.length != s2.length) {
        return false
    }

    val charCount1 = s1.groupingBy { it }.eachCount()
    val charCount2 = s2.groupingBy { it }.eachCount()

    return charCount1 == charCount2
}
