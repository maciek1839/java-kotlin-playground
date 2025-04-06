# Kotlin release

*Notice that not all release's features are described. If you want to check release notes, go to [official documentation](https://kotlinlang.org/docs/home.html).*


- [2.1.0](https://kotlinlang.org/docs/whatsnew21.html) | November 27, 2024
  - New language features in preview:
    - Guard conditions in when with a subject, 
    - Non-local break and continue, 
    - Multi-dollar string interpolation.
  - Tooling updates and performance improvements
- [2.0.0](https://kotlinlang.org/docs/whatsnew20.html) | May 21, 2024
  - Stable release of the Kotlin K2 compiler
  - Stable AutoCloseable interface

```kotlin
interface XMLWriter {
    fun document(encoding: String, version: String, content: XMLWriter.() -> Unit)
    fun element(name: String, content: XMLWriter.() -> Unit)
    fun attribute(name: String, value: String)
    fun text(value: String)

    fun flushAndClose()
}

fun writeBooksTo(writer: XMLWriter) {
    val autoCloseable = AutoCloseable { writer.flushAndClose() }
    autoCloseable.use {
        writer.document(encoding = "UTF-8", version = "1.0") {
            element("bookstore") {
                element("book") {
                    attribute("category", "fiction")
                    element("title") { text("Harry Potter and the Prisoner of Azkaban") }
                    element("author") { text("J. K. Rowling") }
                    element("year") { text("1999") }
                    element("price") { text("29.99") }
                }
                element("book") {
                    attribute("category", "programming")
                    element("title") { text("Kotlin in Action") }
                    element("author") { text("Dmitry Jemerov") }
                    element("author") { text("Svetlana Isakova") }
                    element("year") { text("2017") }
                    element("price") { text("25.19") }
                }
            }
        }
    }
}
```

- [1.9.0](https://kotlinlang.org/docs/whatsnew19.html) | July 6, 2023
  - Stable ..< operator for open-ended ranges

```kotlin
val numbers = (1..<10).toList()
println(numbers)  // [1, 2, 3, ..., 9] (Excludes 10)
```

- [1.8.0](https://kotlinlang.org/docs/whatsnew18.html) | December 28, 2022
  - `kotlin-stdlib-jdk7` and `kotlin-stdlib-jdk8` merged into kotlin-stdlib

```kotlin
fun main() {
    val list = listOf("Apple", "Banana", "Orange")
    val result = list.filter { it.startsWith("A") }
    println(result)  // Output: [Apple]
}
```

- [1.7.0](https://kotlinlang.org/docs/whatsnew17.html) | June 9, 2022
- [1.6.0](https://kotlinlang.org/docs/whatsnew16.html) | November 16, 2021
- [1.5.0](https://blog.jetbrains.com/kotlin/2021/05/kotlin-1-5-0-released/) | May 5, 2021
  - Support for JVM records
    -  Remain interoperable with the latest Java features.
  - Sealed interfaces
    - The sealed modifier works on interfaces the same way it works on classes.
  - Sealed class improvements. 
    - Previously, all subclasses had to be in the same file with sealed classes. Now they can be in all files of the same compilation unit and the same package.
  - Inline classes
    - They are a subset of value-based classes that only hold values. 
    - You can use them as wrappers for a value of a certain type without the additional overhead that comes from using memory allocations.
  - The new JVM IR compiler, announced in Kotlin 1.4.0, becomes Stable and default.

Some selected features from older releases:
- [1.3.0](https://kotlinlang.org/docs/whatsnew13.html)
  - Coroutines

---

References:
- https://kotlinlang.org/docs/releases.html#release-details
