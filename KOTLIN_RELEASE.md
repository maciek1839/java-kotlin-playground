# Kotlin release

*Notice that not all release's features are described. If you want to check release notes, go to [official documentation](https://kotlinlang.org/docs/home.html).*

- [1.9.0](https://kotlinlang.org/docs/whatsnew19.html) | July 6, 2023
  - Stable ..< operator for open-ended ranges
- [1.8.0](https://kotlinlang.org/docs/whatsnew18.html) | December 28, 2022
  - `kotlin-stdlib-jdk7` and `kotlin-stdlib-jdk8` merged into kotlin-stdlib
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
