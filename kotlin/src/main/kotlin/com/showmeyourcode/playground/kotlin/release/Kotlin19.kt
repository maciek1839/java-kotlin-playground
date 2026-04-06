package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Kotlin19 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.9", Descriptions.INDENT1)
        log.info("Kotlin 1.9.0 was released on July 6, 2023.")
        log.info("https://kotlinlang.org/docs/whatsnew19.html")

        openEndedRanges()
        regexNamedGroups()
    }

    private fun openEndedRanges() {
        log.info("\n{} Open-ended ranges (..< operator)", Descriptions.INDENT2)
        log.info(
            """
            The ..< operator creates a range that excludes the upper bound.
            This is useful when you need a range that doesn't include
            the last value, similar to Python's range() or Java's IntStream.range().
            """.trimIndent()
        )

        val numbers = (1..<10).toList()
        log.info("Range 1..<10: {}", numbers)

        val indices = (0..<5).toList()
        log.info("Indices 0..<5: {}", indices)

        // Practical example: iterating over array indices
        val items = arrayOf("Kotlin", "Java", "Scala")
        for (i in 0..<items.size) {
            log.info("  Item[{}]: {}", i, items[i])
        }
    }

    private fun regexNamedGroups() {
        log.info("\n{} Regex named capture groups", Descriptions.INDENT2)
        log.info(
            """
            Kotlin 1.9 adds a common function to create regex capture groups
            by name, making regex more readable and maintainable.
            """.trimIndent()
        )

        val dateRegex = Regex("""(?<year>\d{4})-(?<month>\d{2})-(?<day>\d{2})""")
        val match = dateRegex.find("2023-07-06")
        if (match != null) {
            val year = match.groups["year"]?.value
            val month = match.groups["month"]?.value
            val day = match.groups["day"]?.value
            log.info("Parsed date - year: {}, month: {}, day: {}", year, month, day)
        }
    }
}
