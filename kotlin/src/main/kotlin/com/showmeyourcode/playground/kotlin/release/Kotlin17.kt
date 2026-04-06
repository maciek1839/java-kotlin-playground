package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

@JvmInline
private value class UserId(val id: Long)

@JvmInline
private value class OrderId(val id: Long)

object Kotlin17 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.7", Descriptions.INDENT1)
        log.info("Kotlin 1.7.0 was released on June 9, 2022.")
        log.info("https://kotlinlang.org/docs/whatsnew17.html")

        inlineClasses()
        definitelyNonNullableTypes()
        builderInference()
    }

    private fun inlineClasses() {
        log.info("\n{} Stable inline (value) classes", Descriptions.INDENT2)
        log.info(
            """
            Inline (value) classes provide type safety without runtime overhead.
            They are wrappers around a single value that are inlined at compile time,
            avoiding the performance cost of object allocation.
            """.trimIndent()
        )

        fun processUser(userId: UserId) {
            log.info("Processing user with ID: {}", userId.id)
        }

        // Type safety: can't accidentally pass OrderId where UserId is expected
        val userId = UserId(42L)
        // val orderId = OrderId(42L)
        // processUser(orderId) // Compile error!
        processUser(userId)
        log.info("UserId and OrderId have the same underlying type but are not interchangeable")
    }

    private fun definitelyNonNullableTypes() {
        log.info("\n{} Definitely non-nullable types", Descriptions.INDENT2)
        log.info(
            """
            Definitely non-nullable types (T & Any) are useful for Java interop.
            They allow you to specify that a generic type parameter is definitely
            not null, even when the original Java declaration is platform-typed.
            """.trimIndent()
        )

        // Example: ensuring non-null in generic context
        fun <T> processNotNull(value: T & Any): String {
            return value.toString()
        }

        val result = processNotNull("Kotlin 1.7")
        log.info("Processed non-null value: {}", result)
    }

    private fun builderInference() {
        log.info("\n{} Builder inference improvements", Descriptions.INDENT2)
        log.info(
            """
            Builder inference allows the compiler to infer type arguments
            of a call using type information from other calls inside its
            lambda argument. This is useful for building type-safe builders.
            """.trimIndent()
        )

        // Builder inference in action
        val map =
            buildMap {
                put("language", "Kotlin")
                put("version", "1.7")
                put("feature", "builder inference")
            }
        log.info("Built map: {}", map)

        val list =
            buildList {
                add("first")
                add("second")
                addAll(listOf("third", "fourth"))
            }
        log.info("Built list: {}", list)
    }
}
