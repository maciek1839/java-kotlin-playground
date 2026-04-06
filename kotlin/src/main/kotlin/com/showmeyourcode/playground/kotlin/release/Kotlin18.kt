package com.showmeyourcode.playground.kotlin.release

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import java.nio.file.Files
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.deleteRecursively
import kotlin.io.path.writeText

object Kotlin18 {
    private val log = Logging.LOGGER

    fun main() {
        log.info("\n{} Kotlin 1.8", Descriptions.INDENT1)
        log.info("Kotlin 1.8.0 was released on December 28, 2022.")
        log.info("https://kotlinlang.org/docs/whatsnew18.html")

        stdlibConsolidation()
        recursiveDirectoryOperations()
        kotlinReflectImprovements()
    }

    private fun stdlibConsolidation() {
        log.info("\n{} Standard library consolidation", Descriptions.INDENT2)
        log.info(
            """
            kotlin-stdlib-jdk7 and kotlin-stdlib-jdk8 have been merged into kotlin-stdlib.
            You no longer need to add separate dependencies for JDK 7/8 extensions.
            The merged stdlib automatically provides all JDK 7/8 extension functions.
            """.trimIndent()
        )

        // java.nio.file extensions (previously in kotlin-stdlib-jdk7)
        val tempFile = Files.createTempFile("kotlin18-demo", ".txt")
        tempFile.toFile().writeText("Kotlin 1.8 unified stdlib!")
        val content = tempFile.toFile().readText()
        log.info("File content (from unified stdlib): {}", content)
        Files.deleteIfExists(tempFile)

        // Stream extensions (previously in kotlin-stdlib-jdk8)
        val list = listOf(1, 2, 3, 4, 5)
        val sum = list.stream().mapToInt { it * 2 }.sum()
        log.info("Stream sum of doubled values: {}", sum)
    }

    @OptIn(kotlin.io.path.ExperimentalPathApi::class)
    private fun recursiveDirectoryOperations() {
        log.info("\n{} Recursive directory operations (kotlin.io.path)", Descriptions.INDENT2)
        log.info(
            """
            New extension functions for recursively copying and deleting directories.
            - Path.deleteRecursively() - deletes a directory and all its contents
            - Path.copyToRecursively() - copies a directory tree to a destination
            These replace the need for manual recursive file traversal.
            """.trimIndent()
        )

        // Create a temp directory structure
        val tempDir = createTempDirectory("kotlin18-demo")
        val subDir = tempDir.resolve("subdir").createDirectories()
        tempDir.resolve("file1.txt").writeText("Hello")
        subDir.resolve("file2.txt").writeText("World")

        log.info("Created temp directory with files: {}", tempDir)

        // Delete recursively (new in 1.8)
        tempDir.deleteRecursively()
        log.info("Directory deleted recursively: {}", !Files.exists(tempDir))
    }

    private fun kotlinReflectImprovements() {
        log.info("\n{} kotlin-reflect performance improvements", Descriptions.INDENT2)
        log.info(
            """
            kotlin-reflect received significant performance improvements in 1.8.
            Reflection operations like getting KClass, accessing properties,
            and calling functions are now faster due to internal caching
            and reduced memory allocations.
            """.trimIndent()
        )

        // Demonstrating reflection (now faster in 1.8)
        data class Sample(val x: Int, val y: String)

        val kClass = Sample::class
        log.info("Class: {}", kClass.simpleName)
        log.info("Properties: {}", kClass.members.filter { it.name in listOf("x", "y") }.map { it.name })

        val instance = Sample(10, "hello")
        val xProp = kClass.members.first { it.name == "x" }
        log.info("Property 'x' value: {}", xProp.call(instance))
    }
}
