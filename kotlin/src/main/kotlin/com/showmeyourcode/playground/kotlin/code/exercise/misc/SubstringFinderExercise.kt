package com.showmeyourcode.playground.kotlin.code.exercise.misc

fun findAllSubstringIndexes(
    str1: String,
    substring: String
): List<Int> {
    val indexes = mutableListOf<Int>()

    var idx = str1.indexOf(substring)
    while (idx >= 0) {
        indexes.add(idx)
        idx = str1.indexOf(substring, idx + substring.length)
    }

    return indexes
}
