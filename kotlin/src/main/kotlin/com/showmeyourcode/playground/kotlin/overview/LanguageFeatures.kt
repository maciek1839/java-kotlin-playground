package com.showmeyourcode.playground.kotlin.overview

import com.showmeyourcode.playground.kotlin.common.Descriptions
import com.showmeyourcode.playground.kotlin.common.Logging
import java.io.BufferedReader
import java.io.InputStreamReader

object LanguageFeatures {
    fun main() {
        Logging.LOGGER.info(Descriptions.headerTemplate(Descriptions.LANGUAGE_FEATURES))
        Logging.LOGGER.info("Below some Kotlin features/syntax good to know:\n")

        nullSafety()
        controlStructures()
        lazyLoading()
        operators()
        operatorOverloading()
        destructingDeclarations()
        singleExpressionFunctions()
        classes()
        collectionLiterals()
        extensionFunctions()
        varianceModifiers()
        readingFiles()
    }

    // Covariant interface (out) - Produces values
    interface Producer<out T> {
        fun produce(): T
    }

    // Contravariant interface (in) - Consumes values
    interface Consumer<in T> {
        fun consume(value: T)
    }

    // Invariant class (default behavior)
    class Container<T>(val value: T)

    // Implementations
    class StringProducer : Producer<String> {
        override fun produce(): String = "Hello, Kotlin!"
    }

    class AnyConsumer : Consumer<Any> {
        override fun consume(value: Any) {
            println("Consumed: $value")
        }
    }

    private fun varianceModifiers() {
        Logging.LOGGER.info("\n{} Variance modifiers", Descriptions.INDENT1)
        Logging.LOGGER.info(
            """
            Variance modifiers in Kotlin (in, out) define how generic types can be used in relation to subtyping.
            They help manage covariance and contravariance, ensuring type safety when working with generics.
            """.trimIndent()
        )

        // Use-site contravariance (in) - Write only
        fun addValues(list: MutableList<in String>) {
            list.add("Kotlin")
        }

        // Covariance: Producer<String> can be assigned to Producer<Any>
        val stringProducer: Producer<String> = StringProducer()
        val anyProducer: Producer<Any> = stringProducer
        println(anyProducer.produce()) // "Hello, Kotlin!"

        // Contravariance: Consumer<Any> can be assigned to Consumer<String>
        val anyConsumer: Consumer<Any> = AnyConsumer()
        val stringConsumer: Consumer<String> = anyConsumer
        stringConsumer.consume("Generics in Kotlin") // "Consumed: Generics in Kotlin"

        // Invariance: Container<String> CANNOT be assigned to Container<Any>
        val stringContainer: Container<String> = Container("Invariant Example")
        // val anyContainer: Container<Any> = stringContainer // Compilation Error

        // Type Projections (Use-Site Variance)
        val stringList: List<String> = listOf("Kotlin", "Java")

        val anyList: MutableList<Any> = mutableListOf("Scala")
        addValues(anyList) // Allowed due to `in`
    }

    private fun extensionFunctions() {
        Logging.LOGGER.info("\n{} Extension functions", Descriptions.INDENT1)
        Logging.LOGGER.info(
            """
            An extension function in Kotlin allows you to add new functionality to 
            existing classes without modifying their source code. 
            This is done by defining a function outside the class and using a special syntax 
            to attach it to a class. The function is then called as if it were a member of that class.
            """.trimIndent()
        )

        fun Int.isEven() = this % 2 == 0
        print(2.isEven())

        fun List<Int>.average() = 1.0 * sum() / size
        print(listOf(1, 2, 3, 4).average())
    }

    private fun collectionLiterals() {
        Logging.LOGGER.info("\n{} Collection literals", Descriptions.INDENT1)
        Logging.LOGGER.info("Collection literals refer to a concise and direct way to create collections.")

        listOf(1, 2, 3, 4)
        mutableListOf(1, 2, 3, 4)

        setOf("A", "B", "C")
        mutableSetOf("A", "B", "C")

        arrayOf('a', 'b', 'c')

        mapOf(1 to "A", 2 to "B")
        mutableMapOf(1 to "A", 2 to "B")
        1 to "A" // Pair<Int, String>

        sequenceOf(4, 3, 2, 1)

        List(4) { it * 2 }
        generateSequence(4) { it + 2 }

        val students = listOf(true to 4.5, false to 2.0)
        // COLLECTION PROCESSING
        students
            .filter { it.first && it.second > 4.0 }
            // Only passing students
            .sortedByDescending { it.second }
            // Starting from ones with biggest grades
            .take(10) // Take first 10
        // .sortedWith(compareBy({ it.surname }, { it.name }))

        // Infinitive sequence of next numbers starting on 0
        generateSequence(0) { it + 1 }
            .filter { it % 2 == 0 } // Keep only even
            .map { it * 3 } // Triple every one
            .take(100) // Take first 100
            .average() // Count average

        // Most important functions for collection processing
        val l = listOf(1, 2, 3, 4)
        // filter - returns only elements matched by predicate
        l.filter { it % 2 == 0 } // [2, 4]
        // map - returns elements after transformation
        l.map { it * 2 } // [2, 4, 6, 8]
        // flatMap - returns elements yielded from results of trans.
        l.flatMap { listOf(it, it + 10) } // [1, 11, 2, 12, 3, 13, 4, 14]
        // fold/reduce - accumulates elements
        l.fold(0.0) { acc, i -> acc + i } // 10.0
        l.reduce { acc, i -> acc * i } // 24
        // forEach/onEach - performs an action on every element
        l.forEach { print(it) } // Prints 1234, returns Unit
        l.onEach { print(it) } // Prints 1234, returns [1, 2, 3, 4]
        // partition - splits into pair of lists
        val (even, odd) = l.partition { it % 2 == 0 }
        print(even) // [2, 4]
        print(odd) // [1, 3]
        // min/max/minBy/maxBy
        l.min() // 1, possible because we can compare Int
        l.minBy { -it } // 4
        l.max() // 4, possible because we can compare Int
        l.maxBy { -it } // 1
        // first/firstBy
        l.first() // 1
        l.first { it % 2 == 0 } // 2 (first even number)
        // count - count elements matched by predicate
        l.count { it % 2 == 0 } // 2
        // sorted/sortedBy - returns sorted collection
        listOf(2, 3, 1, 4).sorted() // [1, 2, 3, 4]
        l.sortedBy { it % 2 } // [2, 4, 1, 3]
        // groupBy - group elements on collection by key
        l.groupBy { it % 2 } // Map: {1=[1, 3], 0=[2, 4]}
        // distinct/distinctBy - returns only unique elements
        listOf(1, 1, 2, 2).distinct() // [1, 2]

        // Mutable vs immutable
        // collection processing functions
        val list = mutableListOf(3, 4, 2, 1)
        val sortedResult = list.sorted() // Returns sorted
        println(sortedResult) // [1, 2, 3, 4]
        println(list) // [3, 4, 2, 1]
        val sortResult = list.sort() // Sorts mutable collection
        println(sortResult) // kotlin.Unit
        println(list) // [1, 2, 3, 4]
    }

