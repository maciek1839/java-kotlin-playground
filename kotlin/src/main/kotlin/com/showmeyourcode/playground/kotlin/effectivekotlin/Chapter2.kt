package com.showmeyourcode.playground.kotlin.effectivekotlin

object Chapter2 {
    fun demo() {
        // Item 11: Design for readability
        cognitiveLoad()
        cognitiveLoadModified()

        // Item 15: Consider referencing receivers explicitly
        fun <T : Comparable<T>> List<T>.quickSort(): List<T> {
            if (size < 2) return this
            val pivot = first()
            val (smaller, bigger) =
                drop(1)
                    .partition { it < pivot }
            return smaller.quickSort() + pivot + bigger.quickSort()
        }
        println(listOf(5, 4, 12, 46, 43, 6, 32, 2).quickSort())

        // Item 17: Consider naming arguments
        fun greetPerson(
            name: String,
            age: Int = 20,
            city: String = "Unknown"
        ) {
            println("Hi, I'm $name, I'm $age years old from $city.")
        }

        // Skip 'age' and provide only 'name' and 'city'
        greetPerson(name = "Charlie", city = "New York")
    }

    private data class Person(val isAdult: Boolean = true)

    private class View {
        fun showPerson(person: Person) = println(person)

        fun showError() = println("Error!")

        fun hideProgress() = println("Progress hidden.")

        fun hideProgressWithSuccess() = println("Progress hidden with success.")
    }

    private fun cognitiveLoad() {
        val person: Person? = Person()
        val view = View()

        // Implementation A
        if (person != null && person.isAdult) {
            view.showPerson(person)
        } else {
            view.showError()
        }

        // Implementation B - Kotlin idiomatic
        person?.takeIf { it.isAdult } // safe call, takeIf
            ?.let(view::showPerson) // bounded function reference
            ?: view.showError() // Elvis operator

        // Which one is better, A or B? - depends on how fast we can understand each of them
    }

    private fun cognitiveLoadModified() {
        val person: Person? = Person()
        val view = View()

        // Implementation A
        if (person != null && person.isAdult) {
            view.showPerson(person)
            view.hideProgressWithSuccess()
        } else {
            view.showError()
            view.hideProgress()
        }

        // Implementation B - Kotlin idiomatic
        person?.takeIf { it.isAdult }
            ?.let {
                // view::showPerson - can't be used
                view.showPerson(it)
                view.hideProgressWithSuccess()
            }
            ?: run { // run added
                view.showError()
                view.hideProgress()
            }

        // Have you noticed that implementation A and B do not even work the same way? - demonstrate on CognitiveLoad
    }
}
