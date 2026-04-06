package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Kotlin21 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("{} Kotlin 2.1", Descriptions.INDENT1)
        log.info("Kotlin 2.1.0 was released on November 27, 2024.")
        log.info("Kotlin 2.1.20 was released on March 20, 2025.")
        log.info("https://kotlinlang.org/docs/whatsnew21.html")
        log.info("https://kotlinlang.org/docs/whatsnew2120.html")
        log.info(
            """
            Key features (preview in 2.1.0, some stabilized in 2.1.20):
            - Guard conditions in when with a subject
            - Non-local break and continue
            - Multi-dollar string interpolation
            - Tooling updates and performance improvements
            
            Kotlin 2.1.20 incremental improvements:
            - K2 compiler stability improvements and bug fixes
            - Gradle build improvements (project isolation support, new DSL for compiler options)
            - Kotlin/Native: custom allocator enabled by default with improved GC performance
            - Improved interop with Swift 6.1
            """.trimIndent()
        )

        guardConditionsInWhen()
        multiDollarInterpolation()
    }

    private fun guardConditionsInWhen() {
        log.info("\n{} Guard conditions in when (preview)", Descriptions.INDENT2)
        log.info(
            """
            Guard conditions allow you to include more than one condition
            for when expression branches, making complex control flow
            more explicit and flat. A guard condition is introduced with
            the 'if' keyword after the primary condition.
            """.trimIndent()
        )

        // Demonstrating the concept with standard when (guard conditions require preview flag)
        data class User(val name: String, val role: String, val isActive: Boolean)

        val user = User("Alice", "admin", true)

        // Standard when can emulate guard-like conditions with combined checks:
        val access =
            when {
                user.role == "admin" && user.isActive -> "Full access"
                user.role == "admin" && !user.isActive -> "Account suspended"
                user.role == "user" && user.isActive -> "Limited access"
                else -> "No access"
            }
        log.info("User: {} -> {}", user.name, access)
    }

    private fun multiDollarInterpolation() {
        log.info("\n{} Multi-dollar string interpolation (preview)", Descriptions.INDENT2)
        log.info(
            """
            Multi-dollar string interpolation allows using multiple dollar signs
            to avoid escaping in strings that naturally contain dollar signs,
            such as JSON templates, shell scripts, or financial data.
            Note: This feature requires the preview flag to use the ${'$'}${'$'} syntax.
            """.trimIndent()
        )

        // Without multi-dollar interpolation, escaping is needed:
        val price = 42
        val jsonTemplate = """{"price": ${'$'}$price, "currency": "${'$'}USD"}"""
        log.info("JSON template: {}", jsonTemplate)
    }
}
