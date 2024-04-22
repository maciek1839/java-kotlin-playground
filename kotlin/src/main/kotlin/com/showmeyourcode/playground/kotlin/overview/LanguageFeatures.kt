package com.showmeyourcode.playground.kotlin.overview

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging

object LanguageFeatures {
    fun main() {
        Logging.LOGGER.info(Descriptions.headerTemplate(Descriptions.LANGUAGE_FEATURES))
        Logging.LOGGER.info("Below some Kotlin features/syntax good to know:\n")

        nullSafety()
        lazyLoading()
        operators()
        operatorOverloading()
        destructingDeclarations()
        singleExpressionFunctions()
    }

    private fun singleExpressionFunctions() {
        // https://kotlintutorial.io/single-expression-functions/
        // https://kotlinlang.org/docs/functions.html#single-expression-functions
        Logging.LOGGER.info("\n{} Single-expression functions", Descriptions.INDENT1)
        Logging.LOGGER.info(
            """
            Single-line functions (also known as single-expression functions) in Kotlin are functions
            that consist of only one expression and without curly braces {} to be defined
            """.trimIndent()
        )
    }

    private fun destructingDeclarations() {
        // https://kotlinlang.org/docs/destructuring-declarations.html
        Logging.LOGGER.info("\n{} Destructuring declarations", Descriptions.INDENT1)
        Logging.LOGGER.info(
            "Sometimes it is convenient to destructure an object into a number of variables, for example:"
        )
        Logging.LOGGER.info("val (name, age) = person")

        Logging.LOGGER.info(
            """
            |Destructuring is performed by calling functions 
            |component1, component2, component3 etc, on the instance being destructured.
            |
            |The componentN() functions need to be marked with the operator keyword 
            |to allow using them in a destructuring declaration.
            """.trimMargin()
        )
        // https://stackoverflow.com/questions/35736960/is-it-possible-to-implement-custom-destructuring-for-non-data-class-in-kotlin
        val person = Person("first", "last")
        val(param1, param2) = person
        Logging.LOGGER.info("Destruction result: ${"($param1, $param2)"}")
    }

    class Person(val firstName: String, val lastName: String) {
        operator fun component1() = firstName

        operator fun component2() = lastName
    }

    private fun operatorOverloading() {
        Logging.LOGGER.info("\n{} Operator overloading", Descriptions.INDENT1)
        Logging.LOGGER.info(
            "Kotlin allows you to provide custom implementations for the predefined set of operators on types."
        )
        Logging.LOGGER.info("Example operators: +,-,*.")

        val c1 = Complex(1.0, 2.0)
        val c2 = Complex(2.0, 3.0)
        Logging.LOGGER.info("Operator overloading result: ${c1 + c2} | ${c2 - c1}")

        // References:
        // https://kt.academy/article/kfde-operators
        // https://kotlinlang.org/docs/operator-overloading.html#unary-operations
    }

    private fun lazyLoading() {
        Logging.LOGGER.info("\n{} lazy loading", Descriptions.INDENT1)
        // https://stackoverflow.com/questions/36623177/property-initialization-using-by-lazy-vs-lateinit
        Logging.LOGGER.info("The Kotlin language has built-in support for lazy initialization.")
        Logging.LOGGER.info(
            """Lazy loading is a design pattern commonly used in computer programming to 
                    |defer initialization of an object until the point at which it is needed.
            """.trimMargin()
        )
        Logging.LOGGER.info(
            """
                |Lazy Initialization - lazy() is a function that takes a lambda and returns an instance of Lazy<T> 
                |which can serve as a delegate for implementing a lazy property: 
                |the first call to get() executes the lambda passed to lazy() and remembers the result, 
                |subsequent calls to get() simply return the remembered result.
                |
            """.trimMargin()
        )
        // Delegation pattern
        // Delegation can be an alternative to inheritance.
        // Delegation means that you use an object of another class as an instance variable, and forward messages to the instance.
        // https://www.geeksforgeeks.org/delegation-vs-inheritance-java/
        // https://java-design-patterns.com/patterns/delegation/#explanation
        val myLazyString: String by lazy {
            Logging.LOGGER.info("Calling a lazy method.")
            "Hello"
        }
        Logging.LOGGER.info("val myLazyString: String by lazy { \"Hello\" }: $myLazyString $myLazyString\n")

        Logging.LOGGER.info(
            """
                |Late Initialization - normally, properties declared as having a non-null type must be initialized in the constructor.
                |However, fairly often this is not convenient. For example, properties can be initialized through dependency injection, 
                |or in the setup method of a unit test. 
                |In this case, you cannot supply a non-null initializer in the constructor, 
                |but you still want to avoid null checks when referencing the property inside the body of a class.
                | 
            """.trimMargin()
        )

        lateinit var subject: String

        subject = "MySubject"

        Logging.LOGGER.info("lateinit var subject: String | $subject\n")

        // Comparing to Java, you have to initialize it on your own. There is no lazy keyword.
        // See: https://www.infoworld.com/article/3675954/lazy-vs-eager-instantiation-in-java-which-is-better.html
    }

    private fun nullSafety() {
        Logging.LOGGER.info("\n{} null safety", Descriptions.INDENT1)
        Logging.LOGGER.info("It is a way to indicate which variables can possibly hold a null value.")
        Logging.LOGGER.info("Kotlin's type system is aimed at eliminating the danger of null references")
    }

    private fun operators() {
        Logging.LOGGER.info(
            """
            In mathematics and computer programming, 
            an operator is a character that represents a specific mathematical or logical action or process.
            """.trimIndent()
        )
        Logging.LOGGER.info("\n{} Elvis operator", Descriptions.INDENT1)
        Logging.LOGGER.info(
            """
            |The Elvis operator ?: is a binary operator that returns its first operand if that operand is true,
            |and otherwise evaluates and returns its second operand.
            """.trimIndent()
        )
        Logging.LOGGER.info("150/10 == 15 ?: 100 Result: ${150 / 10 == 15 ?: 100}")
    }
}

data class Complex(val real: Double, val imaginary: Double) {
    operator fun plus(another: Complex) =
        Complex(
            real + another.real,
            imaginary + another.imaginary
        )

    operator fun minus(another: Complex) =
        Complex(
            real = real - another.real,
            imaginary = imaginary - another.imaginary
        )
}
