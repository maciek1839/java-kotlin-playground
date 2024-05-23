# Functional programming

Functional programming is a declarative programming paradigm style (a style of building the structure and elements of computer programs—that expresses the logic of a computation without describing its control flow) where one applies pure functions in sequence to solve complex problems. 

The number 1 goal of functional programming is to write code that has no side effects. 
This leads to code that is: easier to read and write (especially when codebase grows in time and you have to remember about all global variables if no functional programming).

`Date.now()` is not a pure function because it depends on environment. It's unpredictable.

>What is the best in functional programming?
Data immutability and functional approach (chain of functions is easier to understand then imperative programming)

**Fundamental Principles and Concepts**:
- First-Class and Higher-Order Functions
  - A higher order function is a function that takes one or more functions as arguments, or returns a function as its result.
  - For example, to square a list of numbers, you may do something like `Enum.map(list, fn x -> x * x end)`, where `fn x -> x * x end` is a function that is called for each item in the list (variable x).
  - Functions are first class, just like integers, strings, etc.
  - You can create functions, save them to variables, pass them to other functions, and return them from another function.
- Pure functions
  - A pure function is one without side effects / making any visible state changes and always return the same result for the same input.
  - Side effects are any changes in the state of the program or the environment that are not reflected in the function's output. Side effects can make the program unpredictable, hard to test, and difficult to debug e.g. variation with local static variables, non-local variables, fetch data from a cookie, mutable reference arguments or input streams.
- Immutability
  - It refers to the property that an entity can’t be modified after being instantiated.
  - Variables (and data) can be set once, and cannot be changed one set. This pretty well rules out global variables.
  - Immutability rules out the use of imperative constructs like for, while, etc. Instead, we use recursion for iteration.
- Referential Transparency
  - An expression is referentially transparent if it can be replaced with its corresponding value without changing the program's behavior. Pure functions ensure referential transparency.

**Functional Programming Techniques**:
- Function Composition
  - Function composition is the process of combining two or more functions to produce a new function. This can be done using `compose` and `andThen` methods in Java.
- Monad Pattern
  - Formally, a monad is an abstraction that allows structuring programs generically. So, a monad allows us to wrap a value, apply a set of transformations, and get the value back with all transformations applied.
  - In Java, the Optional class is a simple form of a monad used to represent optional values. Two of the most commonly known Java 8 features are monad implementations, namely Stream and Optional.
  - Supporting languages may use monads to abstract away boilerplate code needed by the program logic.
- Currying
  - Currying is a mathematical technique of converting a function that takes multiple arguments into a sequence of functions that take a single argument.
  - In functional programming, it gives us a powerful composition technique where we don’t need to call a function with all its arguments. Moreover, a curried function does not realize its effect until it receives all the arguments.
- Recursion over iteration
  - Recursion is a fundamental concept in functional programming where a function calls itself to solve smaller instances of the same problem. Recursion is often used in place of iteration (loops) in functional programming to handle repetitive tasks.
  - Recursion can be slower than iteration because, in addition to processing the loop content, it has to deal with the recursive call stack frame. One of the drawbacks of recursion in a non-functional language is that each recursive call creates a new stack frame. This can eventually cause a stack overflow if the recursion runs for many iterations. Functional languages typically implement something called tail call optimization. If the last statement in a recursive function is a call to itself, then a new stack frame is not created. This allows some programming languages and runtimes to optimize the recursive calls, preventing stack overflow and making the recursion as efficient as iteration.
  - Java does not natively support tail call optimization (TCO) or tail recursion elimination (TRE). This means that even if you write a tail-recursive function, the Java Virtual Machine (JVM) will not optimize it to use constant stack space, and each recursive call will consume a new frame on the stack. This can lead to stack overflow errors for deep recursion.
  - Kotlin supports tail recursion and provides a special annotation tailrec to indicate that a function is tail-recursive. When a function is marked with tailrec, the Kotlin compiler optimizes it to use a loop instead of recursive calls, thus preventing stack overflow.
- State management and side effect constructs
  - Programs need state. They need side effects (IO for example).
  - Functional languages support these concepts with different techniques (based on the language). Please, don’t get dissuaded by all the ignorance on the internet that say functional languages are impractical because of immutability and pure functions. Functional languages simply make state management explicit. This is a good thing, especially when you add concurrency to your application.

