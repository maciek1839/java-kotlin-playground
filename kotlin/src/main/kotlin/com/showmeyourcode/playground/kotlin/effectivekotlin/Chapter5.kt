package com.showmeyourcode.playground.kotlin.effectivekotlin

// Top-Level Factory Functions
// Used like listOf(), mapOf(), etc.
// More readable than List.of().
// Disadvantage: Avoids polluting the global scope unnecessarily.
fun createPerson(name: String): Chapter5.Person = Chapter5.Person.create(name)

object Chapter5 {
    class Person private constructor(val name: String) {
        companion object {
            // Companion Object Factory Functions
            // Private constructor enforces controlled object creation.
            // Factory function provides a named, flexible alternative.
            fun create(name: String): Person = Person(name)
        }
    }

    // 4. Fake Constructor
    interface Animal {
        fun speak(): String
    }

    fun demo() {
        // Item 33: Consider factory functions instead of constructors

        // 1. Companion Object Factory Functions
        val person = Person.create("Alice")

        // 2. Extension Factory Functions
        // Encapsulates complex construction logic within an extension function.
        // Extension Factory Functions can be unintuitive.
        class Person2(val name: String)

        fun String.toPerson(): Person2 = Person2(this)

        val person2 = "Alice".toPerson()
        // 3. Top-Level Factory Functions
        val person3 = createPerson("Alice")

        // 4. Fake Constructor
        // In Kotlin, constructors are used like top-level functions.
        // Fake constructors mimic the constructor style but allow flexibility.

        class Dog : Animal {
            override fun speak() = "Woof!"
        }

        fun Animal(): Animal = Dog() // Fake constructor

        val animal = Animal()
        println(animal.speak()) // "Woof!"

        // 5. Factory Class with Methods
        // Useful for stateful factories.
        // Suitable for Abstract Factory, Prototype, etc.
        data class Car(val model: String)

        class CarFactory {
            private val cache = mutableMapOf<String, Car>()

            fun getCar(model: String): Car {
                return cache.getOrPut(model) { Car(model) }
            }
        }

        val factory = CarFactory()
        val car1 = factory.getCar("Tesla")
        val car2 = factory.getCar("Tesla")

        println(car1 === car2)

        // Item 34: Consider a primary constructor with named optional arguments
        // The Telescoping Constructor Pattern (Avoided in Kotlin) because you have optional arguments
        // A telescoping constructor is a series of constructors where each one has a different number of parameters.
        class Pizza constructor(
            private val size: String,
            private val cheese: Boolean = false,
            private val pepperoni: Boolean = false
        )

        // The Builder Pattern (Less Needed in Kotlin)
        // When Would You Still Use a Builder in Kotlin?
        // Although Kotlin usually does not require the builder pattern, it can still be useful when:
        // 1. Interoperating with Java (e.g., designing a Kotlin API used by Java)
        // 2. Supporting other languages (e.g., Swift, JavaScript)
        // 3. Preserving mutable object creation (e.g., dialogs, configuration objects)
        //
        // Alternative: Instead of builders, we can use data classes and the .copy() function:
        data class Route(val name: String, val fn: () -> Unit)

        data class RouterConfig(
            val routes: List<Route> = emptyList(),
            val loggingEnabled: Boolean = false
        )

        val config = RouterConfig(loggingEnabled = true)
        val updatedConfig = config.copy(routes = listOf(Route("/home") { println("Home route executed") }))

        // Item 35: Consider defining a DSL for complex object creation
        // Use extension functions to define a declarative API.
        // Pass an extension function to another function (scope functions help).
        data class SimplePerson(val age: Int)

        class SimplePersonBuilder {
            var age = 0

            fun build(): SimplePerson = SimplePerson(age)
        }

        fun buildSimplePerson(init: SimplePersonBuilder.() -> Unit): SimplePerson {
            val builder = SimplePersonBuilder()
            builder.init()
            return builder.build()
        }

        val simplePerson: SimplePerson =
            buildSimplePerson {
                age = 42
            }
        println(simplePerson)
    }
}
