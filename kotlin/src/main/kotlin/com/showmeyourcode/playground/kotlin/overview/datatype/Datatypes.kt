package com.showmeyourcode.playground.kotlin.overview.datatype

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Datatypes {
    fun main() {
        Logging.LOGGER.info("\n${Descriptions.DATA_TYPE_HEADER}")
        Logging.LOGGER.info(Descriptions.DATA_TYPE)

        // https://kotlinlang.org/docs/basic-types.html
        Logging.LOGGER.info(
            """
            |In Kotlin, everything is an object in the sense 
            |that you can call member functions and properties on any variable.
            """.trimIndent()
        )

        types()
        primitives()
        signedUnsignedVariables()
    }

    private fun primitives() {
        Logging.LOGGER.info("\nDoes Kotlin have primitive types? No, it does not.")
        Logging.LOGGER.info("Primitives are a silly construct, which Kotlin does not attempt to bring in from Java.")
        Logging.LOGGER.info(
            """Everything in Kotlin is a class and Int is no exception, 
                |leading to a completely unified type system where everything inherits from Any.
            """.trimMargin()
        )
        // https://kotlinlang.org/docs/comparison-to-java.html#what-java-has-that-kotlin-does-not
        // https://discuss.kotlinlang.org/t/does-kotlin-have-primitive-types/13789/4
    }

    private fun types() {
        // https://mfallahpour.medium.com/any-nothing-and-unit-in-kotlin-6b0b9779c197
        Logging.LOGGER.info(
            "Any is the root of the Kotlin class hierarchy. Every Kotlin class has Any as a superclass.\n"
        )

        Logging.LOGGER.info(
            """Nothing, you can use Nothing to represent 'a value that never exists': 
               |for example, if a function has the return type of Nothing, it means that it never returns 
               |(always throws an exception).
            """.trimMargin()
        )

        Logging.LOGGER.info(
            """
                |
                |Unit is the same thing as void in Java or C. 
                |When the return type of a function is Unit it means that the function does not return anything."""
                .trimMargin()
        )

        Logging.LOGGER.info(
            """
                |
                |Local type inference in Kotlin is the process of deducing the compile-time types of expressions, 
                |lambda expression parameters and properties. Ref: https://kotlinlang.org/spec/type-inference.html
            """.trimMargin()
        )

        val typeInterferenceExample = "1".toIntOrNull() ?: 0
        Logging.LOGGER.info(
            """
                |val a = \"1\".toIntOrNull() ?: 0: $typeInterferenceExample 
                |-> you don't have to specify the type as it's deducted by Kotlin/Type inference
            """.trimMargin()
        )
    }

    private fun signedUnsignedVariables() {
        // https://www.baeldung.com/kotlin/unsigned-integers
        Logging.LOGGER.info("\n====> Signed/Unsigned variables")
        Logging.LOGGER.info("As of Kotlin 1.3, Kotlin supports unsigned integers to accommodate this requirement.")

        val uByte: UByte = 42u
        val uShort: UShort = 42u
        val uInt: UInt = 42U
        val uLong: ULong = 42u

        Logging.LOGGER.info("UByte: $uByte UShort: $uShort UInt: $uInt ULong: $uLong (42u * 43u): ${42u * 43u}")
    }
}
