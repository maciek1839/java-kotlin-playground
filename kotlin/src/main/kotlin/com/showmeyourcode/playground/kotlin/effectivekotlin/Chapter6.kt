package com.showmeyourcode.playground.kotlin.effectivekotlin

object Chapter6 {
    interface Counter {
        fun count(): Int
    }

    interface Engine {
        fun start()
    }

    // SAM Interface: Useful when extending
    fun interface Transformer {
        fun transform(value: String): String
    }

    // Item 39: Use sealed classes and interfaces to express restricted hierarchies
    // Example 1: Sealed Classes for Modeling States
    // Sealed class defining the state of a network request
    sealed class NetworkState {
        object Loading : NetworkState() // Represents a loading state

        data class Success(val data: String) : NetworkState() // Represents a successful result

        data class Failure(val error: String) : NetworkState() // Represents an error

        // A function to process the network state
        fun handleState() {
            when (this) {
                is Loading -> println("Loading data...")
                is Success -> println("Data loaded: $data")
                is Failure -> println("Error: $error")
            }
        }
    }

    // Example 2: Sealed Interfaces for Modeling Actions
    // Sealed interface for defining user actions
    sealed interface UserAction {
        data class Login(val username: String, val password: String) : UserAction

        data class Logout(val username: String) : UserAction

        data class Register(val username: String, val email: String, val password: String) : UserAction
    }

    // Item 40: Prefer class hierarchies to tagged classes
    class ValueMatcherTagged<T> private constructor(
        private val value: T? = null,
        private val matcher: Matcher
    ) {
        fun match(value: T?) =
            when (matcher) {
                Matcher.EQUAL -> value == this.value
                Matcher.NOT_EQUAL -> value != this.value
                Matcher.LIST_EMPTY -> value is List<*> && value.isEmpty()
                Matcher.LIST_NOT_EMPTY -> value is List<*> && value.isNotEmpty()
            }

        enum class Matcher {
            EQUAL,
            NOT_EQUAL,
            LIST_EMPTY,
            LIST_NOT_EMPTY
        }

        companion object {
            fun <T> equal(value: T) =
                ValueMatcherTagged(
                    value = value,
                    matcher = Matcher.EQUAL
                )

            fun <T> notEqual(value: T) =
                ValueMatcherTagged(
                    value = value,
                    matcher = Matcher.NOT_EQUAL
                )

            fun <T> emptyList() = ValueMatcherTagged<T>(matcher = Matcher.LIST_EMPTY)

            fun <T> notEmptyList() = ValueMatcherTagged<T>(matcher = Matcher.LIST_NOT_EMPTY)
        }
    }

    sealed class ValueMatcher {
        abstract fun match(input: String): Boolean

        class Equals(private val value: String) : ValueMatcher() {
            override fun match(input: String) = input == value
        }

        class Contains(private val value: String) : ValueMatcher() {
            override fun match(input: String) = input.contains(value)
        }

        class StartsWith(private val value: String) : ValueMatcher() {
            override fun match(input: String) = input.startsWith(value)
        }
    }

    enum class State {
        STARTED,
        STOPPED,
        PAUSED
    }

    // Tagged class pattern using enum
    class Player(val state: State) {
        fun play() {
            when (state) {
                State.STARTED -> println("Player is already playing.")
                State.STOPPED -> println("Starting the player.")
                State.PAUSED -> println("Resuming the player.")
            }
        }

        fun stop() {
            when (state) {
                State.STARTED -> println("Stopping the player.")
                State.PAUSED -> println("Player is paused. Can't stop.")
                State.STOPPED -> println("Player is already stopped.")
            }
        }

        fun pause() {
            when (state) {
                State.STARTED -> println("Pausing the player.")
                State.PAUSED -> println("Player is already paused.")
                State.STOPPED -> println("Can't pause. Player is stopped.")
            }
        }
    }

    // Define the states using sealed classes
    sealed class PlayerState {
        object Started : PlayerState()

