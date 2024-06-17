package com.showmeyourcode.playground.kotlin.code.exercise.arrays

fun sum(arr: IntArray): Int = arr.sum()

fun sum(arr: Array<Int>): Int = arr.reduce { acc, element -> acc + element }
