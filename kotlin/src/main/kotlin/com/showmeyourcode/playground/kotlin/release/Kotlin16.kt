package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

private enum class Direction { NORTH, SOUTH, EAST, WEST }

object Kotlin16 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.6", Descriptions.INDENT1)
        log.info("Kotlin 1.6.0 was released on November 16, 2021.")
        log.info("https://kotlinlang.org/docs/whatsnew16.html")

        exhaustiveWhenStatements()
        suspendingFunctionConversions()
        durationApiStable()
    }

    private fun exhaustiveWhenStatements() {
        log.info("\n{} Stable exhaustive when statements", Descriptions.INDENT2)
        log.info(
            """
            Exhaustive when statements over sealed classes and enum classes
            are now stable. The compiler warns when a 'when' statement on
            a sealed class or enum is not exhaustive, ensuring all cases
            are handled. This warning will become an error in future releases.
            """.trimIndent()
        )

        // Exhaustive when - compiler ensures all cases are covered
        fun describe(direction: Direction): String =
            when (direction) {
                Direction.NORTH -> "Going up"
                Direction.SOUTH -> "Going down"
                Direction.EAST -> "Going right"
                Direction.WEST -> "Going left"
            }

        Direction.entries.forEach { log.info("  {} -> {}", it, describe(it)) }
    }

    private fun suspendingFunctionConversions() {
        log.info("\n{} Stable conversions from regular to suspend functional types", Descriptions.INDENT2)
        log.info(
            """
            Conversions from regular to suspend functional types are now stable.
            You can pass a regular function where a suspend function is expected.
            The compiler automatically wraps it in a suspend function.
            """.trimIndent()
        )

        fun regularFunction(x: Int): Int = x * 2

        // Regular function used where suspend is expected
        val suspendFunction: suspend (Int) -> Int = ::regularFunction
        log.info("Regular-to-suspend conversion works for: {}", suspendFunction)
    }

    private fun durationApiStable() {
        log.info("\n{} Stable Duration API (kotlin.time)", Descriptions.INDENT2)
        log.info(
            """
            The Duration and DurationUnit APIs in kotlin.time are now stable.
            Duration represents an amount of time and supports arithmetic,
            comparison, and conversion between time units.
            """.trimIndent()
        )

        val duration1 = Duration.parse("1h 30m")
        val duration2 = Duration.parse("45m")
        val total = duration1 + duration2

        log.info("Duration 1: {}", duration1)
        log.info("Duration 2: {}", duration2)
        log.info("Total: {} (in minutes: {})", total, total.inWholeMinutes)

        // Duration construction using extension properties
        val fiveSeconds = 5.seconds
        val tenMinutes = 10.minutes
        log.info("Five seconds in milliseconds: {}", fiveSeconds.inWholeMilliseconds)
        log.info("Ten minutes in seconds: {}", tenMinutes.inWholeSeconds)
    }
}
