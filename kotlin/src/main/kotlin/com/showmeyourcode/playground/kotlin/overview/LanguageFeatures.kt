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
        dataClass()
        inlineClasses()
    }

    private fun dataClass() {
        // https://medium.com/@pritik.jain/data-class-vs-normal-class-in-kotlin-6ed2b36edcae
        Logging.LOGGER.info("\n{} Data class", Descriptions.INDENT1)
        Logging.LOGGER.info("Data classes in Kotlin are primarily used to hold data.")

        Logging.LOGGER.info(
            """
            Differences between data classes and normal classes:
            - A data class must be declared with at least one primary constructor parameter which must be declared with val or var. 
              A normal class can be defined with or without a parameter in its constructor.
            - Data classes have default implementations for the following methods using only properties 
              that were declared in the primary constructor; toString(), hashCode(), copy(), componentN(), equals().
              Implementation for those methods can be written in normal classes using properties that were 
              and were not declared in the primary constructor.
            - A data class cannot be extended by another class. 
              They are final classes by default. Normal classes can be extended by other classes, including data classes.
              Certain conditions should however be met.
            - Data classes cannot be sealed, open, abstract or inner. Normal classes can be any of these.
            """.trimIndent()
        )
    }

    private fun inlineClasses() {
        // https://kotlinlang.org/docs/inline-classes.html
        // https://hyperskill.org/learn/step/26475
        Logging.LOGGER.info("\n{} Inline classes", Descriptions.INDENT1)
        Logging.LOGGER.info(
            """
            Sometimes it is useful to wrap a value in a class to create a more domain-specific type.
            However, it introduces runtime overhead due to additional heap allocations.
            Moreover, if the wrapped type is primitive, the performance hit is significant,
            because primitive types are usually heavily optimized by the runtime,
            while their wrappers don't get any special treatment.
            
            To solve such issues, Kotlin introduces a special kind of class called an inline class.
            Inline classes are a subset of value-based classes. They don't have an identity and can only hold values.
            
            An inline class must have a single property initialized in the primary constructor. 
            At runtime, instances of the inline class will be represented using this single property.
            
            Since the code of an inline function is inserted directly into the calling code, 
            there is no need to create an additional function object.
            Inline functions in Kotlin offer valuable benefits,
            including improved performance and enhanced code readability. 
            
            data class is a fully featured class with additional goodies.
            value class is a named, unmodifiable tuple without identity.
            """.trimIndent()
        )

        val password = Password("Don't try this in production")
        Logging.LOGGER.info("Inline class value: {}", password)
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

data class PasswordDataClass(val s: String, val secondProperty: String)

// An inline class must have a single property initialized in the primary constructor.
@JvmInline
value class Password(private val s: String) {
    fun exampleMethod(value: Int): Int {
        return value
    }
}