[Reference](https://www.quora.com/What-are-the-characteristics-of-a-functional-programming-language)

## How to deal with side effects in functional programming?

For the sake of brevity, let me (over)simplify and make the long story short:

To deal with "side effects" in purely functional programming, you (programmers) write pure functions from the input to the output, and the system causes the side effects by applying those pure functions to the "real world".

For example, to read an integer x and write x+1, you (roughly speaking) write a function f(x) = x+1, and the system applies it to the real input and outputs its return value.

For another example, instead of raising an exception as a side effect, your pure function returns a special value representing the exception. Various "monads" such as IO in Haskell generalize these ideas, that is, represent side effects by pure functions (actual implementations are more complicated, of course).

[Reference](https://stackoverflow.com/questions/38331690/side-effects-in-functional-programming)

## Java

The `java.util.function.Function` interface is a key component of the Java 8 functional programming API.

Function and BiFunction interfaces provide methods like compose, andThen, and apply to create more complex function chains:
- apply: Directly applies a function to its argument.
- compose: Composes two functions where the function passed to compose is executed first.
- andThen: Composes two functions where the current function is executed first.

To achieve functional programming in Java:
- Use Immutable Data Structures to avoid side effects.
- Leverage Functional Interfaces like Function, Predicate, Consumer, and Supplier.
- Utilize Lambda Expressions and Method References for concise and readable code.
- Employ the Stream API for functional operations on collections.
- Avoid Mutable State by using local variables and final fields.
- Use Optional to handle the presence or absence of values without nulls (in other words use Optional for Null-Safety).
- Incorporate Vavr for functional error handling and additional functional data structures.

### Intermediate & Terminal operations

In Java's Stream API, operations are divided into two categories: intermediate operations and terminal operations.

Key points:
- Laziness: Intermediate operations are not executed until a terminal operation is invoked.
- Chaining: Intermediate operations return a stream, allowing multiple operations to be chained together.
- Eagerness: Terminal operations trigger the execution of the stream pipeline and produce a result or perform a side effect.

#### Intermediate Operations

Intermediate operations are operations that return another stream. They are lazy, meaning they do not get executed immediately but are instead queued up. Intermediate operations are only executed when a terminal operation is called on the stream.

Common intermediate operations include:
- filter: Filters elements based on a predicate.
- map: Transforms each element using a function.
- flatMap: Transforms each element using a function and flattens the result.
- distinct: Removes duplicate elements.
- sorted: Sorts the elements.
- peek: Performs an action on each element as they are consumed by the terminal operation.
- limit: Limits the number of elements.
- skip: Skips the first n elements.

#### Terminal Operations

Terminal operations are operations that produce a result or a side effect. They are eager, meaning they trigger the execution of the stream pipeline and produce a result or perform an action.

Common terminal operations include:
- forEach: Performs an action for each element.
- collect: Collects the elements into a collection or another result container.
- reduce: Reduces the elements to a single value using an associative accumulation function.
- count: Counts the number of elements.
- anyMatch, allMatch, noneMatch: Checks if any, all, or none of the elements match a given predicate.
- findFirst: Finds the first element.
- findAny: Finds any element (useful in parallel streams).
- toArray: Collects the elements into an array.

#### More examples

- https://github.com/sameershukla/JavaFPLearning

## Kotlin

To achieve functional programming in Kotlin, you can utilize various language features and constructs that promote immutability, higher-order functions, lambda expressions, and functional data processing.

See more [FunctionalProgramming.kt](kotlin/src/main/kotlin/com/showmeyourcode/playground/kotlin/code/paradigm/functional/FunctionalProgramming.kt).

**Kotlin’s standard library** provides many useful functions for functional programming, such as:
- `let` is used to execute a block of code on the object it is called on and returns the result of the block. It is commonly used for null-checks and to limit the scope of variables.
```kotlin
val str: String? = "Kotlin"
val length = str?.let {
    println("The string length is ${it.length}")
    it.length
} ?: 0
println(length) // Output: 6
```
- `run` is used to execute a block of code and return its result. It can be called on an object to operate on it or as a standalone function.
```kotlin
val result = run {
  val a = 5
  val b = 10
  a + b
}
println(result) // Output: 15

val person = Person("John", 30)
val ageNextYear = person.run {
  age + 1
}
println(ageNextYear) // Output: 31

```
- `apply` is used to configure an object and return the object itself. It is often used for object initialization.
```kotlin
val person = Person("John", 30).apply {
    name = "Jane"
    age = 25
}
println(person) // Output: Person(name=Jane, age=25)
```
- `also` is similar to apply but returns the object it is called on. It is typically used for performing additional actions on an object.
```kotlin
val numbers = mutableListOf(1, 2, 3).also {
    println("Initial list: $it")
    it.add(4)
}
println(numbers) // Output: [1, 2, 3, 4]
```
- `with` is used to call multiple methods on the same object without repeating the object reference. It returns the result of the lambda block.
```kotlin
val person = Person("John", 30)
val info = with(person) {
    "Name: $name, Age: $age"
}
println(info) // Output: Name: John, Age: 30
```
- `takeIf` returns the object if it satisfies the given predicate, otherwise it returns null. `takeUnless` returns the object if it does not satisfy the predicate.
```kotlin
val number = 42
val evenNumber = number.takeIf { it % 2 == 0 }
println(evenNumber) // Output: 42

val oddNumber = number.takeUnless { it % 2 == 0 }
println(oddNumber) // Output: null
```
