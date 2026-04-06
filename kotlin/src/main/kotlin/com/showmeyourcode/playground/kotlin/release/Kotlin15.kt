package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

private sealed interface Shape {
    fun area(): Double
}

private data class Circle(val radius: Double) : Shape {
    override fun area() = Math.PI * radius * radius
}

private data class Rectangle(val width: Double, val height: Double) : Shape {
    override fun area() = width * height
}

private data class Triangle(val base: Double, val height: Double) : Shape {
    override fun area() = 0.5 * base * height
}

@JvmRecord
private data class PersonRecord(val name: String, val age: Int)

@JvmInline
private value class Email(val address: String) {
    init {
        require(address.contains("@")) { "Invalid email address: $address" }
    }
}

object Kotlin15 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.5", Descriptions.INDENT1)
        log.info("Kotlin 1.5.0 was released on May 5, 2021.")
        log.info("https://blog.jetbrains.com/kotlin/2021/05/kotlin-1-5-0-released/")

        sealedInterfaces()
        jvmRecords()
        inlineClasses()
    }

    private fun sealedInterfaces() {
        log.info("\n{} Sealed interfaces", Descriptions.INDENT2)
        log.info(
            """
            The sealed modifier now works on interfaces the same way it works on classes.
            This enables more flexible sealed hierarchies where implementations
            can extend other classes while still being part of the sealed hierarchy.
            """.trimIndent()
        )

        // Exhaustive when - compiler knows all implementations
        fun describe(shape: Shape): String =
            when (shape) {
                is Circle -> "Circle with radius ${shape.radius}, area = ${"%.2f".format(shape.area())}"
                is Rectangle -> "Rectangle ${shape.width}x${shape.height}, area = ${"%.2f".format(shape.area())}"
                is Triangle -> "Triangle base=${shape.base}, height=${shape.height}, area = ${"%.2f".format(
                    shape.area()
                )}"
            }

        val shapes = listOf(Circle(5.0), Rectangle(3.0, 4.0), Triangle(6.0, 3.0))
        shapes.forEach { log.info("  {}", describe(it)) }
    }

    private fun jvmRecords() {
        log.info("\n{} JVM records interop", Descriptions.INDENT2)
        log.info(
            """
            Kotlin data classes can be annotated with @JvmRecord to generate
            Java record classes in the bytecode. This ensures interoperability
            with Java code expecting records (Java 16+).
            """.trimIndent()
        )

        val person = PersonRecord("Alice", 30)
        log.info("JVM Record: {} (name={}, age={})", person, person.name, person.age)
    }

    private fun inlineClasses() {
        log.info("\n{} Inline classes (value classes)", Descriptions.INDENT2)
        log.info(
            """
            Value classes (previously inline classes) wrap a single value
            without runtime overhead. The @JvmInline annotation and 'value' keyword
            replaced the old 'inline' keyword for classes.
            Sealed class improvements: subclasses can now be in all files
            of the same compilation unit and package.
            """.trimIndent()
        )

        val email = Email("kotlin@jetbrains.com")
        log.info("Email value class: {}", email)
    }
}
