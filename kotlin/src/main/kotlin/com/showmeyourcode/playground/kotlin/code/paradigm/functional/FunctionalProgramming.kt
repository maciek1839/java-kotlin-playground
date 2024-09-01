package com.showmeyourcode.playground.kotlin.code.paradigm.functional

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right

sealed class Result

data class Success(val message: String) : Result()

data class Error(val message: String) : Result()

sealed class ValidationError(val message: String) {
    object NameEmpty : ValidationError("Name cannot be empty")

    object AgeTooLow : ValidationError("Age must be at least 18")
}

object FunctionalProgramming {
    fun main() {
        higherOrderFunctions()
        functionComposition()
        collectionOperations()
        sealedClassesAndPatternMatching()
        currying()
        uncurrying()
        // functional constructs
        kotlinStandardLibraryFunctions()
        // functional libraries
        arrowLibrary()
    }

    private fun arrowLibrary() {
        // validation
        // https://arrow-kt.io/learn/typed-errors/validation/
        data class User(val name: String, val age: Int)

        fun validateName(name: String): Either<ValidationError, String> =
            if (name.isNotBlank()) {
                name.right()
            } else {
                ValidationError.NameEmpty.left()
            }

        fun validateAge(age: Int): Either<ValidationError, Int> =
            if (age >= 18) {
                age.right()
            } else {
                ValidationError.AgeTooLow.left()
            }

        fun validateUser(
            name: String,
            age: Int
        ): Either<ValidationError, User> =
            validateName(name).flatMap { validName ->
                validateAge(age).map { validAge ->
                    User(validName, validAge)
                }
            }

        val result1 = validateUser("John Doe", 20)
        val result2 = validateUser("", 16)

        println(result1) // Right(User(name=John Doe, age=20))
        println(result2) // Left(Name cannot be empty)

        // functional constructs
        fun processEmail(email: String): Either<String, String> =
            email.takeIf { it.contains("@") }
                ?.right()
                ?.flatMap { it.split("@").last().right() }
                ?.flatMap { domain ->
                    if (domain.isNotBlank()) {
                        domain.right()
                    } else {
                        "Domain cannot be blank".left()
                    }
                }
                ?: "Invalid email: $email".left()

        val result = processEmail("user@example.com")
        println(result) // Right(example.com)

        val invalidEmailResult = processEmail("userexample.com")
        println(invalidEmailResult) // Left(Invalid email: userexample.com)
    }

    private fun kotlinStandardLibraryFunctions() {
        fun example1() {
            data class Config(var url: String, var timeout: Int)

            val config =
                Config("http://example.com", 1000).apply {
                    url = "https://new-url.com"
                    timeout = 5000
                }.also {
                    println("Config updated: $it")
                }

            val result =
                config.run {
                    if (timeout > 3000) {
                        "Timeout is too high"
                    } else {
                        "Configuration is acceptable"
                    }
                }.let {
                    println(it)
                    it.length
                }

            println(result) // Output: Config updated: Config(url=https://new-url.com, timeout=5000)
            // Timeout is too high
            // 19
        }

        fun example2() {
            data class Person(var name: String, var age: Int)

            val personInfo =
                Person("John", 30).apply {
                    name = "Jane"
                    age = 25
                }.run {
                    "Name: $name, Age: $age"
                }.let {
                    println(it)
                    it.length
                }

            println(personInfo) // Output: Name: Jane, Age: 25
            // 17
        }

        example1()
        example2()
    }

    private fun uncurrying() {
        fun curriedAdd(a: Int): (Int) -> Int {
            return { b: Int -> a + b }
        }

        fun uncurriedAdd(
            a: Int,
            b: Int
        ): Int {
            return a + b
        }

        curriedAdd(3)
        uncurriedAdd(3, 5)

        // Another example
        fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C {
            return { a: A, b: B -> f(a)(b) }
        }

        val curriedAdd: (Int) -> (Int) -> Int = { a -> { b -> a + b } }

        val uncurriedAdd: (Int, Int) -> Int = uncurry(curriedAdd)
        println(uncurriedAdd(3, 5)) // Output: 8
    }

    private fun currying() {
        fun multiply(a: Int) = { b: Int -> a * b }

        val multiplyByTwo = multiply(2)
        println(multiplyByTwo(5)) // Output: 10
    }

    private fun sealedClassesAndPatternMatching() {
        fun handleResult(result: Result) {
            when (result) {
                is Success -> println("Success: ${result.message}")
                is Error -> println("Error: ${result.message}")
            }
        }

        handleResult(Success("Hello"))
    }

    private fun collectionOperations() {
        val numbers = listOf(1, 2, 3, 4, 5)

        val squaredNumbers = numbers.map { it * it }
        val evenNumbers = numbers.filter { it % 2 == 0 }
        val sum = numbers.reduce { acc, i -> acc + i }

        println(squaredNumbers) // Output: [1, 4, 9, 16, 25]
        println(evenNumbers) // Output: [2, 4]
        println(sum) // Output: 15
    }

    private fun functionComposition() {
        infix fun <T, U, V> ((T) -> U).compose(f: (U) -> V): (T) -> V {
            return { x: T -> f(this(x)) }
        }
        val addOne = { x: Int -> x + 1 }
        val multiplyByTwo = { x: Int -> x * 2 }

        val addThenMultiply = addOne compose multiplyByTwo
        println(addThenMultiply(3)) // Output: 8 (3 + 1) * 2
    }

    private fun higherOrderFunctions() {
        fun operation(
            x: Int,
            y: Int,
            operation: (Int, Int) -> Int
        ): Int {
            return operation(x, y)
        }

        val add: (Int, Int) -> Int = { a, b -> a + b }
        val subtract: (Int, Int) -> Int = { a, b -> a - b }

        println(operation(5, 3, add)) // Output: 8
        println(operation(5, 3, subtract)) // Output: 2
    }
}