        object Stopped : PlayerState()

        object Paused : PlayerState()
    }

    // Sealed class pattern for the state pattern
    class Player2(private var state: PlayerState) {
        fun play() {
            when (state) {
                is PlayerState.Started -> println("Player is already playing.")
                is PlayerState.Stopped -> {
                    println("Starting the player.")
                    state = PlayerState.Started
                }

                is PlayerState.Paused -> {
                    println("Resuming the player.")
                    state = PlayerState.Started
                }
            }
        }

        fun stop() {
            when (state) {
                is PlayerState.Started -> {
                    println("Stopping the player.")
                    state = PlayerState.Stopped
                }

                is PlayerState.Paused -> println("Player is paused. Can't stop.")
                is PlayerState.Stopped -> println("Player is already stopped.")
            }
        }

        fun pause() {
            when (state) {
                is PlayerState.Started -> {
                    println("Pausing the player.")
                    state = PlayerState.Paused
                }

                is PlayerState.Paused -> println("Player is already paused.")
                is PlayerState.Stopped -> println("Can't pause. Player is stopped.")
            }
        }
    }

    // Item 41: Use enum to represent a list of values
    data class Transaction(val amount: Double, val currency: String)

    enum class PaymentOption(
        val startPayment: (Transaction) -> Unit
    ) {
        CASH(::showCashPaymentInfo),
        CARD(::moveToCardPaymentPage),
        TRANSFER({
            showMoneyTransferInfo()
            setupPaymentWatcher(it)
        })
    }

    fun showCashPaymentInfo(transaction: Transaction) {
        println("Processing cash payment of ${transaction.amount} ${transaction.currency}")
    }

    fun moveToCardPaymentPage(transaction: Transaction) {
        println("Redirecting to card payment page for ${transaction.amount} ${transaction.currency}")
    }

    fun showMoneyTransferInfo() {
        println("Displaying money transfer info.")
    }

    fun setupPaymentWatcher(transaction: Transaction) {
        println("Setting up payment watcher for ${transaction.amount} ${transaction.currency}")
    }

    inline fun <reified T : Enum<T>> printEnumValues() {
        for (value in enumValues<T>()) {
            println(value)
        }
    }

    // Enum with Comparable for status
    enum class Status : Comparable<Status> {
        SUCCESS,
        ERROR,
        LOADING
    }

    // Enum vs sealed class
    sealed class PaymentOption2 {
        data class Cash(val transaction: Transaction) : PaymentOption2()

        data class Card(val transaction: Transaction) : PaymentOption2()

        data class Transfer(val transaction: Transaction) : PaymentOption2()

        // Alternativly you can add a method and implement for each data class
        // abstract fun startPayment()

        companion object {
            fun showCashPaymentInfo(transaction: Transaction) {
                println("Processing cash payment: $transaction")
            }

            fun moveToCardPaymentPage(transaction: Transaction) {
                println("Redirecting to card payment page for: $transaction")
            }

            fun setupPaymentWatcher(transaction: Transaction) {
                println("Setting up payment watcher for: $transaction")
            }
        }
    }

    // Item 45: Consider extracting non-essential parts of your API into extensions
    // Bad Approach: Mixing Core & Utility Functions
    class User40(val name: String, val email: String) {
        fun printUserInfo() {
            println("User: $name, Email: $email")
        }

        // Here, obfuscateEmail() is not essential for User itself—it’s just a helper function.
        // Over time, such non-essential methods clutter the class.
        fun obfuscateEmail(): String {
            val parts = email.split("@")
            return "${parts[0].take(2)}***@${parts[1]}"
        }
    }

    // ✅ Better Approach: Extract Non-Essential API into Extensions
    data class User41(val name: String, val email: String)

    // Extension function for additional behavior
    fun User41.obfuscateEmail(): String {
        val parts = email.split("@")
        return "${parts[0].take(2)}***@${parts[1]}"
    }

