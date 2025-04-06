package com.showmeyourcode.playground.kotlin.effectivekotlin

import java.lang.ref.SoftReference

// Item 49: Consider using inline value classes
// Example: Type Alias (No Type Safety)
typealias UserId = String // Just a synonym, no extra safety

object Chapter7 {
    // Item 47: Avoid unnecessary object creation
    object Logger {
        fun log(message: String) {
            println("LOG: $message")
        }
    }

    // Implement object caching in factory functions
    class Fibonacci private constructor() {
        private val cache = mutableMapOf<Int, Long>()

        fun fib(n: Int): Long {
            if (n <= 1) return n.toLong()
            return cache.getOrPut(n) { fib(n - 1) + fib(n - 2) }
        }

        companion object {
            private val INSTANCE = Fibonacci()

            fun getInstance(): Fibonacci = INSTANCE
        }
    }

    // Using weak/soft references for caching
    class Cache {
        private var cachedData: SoftReference<List<String>>? = null

        fun getData(): List<String> {
            return cachedData?.get() ?: loadData().also {
                cachedData = SoftReference(it)
            }
        }

        private fun loadData(): List<String> {
            println("Loading data...")
            return listOf("Kotlin", "Java", "Scala")
        }
    }

    // Item 48: Use inline modifier for functions with parameters of functional types
    // Example With inline (Optimized)
    inline fun repeatAction2(
        times: Int,
        action: () -> Unit
    ) {
        for (i in 0 until times) {
            action()
        }
    }

    // Non-Local Returns in inline Functions
    inline fun execute(block: () -> Unit) {
        println("Before block")
        block()
        println("After block") // This line will be skipped if `return` is used in the lambda.
    }

    // How noinline Works
    inline fun process(
        inlineLambda: () -> Unit,
        noinline nonInlineLambda: () -> Unit
    ) {
        inlineLambda() // This is inlined
        nonInlineLambda() // This remains a function object
    }

    // How crossinline Works
    // crossinline forces block() to behave like a normal lambda.
    // return inside block() will only exit block(), not execute().
    inline fun execute2(crossinline block: () -> Unit) {
        Thread {
            block() // Now `return` inside `block` will only exit `block`, not `execute()`
        }.start()
    }

    // Item 49: Consider using inline value classes
    // The @JvmInline annotation is required since Kotlin 1.5+.
    // value class replaces the older inline class keyword.
    // There’s only one property, which must be final and cannot be a var.
    @JvmInline
    value class UserId(val id: String) // Under the hood, this behaves as a String at runtime

    @JvmInline
    value class Meter(val value: Double)

    @JvmInline
    value class Kilometer(val value: Double)

    @JvmInline
    value class Email(val value: String) {
        init {
            require(value.contains("@")) { "Invalid email format" }
        }
    }

    interface Printable {
        fun printInfo()
    }

    @JvmInline
    value class OrderId(val id: String) : Printable {
        override fun printInfo() {
            println("Order ID: $id")
        }
    }

    fun demo() {
        // Item 47: Avoid unnecessary object creation
        // Using object declarations (singletons)
        Logger.log("This is a singleton example.")

        // Implement object caching in factory functions
        val fibonacci = Fibonacci.getInstance()
        println(fibonacci.fib(10)) // 55

        // Using weak/soft references for caching
        val cache = Cache()
        println(cache.getData()) // Loads data
        println(cache.getData()) // Uses cached data

        // Lift heavy objects to an outer scope
        class ExpensiveObject {
            init {
                println("ExpensiveObject created!")
            }

            fun process() = println("Processing...")
        }

        // Bad: Creates a new object every time the function is called
        fun badApproach() {
            val obj = ExpensiveObject()
            obj.process()
        }

        // Good: Create once and reuse
        val expensiveObject = ExpensiveObject()

        fun goodApproach() {
            expensiveObject.process()
        }

        // Usage:
        badApproach() // Creates a new object
        badApproach() // Creates another new object

        goodApproach() // Reuses the same object
        goodApproach() // Still reusing

        // Use lazy initialization
        class Database {
            init {
                println("Database connected!")
            }

            fun query(sql: String) = "Result for $sql"
        }

        // Lazy initialization
        val database by lazy { Database() }

        // Usage:
        println("Program started") // Database is NOT created yet
        println(database.query("SELECT * FROM users")) // Now it gets created

        // Prefer primitives when possible
        // Avoiding unnecessary boxing (wrapping primitives in objects)
        fun sum(
            a: Int,
            b: Int
        ): Int = a + b // Uses primitive `int` under the hood

        // But boxing happens when we use nullable types:
        fun sumNullable(
            a: Int?,
            b: Int?
        ): Int? = a?.plus(b ?: 0) // Uses `Integer` instead of `int`

        // Avoiding boxing in generics
        // Kotlin optimizes primitives, but generics and nullable types force boxing.
        fun <T : Comparable<T>> maxValue(values: List<T>): T? {
            return values.maxOrNull()
        }

        // Usage:
        val numbers = listOf(1, 2, 3) // Boxed `Integer`
        println(maxValue(numbers))

        // Item 48: Use inline modifier for functions with parameters of functional types
        // Example Without inline (Overhead Exists)
        fun repeatAction(
            times: Int,
            action: () -> Unit
        ) {
            for (i in 0 until times) {
                action()
            }
        }

        repeatAction(3) {
            println("Executing action!")
        }
        // How This Works Internally (Without inline):
        // The lambda { println("Executing action!") } is boxed into an anonymous class that implements Function0<Unit>.
        // Each time repeatAction calls action(), it actually calls invoke() on the lambda object.
        // This results in extra object creation and function call overhead.

        // Example With inline (Optimized)
        repeatAction2(3) {
            println("Executing action!")
        }
        // How This Works Internally (With inline):
        // No function object is created for action.
        // The body of repeatAction is directly substituted at the call site.
        // No need to allocate memory for a lambda or call invoke().

        // Compiled Java-like equivalent:
        // public static void main() {
        //    for (int i = 0; i < 3; i++) {
        //        System.out.println("Executing action!");
        //    }
        // }

        // Non-Local Returns in inline Functions
        fun main() {
            execute {
                println("Inside block")
                return // Exits `main`, not just the lambda!
            }
            println("This line will never execute")
        }
        main()
        // How noinline Works
        process(
            inlineLambda = { println("This is an inline lambda") },
            nonInlineLambda = { println("This is a non-inline lambda") }
        )
        // How crossinline Works
        execute2 { { println() } }

        // Item 49: Consider using inline value classes
        // The @JvmInline annotation is required since Kotlin 1.5+.
        // value class replaces the older inline class keyword.
        // There’s only one property, which must be final and cannot be a var.
        fun convertToKilometers(distance: Meter): Kilometer {
            return Kilometer(distance.value / 1000)
        }

        val distanceInMeters = Meter(5000.0)
        val distanceInKm = convertToKilometers(distanceInMeters)

        println(distanceInKm.value) // Output: 5.0

        fun sendEmail(email: Email) {
            println("Sending email to ${email.value}")
        }

        val email = Email("user@example.com")
        sendEmail(email)

        // This will not compile:
        // sendEmail("user@example.com")  // Type mismatch error

        val orderId = OrderId("12345")
        orderId.printInfo() // Order ID: 12345

        fun printUserId(userId: UserId) {
            println("User ID: $userId")
        }

        printUserId(UserId("12345"))
    }
}