    private fun controlStructures() {
        // if as an expression
        fun bigger(
            a: Int,
            b: Int
        ) = if (a > b) a else b

        // for loop
        val list = listOf(1, 2)
        for (tmp in list) {
            bigger(tmp, 1)
        }

        // when expression
        fun numberTypeName(x: Number) =
            when (x) {
                0 -> "Zero" // equality check
                in 1..4 -> "Four or less" // range check
                5, 6, 7 -> "Five to seven" // multiple values
                is Byte -> "Byte" // type check
                else -> "Some number"
            }
        for (x in 0..10) {
            numberTypeName(x)
        }

        // when expression with predicates
        fun signAsString(x: Int) =
            when {
                x < 0 -> "Negative"
                x == 0 -> "Zero"
                else -> "Positive"
            }
        for (x in 0 until 2) {
            signAsString(x)
        }
    }

    private fun classes() {
        fun dataClass() {
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

            Logging.LOGGER.info(
                """
                data class Person(val name: String, var age: Int)
                val mike = Person("Mike", 23)
                
                Modifier data adds:
                 1. toString that displays all primary constructor 
                properties
                print(mike.toString()) // Person(name=Mike, age=23)
                 
                 2. equals that compares all primary constructor 
                properties
                print(mike == Person("Mike", 23)) // True
                print(mike == Person("Mike", 21)) // False
                
                 3. hashCode that is based on all primary 
                constructor properties
                val hash = mike.hashCode()
                print(hash == Person("Mike", 23).hashCode()) // True
                print(hash == Person("Mike", 21).hashCode()) // False
                
                 4. component1, component2 etc. that allows 
                deconstruction
                val (name, age) = mike
                
                 5. copy that returns copy of object with concrete 
                properties changed
                val jake = mike.copy(name = "Jake")
                """.trimIndent()
            )

            data class PasswordDataClass(val s: String, val secondProperty: String)

            PasswordDataClass("pswd", "2nd property")
        }

        fun inlineClasses() {
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

        fun propertiesWithAccessors() {
            class Person(var name: String, var surname: String) {
                var fullName: String
                    get() = "$name $surname"
                    set(value) {
                        val (first, rest) = value.split(" ", limit = 2)
                        name = first
                        surname = rest
                    }
            }
            Logging.LOGGER.info("\n{} Properties with accessors", Descriptions.INDENT1)
            Logging.LOGGER.info(
                """
                You can define custom accessors for a property. If you define a custom getter, 
                it will be called every time you access the property 
                (this way you can implement a computed property).
                
                If you define a custom setter, it will be called every time you assign a value to the property, 
                except its initialization. 
                """.trimIndent()
            )
            Person("A", "B").fullName
        }

        dataClass()
        inlineClasses()
        propertiesWithAccessors()
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

        fun sum(
            a: Int,
            b: Int
        ) = a + b

        sum(0, 0)
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
        val (param1, param2) = person
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
        // variables with nullable types
        var name: String?
        name = "John"

        name?.length ?: throw RuntimeException("Brr")
        name?.length ?: return
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

    private fun readingFiles() {
        Logging.LOGGER.info("\n{} Reading files", Descriptions.INDENT1)

        fun readFromClasspath(fileName: String): String {
            val inputStream =
                this::class.java.classLoader.getResourceAsStream(fileName)
                    ?: throw IllegalArgumentException("File not found in classpath: $fileName")

            val reader = BufferedReader(InputStreamReader(inputStream))
            // Operate on objects implementing Closeable or AutoCloseable using 'use'. It is a safe and easy option.
            return reader.use { it.readText() }
        }

        val fileName = "test.txt"
        val fileContent = readFromClasspath(fileName)
        println(fileContent)
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

// An inline class must have a single property initialized in the primary constructor.
@JvmInline
value class Password(private val s: String) {
    fun exampleMethod(value: Int): Int {
        return value
    }
}
