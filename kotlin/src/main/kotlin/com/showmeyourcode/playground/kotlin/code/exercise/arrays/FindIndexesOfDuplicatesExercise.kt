package com.showmeyourcode.playground.kotlin.code.exercise.arrays

import java.util.stream.IntStream

fun findIndexesOfDuplicates(arr: IntArray): Map<Int, List<Int>> {
    val duplicates = hashMapOf<Int, List<Int>>()
    for ((index, value) in arr.withIndex()) {
        duplicates[value] = (duplicates[value] ?: listOf()) + index
    }
    return duplicates.filter { e -> e.value.size > 1 }
}

fun findIndexesOfDuplicatesUsingGroupBy(arr: IntArray): Map<Int, List<Int>> {
    return IntStream.range(0, arr.size).toArray()
        .groupBy { idx -> arr[idx] }
        .filter { e -> e.value.size > 1 }
}
