package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

private fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

private fun interface StringMapper {
    fun map(input: String): String
}

object Kotlin14 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.4", Descriptions.INDENT1)
        log.info("Kotlin 1.4.0 was released on August 17, 2020.")
        log.info("https://kotlinlang.org/docs/whatsnew14.html")

        samConversions()
        trailingCommas()
        namedArgumentsInMiddle()
    }

    private fun samConversions() {
        log.info("\n{} SAM conversions for Kotlin interfaces", Descriptions.INDENT2)
        log.info(
            """
            Kotlin 1.4 introduces SAM (Single Abstract Method) conversions
            for Kotlin interfaces marked with 'fun interface'.
            Previously, SAM conversions only worked with Java interfaces.
            """.trimIndent()
        )

        // SAM conversion with lambda
        val isEven = IntPredicate { it % 2 == 0 }
        val toUpperCase = StringMapper { it.uppercase() }

        log.info("Is 4 even? {}", isEven.accept(4))
        log.info("Is 7 even? {}", isEven.accept(7))
        log.info("Mapped: {}", toUpperCase.map("kotlin"))
    }

    private fun trailingCommas() {
        log.info("\n{} Trailing comma support", Descriptions.INDENT2)
        log.info(
            """
            Trailing commas make version control diffs cleaner,
            make it easy to reorder elements, and simplify code generation.
            They are now allowed in parameter lists, argument lists,
            when entries, destructuring declarations, and more.
            """.trimIndent()
        )

        val languages =
            listOf(
                "Kotlin",
                "Java",
                "Scala",
                // trailing comma - easy to add new items
                "Groovy"
            )
        log.info("Languages: {}", languages)

        data class Config(
            val name: String,
            val version: String,
            // trailing comma in constructor
            val debug: Boolean
        )

        val config =
            Config(
                name = "MyApp",
                version = "1.4",
                // trailing comma in function call
                debug = true
            )
        log.info("Config: {}", config)
    }

    private fun namedArgumentsInMiddle() {
        log.info("\n{} Named arguments in middle position", Descriptions.INDENT2)
        log.info(
            """
            Named arguments can now be used in the middle of a function call,
            not just at the end. This is useful when some arguments are clear
            from context but others need names for clarity.
            """.trimIndent()
        )

        fun createTag(
            tag: String,
            content: String,
            style: String = ""
        ): String {
            return if (style.isEmpty()) {
                "<$tag>$content</$tag>"
            } else {
                """<$tag style="$style">$content</$tag>"""
            }
        }

        val html = createTag("div", content = "Hello Kotlin 1.4", style = "color:blue")
        log.info("HTML: {}", html)

        // Named argument in the middle
        val simpleTag = createTag("p", content = "Simple paragraph")
        log.info("Simple tag: {}", simpleTag)
    }
}
