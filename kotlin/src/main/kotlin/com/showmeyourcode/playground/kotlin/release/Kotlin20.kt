package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

private data object AppConfig {
    val version = "2.0"
}

private enum class Priority { LOW, MEDIUM, HIGH, CRITICAL }

object Kotlin20 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 2.0", Descriptions.INDENT1)
        log.info("Kotlin 2.0.0 was released on May 21, 2024.")
        log.info("https://kotlinlang.org/docs/whatsnew20.html")

        k2Compiler()
        dataObjects()
        enumEntries()
        autoCloseable()
    }

    private fun k2Compiler() {
        log.info("\n{} K2 Compiler", Descriptions.INDENT2)
        log.info(
            """
            The Kotlin K2 compiler is now Stable. It brings major performance
            improvements to the Kotlin compiler, with up to 2x speedup in
            compilation times. The K2 compiler also lays the foundation for
            future language features.
            """.trimIndent()
        )
    }

    private fun dataObjects() {
        log.info("\n{} Data objects", Descriptions.INDENT2)
        log.info(
            """
            Data objects provide a clean toString() representation for singletons.
            Unlike regular objects, data objects print their name rather than a hash.
            """.trimIndent()
        )

        log.info("Data object toString: {}", AppConfig)
        log.info("Data object version: {}", AppConfig.version)
    }

    private fun enumEntries() {
        log.info("\n{} Enum entries property", Descriptions.INDENT2)
        log.info(
            """
            The 'entries' property replaces the synthetic 'values()' function for enum classes.
            It returns a pre-allocated immutable list of enum constants,
            which is more performant and type-safe than values() which creates a new array each time.
            """.trimIndent()
        )

        // New way (preferred) - returns EnumEntries<Priority> (immutable list)
        val allPriorities = Priority.entries
        log.info("Priorities (entries): {}", allPriorities)

        // Old way (still works but discouraged) - creates a new array each time
        @Suppress("EnumValuesSoftDeprecate")
        val allPrioritiesOld = Priority.values()
        log.info("Priorities (values): {}", allPrioritiesOld.toList())
    }

    private fun autoCloseable() {
        log.info("\n{} Stable AutoCloseable interface", Descriptions.INDENT2)
        log.info(
            """
            The AutoCloseable interface is now stable in common Kotlin,
            allowing multiplatform use of the .use {} extension function
            for resource management.
            """.trimIndent()
        )

        class SimpleResource(private val name: String) : AutoCloseable {
            init {
                log.info("Resource '{}' opened", name)
            }

            fun process() {
                log.info("Processing resource '{}'", name)
            }

            override fun close() {
                log.info("Resource '{}' closed", name)
            }
        }

        SimpleResource("demo").use { resource ->
            resource.process()
        }
    }
}
