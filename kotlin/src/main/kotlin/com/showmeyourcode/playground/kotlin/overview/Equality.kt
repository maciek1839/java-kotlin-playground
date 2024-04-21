package com.showmeyourcode.playground.kotlin.overview

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object Equality {
    fun main() {
        Logging.LOGGER.info("\n${Descriptions.EQUALITY_HEADER}")
        // https://medium.com/@humzakhalid94/understanding-and-equals-in-kotlin-a-comparison-with-code-examples-9da0272e4a94
        Logging.LOGGER.info("\n====> (Equality Operator)")
        Logging.LOGGER.info("The '==' operator is used to check for structural equality between objects.")
        Logging.LOGGER.info("It compares the values of the objects rather than their references.")

        Logging.LOGGER.info("\n====> (Reference Equality Operator)")
        Logging.LOGGER.info(
            """
            The '===' operator, also known as the reference equality operator, compares object references. 
            It checks whether two objects refer to the same memory location or not.
            """.trimIndent()
        )

        data class Person(val name: String, val age: Int)
        val person1 = Person("John", 25)
        val person2 = Person("John", 25)
        val person3 = person1

        Logging.LOGGER.info("Comparing result: ${person1 === person2}")
        Logging.LOGGER.info("Comparing result: ${person1 === person3}")
    }
}