    // Item 46: Avoid member extensions
    // Reference is not supported
    interface PhoneBook {
        fun String.isPhoneNumber(): Boolean
    }

    class PhoneBookImpl : PhoneBook {
        override fun String.isPhoneNumber(): Boolean = length == 7 && all { it.isDigit() }

        fun checkNumber(number: String): Boolean = number.isPhoneNumberPrivate()

        private fun String.isPhoneNumberPrivate() = isPhoneNumber()

        // Reference is not supported!
        //    private val ref = String::isPhoneNumber // ERROR
        //    private val defaultNumber = "1234567"
        //    private val boundedRef = defaultNumber::isPhoneNumber // ERROR
    }

    fun demo() {
        // Item 36: Prefer composition over inheritance
        // Interface delegation is a feature in Kotlin that allows a class to delegate the implementation of an interface to another object,
        // reducing boilerplate and improving code reuse. Instead of implementing all interface methods manually,
        // the class can delegate them to an instance of another class that already provides the required implementation.
        class CounterSet(private val delegate: Counter) : Counter {
            override fun count() = delegate.count()
        }

        // Bad Example: Inheritance Misuse (Tight Coupling & Unnecessary Exposure)
        open class Car {
            open fun startEngine() {
                println("Engine started")
            }

            open fun refuel() {
                println("Filling up gas tank")
            }
        }

        class ElectricCar : Car() {
            override fun refuel() {
                println("Charging battery instead of refueling")
            }
        }

        val tesla = ElectricCar()
        tesla.startEngine() // Makes no sense for an electric car!
        tesla.refuel() // Overriding refuel is a hacky workaround
        // Issues:
        // ElectricCar doesn’t need an engine, but it still inherits startEngine().
        // Refueling doesn’t apply, so we override it with Charging battery instead.
        // Future changes to Car might break ElectricCar, making the hierarchy fragile.

        // Preferred Alternative: Composition (More Flexible & Encapsulated)
        class GasEngine : Engine {
            override fun start() = println("Starting gas engine")
        }

        class ElectricMotor : Engine {
            override fun start() = println("Starting electric motor")
        }

        class Vehicle(private val engine: Engine) {
            fun startVehicle() = engine.start()
        }

        val gasCar = Vehicle(GasEngine())
        gasCar.startVehicle() // ✅ "Starting gas engine"

        val tesla2 = Vehicle(ElectricMotor())
        tesla2.startVehicle() // ✅ "Starting electric motor"
        // Now, Vehicle can work with any engine type, without unnecessary inheritance.
        // Vehicle does not carry unnecessary methods (refuel()), reducing confusion.
        // We can extend behavior by creating new engines (HybridEngine, etc.) without modifying Vehicle.

        // Good Example: Inheritance When There’s a Clear Relationship
        abstract class Animal {
            abstract fun makeSound()
        }

        class Dog : Animal() {
            override fun makeSound() = println("Woof!")
        }

        class Cat : Animal() {
            override fun makeSound() = println("Meow!")
        }

        val animals: List<Animal> = listOf(Dog(), Cat())
        animals.forEach { it.makeSound() } // ✅ Polymorphism works well

        // Item 37: Use the data modifier to represent a bundle of data
        // Bad Use of Pair (Should Use a Data Class Instead)
        fun getUserInfo(): Pair<String, Int> {
            return Pair("Alice", 25)
        }

        val (name, age) = getUserInfo()
        println("User: $name, Age: $age") // ❌ Not very descriptive

        // Good Example: Using a Data Class for Clarity
        data class User266(val name: String, val age: Int)

        fun getUserInfo2(): User266 {
            return User266("Alice", 25)
        }

        val user2 = getUserInfo2()
        println("User: ${user2.name}, Age: ${user2.age}") // ✅ Clear and readable

        // Item 38: Use function types instead of interfaces to pass operations and actions
        // Function type: Simple and concise
        val transformer: (String) -> String = { it.uppercase() }

        val samTransformer = Transformer { it.uppercase() }

        // Item 39: Use sealed classes and interfaces to express restricted hierarchies
        // Example 1: Sealed Classes for Modeling States

        val loading = NetworkState.Loading
        val success = NetworkState.Success("Data received successfully")
        val failure = NetworkState.Failure("Network error")

        loading.handleState()
        success.handleState()
        failure.handleState()

        // Example 2: Sealed Interfaces for Modeling Actions

        fun handleAction(action: UserAction) {
            when (action) {
                is UserAction.Login -> println("Logging in ${action.username}")
                is UserAction.Logout -> println("Logging out ${action.username}")
                is UserAction.Register -> println("Registering ${action.username} with email ${action.email}")
            }
        }

        val login = UserAction.Login("user1", "password123")
        val logout = UserAction.Logout("user1")
        val register = UserAction.Register("user2", "user2@example.com", "password456")

        handleAction(login)
        handleAction(logout)
        handleAction(register)

        // Item 40: Prefer class hierarchies to tagged classes
        ValueMatcherTagged.equal("1").match("2")
        ValueMatcher.Equals("a").match("a")

        // Item 41: Use enum to represent a list of values
        fun PaymentOption.startPayment(transaction: Transaction) {
            when (this) {
                PaymentOption.CASH -> showCashPaymentInfo(transaction)
                PaymentOption.CARD -> moveToCardPaymentPage(transaction)
                PaymentOption.TRANSFER -> {
                    showMoneyTransferInfo()
                    setupPaymentWatcher(transaction)
                }
            }
        }

        // Testing PaymentOption enum
        val options = PaymentOption.values()
        println(options.map { it.name })
        // Output: [CASH, CARD, TRANSFER]

        val options2: Array<PaymentOption> = enumValues()
        println(options2.map { it.name })
        // Output: [CASH, CARD, TRANSFER]

        val option: PaymentOption = PaymentOption.valueOf("CARD")
        println(option) // Output: CARD

        val option2: PaymentOption = enumValueOf<PaymentOption>("CARD")
        println(option2) // Output: CARD

        printEnumValues<PaymentOption>()
        // Output:
        // CASH
        // CARD
        // TRANSFER

        val status1 = Status.SUCCESS
        val status2 = Status.ERROR

        if (status1 < status2) {
            println("${status1.name} is considered less than ${status2.name}")
        } else {
            println("${status1.name} is considered greater than or equal to ${status2.name}")
        }

        // Item 44: Respect the contract of compareTo
        println(5.compareTo(3)) // Output: 1 (5 > 3)
        println("apple".compareTo("banana")) // Output: -1 ("apple" < "banana")
        println("Kotlin" > "Java") // true ('K' > 'J')

        data class Person(val name: String, val age: Int) : Comparable<Person> {
            override fun compareTo(other: Person): Int {
                return this.age.compareTo(other.age) // Sort by age
            }
        }

        val p1 = Person("Alice", 30)
        val p2 = Person("Bob", 25)

        println(p1 > p2) // true (Alice is older than Bob)

        // Item 45: Consider extracting non-essential parts of your API into extensions
        val phoneBook = PhoneBookImpl()

        val user = User40("Alice", "alice@example.com")
        println(user.obfuscateEmail()) // Output: al***@example.com

        //    phoneBook.isPhoneNumber() // ERROR
        //    "1234567".isPhoneNumber() // ERROR
        phoneBook.run { println("1234567".isPhoneNumber()) } // it only makes it more complicated to use

        println(phoneBook.checkNumber("12345678"))

        // Ambiguity between receivers
        class A {
            val a = 10
        }

        class B {
            val a = 20
            val b = 30

            fun A.test() = a + b // Is it 40 or 50?

            fun A.update() {} // shell update A (receiver) or B (dispatch receiver)?
        }

        val a = A()
        val b = B()

        b.run { println(a.test()) }
    }
}
