package com.showmeyourcode.playground.kotlin.effectivekotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

object Chapter3 {
    fun demo() {
        // Item 21: Use property delegation to extract common property patterns
        // Built-in Property Delegates
        // Lazy Initialization (lazy)
        class User {
            val fullName: String by lazy { loadUserName() }

            private fun loadUserName(): String {
                println("Loading user name...")
                return "Jan Kowalski"
            }
        }

        val user = User()
        println(user.fullName) // Loads and prints "Jan Kowalski"
        println(user.fullName) // Uses cached value, doesn't call loadUserName() again

        // Observable (Delegates.observable)
        class Person {
            var age: Int by Delegates.observable(18) { _, old, new ->
                println("Age changed from $old to $new")
            }
        }

        val person = Person()
        person.age = 21 // Prints: Age changed from 18 to 21

        // Vetoable (Delegates.vetoable)
        class Account {
            var balance: Int by Delegates.vetoable(1000) { _, old, new ->
                new >= 0 // Prevents setting a negative balance
            }
        }

        val account = Account()
        account.balance = 2000 // Allowed
        println(account.balance) // 2000

        account.balance = -500 // Rejected
        println(account.balance) // Still 2000

        // Custom Property Delegates
        class NonEmptyStringDelegate {
            private var value: String = "Default"

            operator fun getValue(
                thisRef: Any?,
                property: KProperty<*>
            ): String = value

            operator fun setValue(
                thisRef: Any?,
                property: KProperty<*>,
                newValue: String
            ) {
                require(newValue.isNotBlank()) { "${property.name} cannot be empty!" }
                value = newValue
            }
        }

        class Example {
            var message: String by NonEmptyStringDelegate()
        }

        val example = Example()
        example.message = "Hello, Kotlin!"
        println(example.message)

        // Item 22 - Use Generics When Implementing Common Algorithms
        fun <T : Comparable<T>> sortList(items: List<T>): List<T> {
            return items.sorted()
        }

        val intList = listOf(5, 3, 8, 1)
        val stringList = listOf("Kotlin", "Java", "Scala")

        println(sortList(intList)) // Output: [1, 3, 5, 8]
        println(sortList(stringList)) // Output: [Java, Kotlin, Scala]

        // Item 24: Consider variance for generic
        // Base class
        open class Vehicle(val name: String) {
            override fun toString(): String = name
        }

        // Subtypes of Vehicle
        open class LandVehicle(name: String) : Vehicle(name)

        class Car(name: String) : LandVehicle(name)

        class Truck(name: String) : LandVehicle(name)

        class Motorcycle(name: String) : LandVehicle(name)

        class Bicycle(name: String) : LandVehicle(name)

        // A completely different category of vehicle
        class Plane(name: String) : Vehicle(name)

        // Covariance (out) - Garage<T> (Producers)
        // A garage stores vehicles, but you can only read from it.
        // Because Garage<out T> is covariant, a Garage<Car> can be treated as a Garage<Vehicle>.
        // ✅ Covariant class: `Garage<T>` - Producer (read-only)
        class Garage<out T : Vehicle>(private val vehicle: T) {
            fun getVehicle(): T = vehicle // ✅ Read is allowed
            // fun setVehicle(vehicle: T) { this.vehicle = vehicle } // ❌ Write is NOT allowed
        }

        val carGarage: Garage<Car> = Garage(Car("Tesla"))
        val vehicleGarage: Garage<Vehicle> = carGarage // ✅ Works due to `out`
        println(vehicleGarage.getVehicle()) // Output: Tesla

        val truckGarage: Garage<Truck> = Garage(Truck("Volvo"))
        // val landVehicleGarage: Garage<LandVehicle> = truckGarage // ❌ Compilation Error!

        // Contravariance (in) - Washer<T> (Consumers)
        // A Washer can only accept input and wash it.
        // A Washer<Vehicle> should be able to wash any subtype (Car, Truck, Motorcycle).
        // ✅ Contravariant class: `Washer<T>` - Consumer (write-only)
        class Washer<in T : Vehicle> {
            fun wash(vehicle: T) {
                println("Washing ${vehicle.name}")
            }
        }

        val vehicleWasher: Washer<Vehicle> = Washer<Vehicle>()
        val carWasher: Washer<Car> = vehicleWasher // ✅ Works due to `in`

        carWasher.wash(Car("BMW")) // Output: Washing BMW
        // val bikeWasher: Washer<Bicycle> = Washer<Vehicle>() // ❌ Compilation Error!

        // Invariance (Default) - RepairShop<T>
        // By default, generics are invariant.
        // This means you cannot assign RepairShop<Car> to RepairShop<Vehicle>.
        class RepairShop<T : Vehicle> {
            fun repair(vehicle: T) {
                println("Repairing ${vehicle.name}")
            }
        }

        val carShop: RepairShop<Car> = RepairShop<Car>()
        carShop.repair(Car("Toyota")) // ✅ Works fine
        // val vehicleShop: RepairShop<Vehicle> = RepairShop<Car>() // ❌ Compilation Error!

        // Use-site Variance (in & out in Function Parameters)
        // Example: Use-site out (Read-only List)
        fun printVehicleNames(vehicles: List<out Vehicle>) { // `out Vehicle` means read-only
            vehicles.forEach { println(it.name) }
        }

        val cars: List<Car> = listOf(Car("Audi"), Car("Toyota"))
        printVehicleNames(cars) // ✅ Works due to `out Vehicle`

        // Example: Use-site in (Write-only List)
        fun addVehicles(vehicles: MutableList<in Car>) { // `in Car` means write-only
            vehicles.add(Car("Ford"))
        }

        val anyVehicles: MutableList<Vehicle> = mutableListOf()
        addVehicles(anyVehicles) // ✅ Works due to `in Car`
        println(anyVehicles) // Output: [Ford]

        // Function Types and Variance
        open class Animal

        class Dog : Animal()

        // Function type: (Dog) -> Animal
        val dogToAnimal: (Dog) -> Animal = { dog -> dog }

        // Function type: (Animal) -> Dog
        val animalToDog: (Animal) -> Dog = { Dog() }

        // ✅ Valid: Assigning a function with a `Dog` parameter to one expecting an `Animal` (Contravariant)
        val func1: (Animal) -> Animal = animalToDog

        // Kotlin: Arrays Are Invariant
        open class Animal1

        class Dog1 : Animal()

        val dogs: Array<Dog1> = arrayOf(Dog1(), Dog1())
        // val animals: Array<Animal> = dogs  // ❌ Compilation error (invariant)

        // To safely allow reading elements of an array in a covariant way, Kotlin provides Array<out T>.
        fun printAnimals(animals: Array<out Animal>) { // `out` allows only reading
            for (animal in animals) {
                println(animal)
            }
        }

        val dogs2: Array<Dog> = arrayOf(Dog(), Dog())
        printAnimals(dogs2) // ✅ Works fine
    }
}
