# Kotlin release

*Notice that not all release's features are described. If you want to check release notes, go to [official documentation](https://kotlinlang.org/docs/home.html).*

- [2.2.0](https://kotlinlang.org/docs/whatsnew22.html) | May 28, 2025
  - Guard conditions in when with a subject (Stable, promoted from preview in 2.1)
  - Non-local break and continue (Stable)
  - Multi-dollar string interpolation (Stable)
  - Improved K2 compiler diagnostics

- [2.1.20](https://kotlinlang.org/docs/whatsnew2120.html) | March 20, 2025
  - K2 compiler stability improvements and bug fixes
  - Gradle build improvements (project isolation support, new DSL for compiler options)
  - Kotlin/Native: custom allocator enabled by default with improved GC performance
  - Improved interop with Swift 6.1

- [2.1.0](https://kotlinlang.org/docs/whatsnew21.html) | November 27, 2024
  - New language features in preview:
    - Guard conditions in when with a subject, 
    - Non-local break and continue, 
    - Multi-dollar string interpolation.
  - Tooling updates and performance improvements

- [2.0.0](https://kotlinlang.org/docs/whatsnew20.html) | May 21, 2024
  - Stable release of the Kotlin K2 compiler
  - Stable AutoCloseable interface
  - New Enum class values function (`entries` property)
  - Stable data object declarations
  - Stable `..< ` (rangeUntil) operator

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
            }
        }
    }
}
```

- [1.9.0](https://kotlinlang.org/docs/whatsnew19.html) | July 6, 2023
  - Stable `..< ` operator for open-ended ranges
  - Stable replacement of enum `values()` with `entries` property
  - Stable `@Volatile` annotation for Kotlin/Native
  - New common function to create regex capture groups by name

- [1.8.0](https://kotlinlang.org/docs/whatsnew18.html) | December 28, 2022
  - `kotlin-stdlib-jdk7` and `kotlin-stdlib-jdk8` merged into kotlin-stdlib
  - Improved kotlin-reflect performance
  - New recursively copyable or deletable directory functions
  - Improved Objective-C/Swift interoperability

- [1.7.0](https://kotlinlang.org/docs/whatsnew17.html) | June 9, 2022
  - Stable inline classes
  - Stable opt-in requirement annotations
  - Stable definitely non-nullable types
  - Builder inference improvements

- [1.6.0](https://kotlinlang.org/docs/whatsnew16.html) | November 16, 2021
  - Stable exhaustive when statements for sealed and Boolean subjects
  - Stable suspending functions as supertypes
  - Stable conversions from regular to suspend functional types
  - Stable instantiation of annotation classes

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

- [1.4.0](https://kotlinlang.org/docs/whatsnew14.html) | August 17, 2020
  - SAM conversions for Kotlin interfaces
  - Trailing comma support
  - Named arguments in middle position
  - New JVM IR backend (experimental)

Some selected features from older releases:
- [1.3.0](https://kotlinlang.org/docs/whatsnew13.html)
  - Coroutines
  - Contracts
  - Inline classes (experimental)

---

References:
- https://kotlinlang.org/docs/releases.html#release-details
