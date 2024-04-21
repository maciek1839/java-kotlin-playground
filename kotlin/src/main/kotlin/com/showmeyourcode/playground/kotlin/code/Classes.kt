package com.showmeyourcode.playground.kotlin.code

class SmartDevice {
    var brand: String = ""
        get() = field
        set(value) {
            field = value
        }
}

class ConstructorExample(private val param1: String) {
    init {
        println("init is called.")
    }

    // First secondary constructor
    constructor(
        param1: String,
        param2: String
    ) : this(param1) {

        println("Second constructor is called")
    }

    // Second secondary constructor
    constructor(
        param1: String,
        param2: String,
        param3: String
    ) : this(param1) {

        println("Third constructor is called")
    }
}

// https://www.linkedin.com/pulse/constructors-init-block-kotlin-amit-nadiger
// init blocks are used to initialize the properties of a class.
// They are executed when an instance of the class is created.
// There can be one or more init blocks in a class, and they are executed in the order they are defined.
class Person(firstName: String, lastName: String) {
    val fullName: String

    init {
        fullName = "$firstName $lastName"
        println("Init block 1")
    }

    init {
        println("Init block 2")
    }
}

object Classes {
    fun main() {
        val smartDevice = SmartDevice()
        smartDevice.brand = "new brand"

        val obj = ConstructorExample(param1 = "1", param2 = "2", param3 = "3")
    }
}
