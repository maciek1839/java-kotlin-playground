package com.showmeyourcode.playground.kotlin.effectivekotlin

object Chapter8 {
    fun demo() {
        // Item 51: Prefer Sequence for big collections with more than one processing step
        println("Element-by-element or lazy order:")
        sequenceOf(1, 2, 3)
            .filter {
                print("F$it, ")
                it % 2 == 1
            }
            .map {
                print("M$it, ")
                it * 2
            }
            .forEach { print("E$it, ") }

        println("\nStep-by-step or eager order:")
        listOf(1, 2, 3)
            .filter {
                print("F$it, ")
                it % 2 == 1
            }
            .map {
                print("M$it, ")
                it * 2
            }
            .forEach { print("E$it, ") }

        // Number of operations
        println("Sequence:")
        val sequenceResult =
            (1..10).asSequence()
                .filter {
                    print("F$it, ")
                    it % 2 == 1
                }
                .map {
                    print("M$it, ")
                    it * 2
                }
                .find { it > 5 }
        println("Result = $sequenceResult")

        println("List:")
        val listResult =
            (1..10)
                .filter {
                    print("F$it, ")
                    it % 2 == 1
                }
                .map {
                    print("M$it, ")
                    it * 2
                }
                .find { it > 5 }
        println("Result = $listResult")
        // Sequence:
        // F1, M1, F2, F3, M3, Result = 6
        // List:
        // F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, M1, M3, M5, M7, M9, Result = 6

        // Generate an infinite sequence of natural numbers (1, 2, 3, 4, ...)
        val naturalNumbers =
            sequence {
                var n = 1
                while (true) {
                    yield(n) // Lazily yield numbers one by one
                    n++
                }
            }

        // Take first 10 elements from the sequence (example of lazy processing)
        naturalNumbers.take(10).forEach { println(it) }

        // Item 52: Consider associating elements to a map
        val words2 = listOf("apple", "banana", "apple", "orange", "banana", "banana")

        // Count occurrences of each word
        val wordCount2 = words2.groupingBy { it }.eachCount()

        println(wordCount2)
        // Output: {apple=2, banana=3, orange=1}

        data class User(val id: Int, val name: String, val email: String)
        val users =
            listOf(
                User(1, "Alice", "alice@example.com"),
                User(1, "Bob", "bob@example.com")
            )

        val usersMap = users.associateBy { it.id }
        println(usersMap) // Output: {1=User(id=1, name=Bob, email=bob@example.com)}

        // Associating Elements for Counting, Summing, or Aggregating
        val words = listOf("apple", "banana", "apple", "orange", "banana", "banana")
        val wordCount = words.groupingBy { it }.eachCount()

        println(wordCount) // Output: {apple=2, banana=3, orange=1}

        // Item 53: Consider using groupingBy instead of groupBy
        // Example 1: Using groupBy
        data class Student2(val name: String, val grade: String)

        val students =
            listOf(
                Student2("Alice", "A"),
                Student2("Bob", "B"),
                Student2("Charlie", "A"),
                Student2("David", "B"),
                Student2("Eve", "A")
            )

        val groupedByGrade = students.groupBy { it.grade }

        println(groupedByGrade)
        // Output: {A=[Student(name=Alice, grade=A), Student(name=Charlie, grade=A), Student(name=Eve, grade=A)],
        //          B=[Student(name=Bob, grade=B), Student(name=David, grade=B)]}

        // Example 2: Using groupingBy
        val students2 =
            listOf(
                Student2("Alice", "A"),
                Student2("Bob", "B"),
                Student2("Charlie", "A"),
                Student2("David", "B"),
                Student2("Eve", "A")
            )

        val groupingByGrade = students2.groupingBy { it.grade }

        // Counting the number of students in each grade (without creating a full map first)
        val countByGrade = groupingByGrade.eachCount()
        println(countByGrade)
        // Output: {A=3, B=2}

        // Grouping and applying a transformation (finding the names of students per grade)
        val namesByGrade =
            groupingByGrade.fold(emptyList<String>()) { acc, student ->
                acc + student.name
            }
        println(namesByGrade)
        // Output: {A=[Alice, Charlie, Eve], B=[Bob, David]}

        // Example 3: Grouping and Aggregating by Multiple Keys
        data class Person(val name: String, val city: String, val age: Int)

        val people =
            listOf(
                Person("Alice", "New York", 30),
                Person("Bob", "Los Angeles", 25),
                Person("Charlie", "New York", 35),
                Person("David", "Los Angeles", 30),
                Person("Eve", "Chicago", 25)
            )

        // Group by city and then age
        val groupedByCityAndAge =
            people.groupingBy { it.city }
                .fold(emptyList<String>()) { acc, person ->
                    acc + person.name
                }

        println(groupedByCityAndAge)
        // Output: {New York=[Alice, Charlie], Los Angeles=[Bob, David], Chicago=[Eve]}

        // Item 54: Limit the number of operations
        class Student(val name: String?)

        // Works
        fun List<Student>.getNames(): List<String> =
            this
                .map { it.name }
                .filter { it != null }
                .map { it!! }

        // Better
        // fun List<Student>.getNames(): List<String> =
        //    this
        //        .map { it.name }
        //        .filterNotNull()

        // Best
        // fun List<Student2>.getNames(): List<String> =
        //    this
        //        .mapNotNull { it.name }
    }
}
