package com.showmeyourcode.playground.kotlin.code.exercise.arrays

fun findMin(arr: Array<Int>): Int {
    require(arr.isNotEmpty()) { "Array must not be empty" }
    return arr.min()
}

fun findMax(arr: IntArray): Int {
    require(arr.isNotEmpty()) { "Array must not be empty" }
    return arr.max()
}
