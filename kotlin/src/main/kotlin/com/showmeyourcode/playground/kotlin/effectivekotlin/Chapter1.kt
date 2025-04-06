package com.showmeyourcode.playground.kotlin.effectivekotlin

object Chapter1 {
    // Item 2: Minimize the scope of variables
    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    // Item 7: Prefer null or Failure result when the lack of result is possible
    class ParsingException : Exception()

    data class Person(val name: String)

    sealed class Result<out T>

    class Success<out T>(val result: T) : Result<T>()

    class Failure(val throwable: Throwable) : Result<Nothing>()

    inline fun <reified T> String.readObject(): Result<T> {
        // fake ugly dirty parser
        val incorrectSign = this.isBlank()
        val result = T::class.constructors.first().call(this)

        if (incorrectSign) {
            return Failure(ParsingException())
        }

        return Success(result)
    }

    fun demo() {
        // Item 2: Minimize the scope of variables
        // Bad
        fun updateWeather1(degrees: Int) {
            val description: String
            val color: Int
            if (degrees < 5) {
                description = "cold"
                color = Color.BLUE.rgb
            } else if (degrees < 23) {
                description = "mild"
                color = Color.GREEN.rgb
            } else {
                description = "hot"
                color = Color.RED.rgb
            }
            // ...
        }

        // Better
        fun updateWeather2(degrees: Int) {
            val (description, color) =
                when {
                    degrees < 5 -> "cold" to Color.BLUE
                    degrees < 23 -> "mild" to Color.GREEN
                    else -> "hot" to Color.RED
                }
        }

        updateWeather1(1)
        updateWeather2(2)

        // Item 7: Prefer null or Failure result when the lack of result is possible
        val name =
            when (val result = "Karel".readObject<Person>()) {
                is Failure -> "Exception: ${result.throwable}"
                is Success -> result.result.name
            }

        print(name)
    }
}
