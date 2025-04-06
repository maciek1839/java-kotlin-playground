# Effective Kotlin

Based on version published on 2021-10-13

References:
- https://github.com/VitekKlugi/Effective-Kotlin-Examples
- https://kt.academy/book/effectivekotlin

### Part 1: Good code - Chapter 1: Safety

- Item 1: Limit mutability
- Item 2: Minimize the scope of variables
    - Declare variables as close as possible to where they are used.
    - Prefer immutable (val) over mutable (var).
    - Use scoping functions (let, run, apply) to minimize variable scope.
- Item 3: Eliminate platform types as soon as possible
    - Platform type - a type that comes from another language and has unknown nullability.
    - How to Eliminate Platform Types?
        - Explicitly declare nullability:
      ```kotlin
      val safeStr: String = someJavaMethod() ?: "Default Value" // Non-null
      val nullableStr: String? = someJavaMethod() // Nullable
      ```
        - Use @NotNull and @Nullable annotations in Java to help Kotlin infer the correct type.
        - Use safe calls (?.), !!, or requireNotNull() to explicitly handle nullab
- Item 4: Do not expose inferred types inferred type exposition can be really dangerous.
    - Good Practice: Explicitly declare return types for public properties and functions.
        - Always specify the type in your public API to avoid unexpected changes.
    - Bad Practice: Relying on inferred types in public APIs, as they may change unexpectedly.
- Item 5: Specify your expectations on arguments and state
- Item 6: Prefer standard errors to custom ones
    - IllegalArgumentException and IllegalStateException that we throw using require and check.
    - IndexOutOfBoundsException - Indicate that the index parameter value is out of range. Used especially by collections and arrays. It is thrown for instance by ArrayList.get(Int).
    - ConcurrentModificationException - Indicate that concurrent modification is prohibited and yet it has been detected.
    - UnsupportedOperationException - Indicate that the declared method is not supported by the object. Such a situation should be avoided and when a method is not supported, it should not be present in the class.
    - NoSuchElementException - Indicate that the element being requested does not exist. Used for instance when we implement.
- Item 7: Prefer null or Failure result when the lack of result is possible
    - We should prefer returning null or Failure when an error is expected.
        - Result<T> (Result as a sealed class) or Either<T, E>
            - A sealed class in Kotlin is a special kind of class that restricts its inheritance to a fixed set of subclasses. It is often used for representing restricted hierarchies, such as state management or handling result types in a type-safe manner.
        - `getOrNull` - as an example in stdlib
    - Throwing an exception when an error is not expected.
        - The way exceptions propagate is less readable for most programmers and might be easily missed in the code.
        - In Kotlin all exceptions are unchecked. Users are not forced or even encouraged to handle them. They are often not well documented. They are not really visible when we use an API.
        - Because exceptions are designed for exceptional circumstances, there is little incentive for JVM implementers to make them as fast as explicit tests.
        - Placing code inside a try-catch block inhibits certain optimizations that compiler might otherwise perform.
- Item 8: Handle nulls properly
    - `null` means a lack of value.
    - Handling nulls safely
        - safe call
        - smart casting
        - elvis - default, return, throw

  ```kotlin
  printer?.print() // Safe call
  if (printer != null) printer.print() // Smart casting
  
  val printerName1 = printer?.name ?: "Unnamed"
  val printerName2 = printer?.name ?: return
  val printerName3 = printer?.name ?: throw Error("Printer must be named")
  
  val data: String? = null
  val result = requireNotNull(data) { "Data must not be null!" } // Throws IllegalArgumentException
  ```
    - Throw an error
        - Kotlin encourages null safety, but the not-null assertion (`!!`) forces a value to be non-null, which can lead to serious runtime crashes. We should avoid using !! unless absolutely necessary.
        - The `!!` operator blindly assumes a value is non-null, and if it's null, a NullPointerException (NPE) is thrown at runtime.
        - `!!` should be avoided because:
            - It breaks Kotlin’s null safety by allowing NullPointerException.
            - It makes debugging harder since it doesn’t indicate the root cause.
            - It hides potential issues instead of handling them properly.
        - Use safe alternatives like ?., ?:, or explicit null checks to write more robust, crash-free Kotlin code.
    - Avoiding meaningless nullability
        - Classes can provide variants of functions where the result is expected and in which lack of value is considered and nullable result or a sealed result class is returned.
            - In other words, you might have two separate functions to handle different cases (expected vs. nullable), but both are designed to signal the possibility of a result or an absence of a result in a safe and clear way.
  ```kotlin
  fun findUserById(id: String): User? {
      return if (userExists(id)) fetchUserById(id) else null
  }
  
  sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    object NotFound : Result<Nothing>()
    object Error : Result<Nothing>()
  }
  
  fun findUserById(id: String): Result<User> {
    return if (userExists(id)) {
      Result.Success(fetchUserById(id))
    } else {
      Result.NotFound
    }
  }
  ``` 
    - The `lateinit` modifier is used for non-nullable properties that are guaranteed to be initialized before they are accessed. You can use `lateinit` for properties that you are sure will be initialized during the object's lifecycle but not in the constructor.
        - Use case: When you need to initialize a property later, such as when it is set by a dependency injection framework, or when data is fetched asynchronously.
    - Do not return null instead of an empty collection.
    - Nullable enum and None enum value are two different messages.
  ```kotlin
  enum class Status {
      SUCCESS, FAILURE, NONE
  }
  
  fun getStatus(id: Int): Status {
      return if (id > 0) Status.SUCCESS else Status.NONE // No null values
  }
  
  val status: Status = getStatus(-1)
  println(status) // Prints: NONE
  ```

### Chapter 2: Readability

- Item 11: Design for readability
- Programming is mostly about reading, not writing.
- Reduce cognitive load
    - Cognitive load refers to the amount of mental effort required to process and understand information. In programming, it describes how much a developer has to remember, track, and think about while working with code.
- Item 13: Avoid returning or operating on Unit?
    - Unit? has only 2 possible values: Unit or null - isomorphic to Boolean
        - Example: `verifyKey(key) ?: return`
        - It is misleading and confusing. It should nearly always be replaced by Boolean. This is why the general rule is that we should avoid returning or operating on Unit?.
- Item 14: Specify the variable type when it is not clear
    - Kotlin has a great type inference system that allows us to omit types when those are obvious for developers.
    - It improves not only development time, but also readability when a type is clear from the context and additional specification is redundant and messy. However, it should not be overused in cases when a type is not clear
- Item 16: Properties should represent state, not behavior
    - Properties represent accessors, not fields
    - Properties are essentially functions, we can make extension properties as well
    - When we should not use properties:
        - Operation is computationally expensive or has computational complexity higher than O(1)
        - It involves business logic (how the application acts) - when we read code, we do not expect that a property might do anything more than simple actions
        - It is not deterministic - Calling the member twice in succession produces different results.
        - It is a conversion, such as Int.toDouble() - It is a matter of convention that conversions are a method or an extension function. Using a property would seem like referencing some inner part instead of wrapping the whole object.
        - Getters should not change property state - We expect that we can use getters freely without worrying about property state modifications.
    - We should use them only to represent or set state, and no other logic should be involved
  ```kotlin
  var date: Date
    get() = Date(millis)
    set(value) {
    millis = value.time
  }
  ```
- Item 17: Consider naming arguments
    - Consider named arguments in the following cases:
        - When using default parameter values
        - When multiple parameters have the same type, reducing the risk of misordering
        - When dealing with functional-type parameters that are not in the last position
- Item 18: Respect coding conventions
    - https://kotlinlang.org/docs/coding-conventions.html

### Part 2: Code design - Chapter 3: Reusability

- Item 19: Do not repeat knowledge
    - DRY, Single Source of Truth (SSOT)
        - SSOT ensures that a piece of data or logic is stored in only one place and is referenced everywhere it’s needed.
- Everything changes
    - Change is the only constant
- Item 20: Do not repeat common algorithms
    - Learn the standard library
        - Developers frequently reimplement common algorithms and utilities instead of leveraging the standard library, leading to redundant, inefficient, or inconsistent code.
        - Common Reimplemented Functions:
            - String manipulations: split(), trim(), replace()
            - Collections operations: map(), filter(), groupBy(), distinct()
            - Math functions: maxOf(), minOf(), coerceIn()
    - Implementing you own `Utils`
        - Many developers create utility/helper functions, but in Kotlin, extension functions are often a better choice because they allow you to extend existing classes without modifying their source code.
        -  Why Use Extension Functions Instead of Utility Classes?
        - Improves Readability – Makes your code look more like natural method calls.
        - More Kotlin-idiomatic – Avoids static utility classes (which are common in Java).
        - Better Organization – Keeps relevant functionality close to the class it extends.
        - Encapsulation – Provides helper functionality only where needed.
        - When You Should Implement a utility class?
            - Cross-Cutting Concerns
                - When you have functions that don’t logically belong to a specific class.
                - Example: Logging, validation, or configuration helpers.
            - Multiple Extensions on Different Types
                - If you have helper functions for multiple different types, an extension function might not be ideal.
                - Example: A function that operates on both String and Int in the same place.
            - Grouping Similar Functions
                - When you want to organize multiple helper methods together instead of scattering them as extensions.
- Item 21: Use property delegation to extract common property patterns
    - Property delegation in Kotlin allows you to delegate the responsibility of getting and setting a property to another object or function. It enables you to write cleaner and more reusable code, especially when dealing with common patterns like lazy initialization, observable properties, or validation.
    - Why Use Property Delegation?
        -  Avoids Code Duplication – No need to write the same getter/setter logic multiple times.
        - Encapsulates Behavior – Keeps properties clean and maintainable.
        - Lazy Initialization – Improves performance by delaying object creation.
        - Thread Safety – Provides safe access to shared resources (e.g., lazy).
        - Observability – Helps track property changes with Delegates.observable
    - Built-in property delegates
        - Lazy Initialization (`lazy`)
            - The value is computed only once when first accessed.
            - Useful for expensive operations like database queries or network calls.
        - Observable (`Delegates.observable`)
            - Triggers a callback every time the property changes
        - Vetoable (`Delegates.vetoable`)
            - Allows rejecting a value change based on conditions.
    - Custom property delegates
- Item 22 - Use Generics When Implementing Common Algorithms
    - Generics in Kotlin allow us to write flexible and reusable code by defining type parameters. This improves type safety and avoids unnecessary type casting.
- Item 23: Avoid shadowing type parameters
    - Shadowing occurs when a local variable, function parameter, or another type parameter has the same name as a type parameter from an outer scope. This can lead to confusion, reduced readability, and potential errors in your code.
- Item 24: Consider variance for generic
    - Kotlin provides three variance modifiers to manage generics properly:
        - Covariant (out) → Allows reading but not writing.
  ```kotlin
  class Box<out T>(val value: T)
  
  val intBox: Box<Int> = Box(42)
  val numberBox: Box<Number> = intBox // Works: Box<Int> can be treated as Box<Number>
  // Box<out T> means that T can be any subtype of T. 
  // This allows you to treat Box<Int> as Box<Number> because Int is a subtype of Number.
  ```
    - Contravariant (in) → Allows writing but not reading.
  ```kotlin
  class Box<in T> {
      fun add(item: T) { /* Adding items of type T */ }
  }
  
  val box: Box<Number> = Box()
  box.add(42)  // Works: Adding Integer, a subtype of Number
  box.add(3.14)  // Works: Adding Double, another subtype of Number
  // Box<in T> allows the type T to be any supertype of the specified type. 
  // You can add any subtype of Number, but you can't retrieve it as a specific type.
  // You can write to the structure (put elements of type T or its subtypes), 
  // but you can only read as T (or a more general type, such as Any or Object).
  ```
    - Invariant (default behavior) → Neither covariant nor contravariant.
  ```kotlin
  List<Integer> integers = new ArrayList<>();
  integers.add(42);  // Works: Integer is the exact type.
  integers.add(3.14);  // Error: Cannot add Double, the type is fixed as Integer.
  ```
    - Variance modifiers positions
        - declaration-side
            - `fun <T : Number> printNumber(number: T)`
        - use-side
            - `val intBox: Box<Int> = Box(42)` - In the usage-side, you are using the class Box defined earlier.
    - All parameter types in Kotlin function types are contravariant - `in`.
    - All return types in Kotlin function types are covariant - `out`.
    - Java Arrays vs Kotlin Arrays
        - In Java, arrays are covariant.
  ```Java
  class Animal {}
  class Dog extends Animal {}
  
  public class Main {
      public static void main(String[] args) {
          Animal[] animals = new Dog[2];  // ✅ Covariance allowed
          animals[0] = new Dog();         // ✅ Fine
          animals[1] = new Animal();      // ❌ ArrayStoreException at runtime
      }
  }
  ```
    - In Kotlin, arrays are invariant
  ```kotlin
  open class Animal
  class Dog : Animal()
  
  fun main() {
      val dogs: Array<Dog> = arrayOf(Dog(), Dog())
      // val animals: Array<Animal> = dogs  // ❌ Compilation error (invariant)
  }
  ```
    - To safely allow reading elements of an array in a covariant way, Kotlin provides Array<out T>.
  ```kotlin
  fun printAnimals(animals: Array<out Animal>) {  // `out` allows only reading
      for (animal in animals) {
          println(animal)
      }
  }
  
  fun main() {
      val dogs: Array<Dog> = arrayOf(Dog(), Dog())
      printAnimals(dogs)  // ✅ Works fine
  }
  ```

| Feature                             | **Java (`T[]`)**              | **Kotlin (`Array<T>`)**      |
|-------------------------------------|-------------------------------|------------------------------|
| **Variance**                        | **Covariant** ✅ (Unsafe)      | **Invariant** ❌ (Safe)       |
| **Allows `Animal[] = Dog[]`?**      | ✅ Yes (but runtime exception) | ❌ No (compile-time error)    |
| **Ensures Type Safety?**            | ❌ No                          | ✅ Yes                        |
| **Safe Covariant Alternative?**     | ❌ None                        | ✅ `Array<out T>` (read-only) |
| **Safe Contravariant Alternative?** | ❌ None                        | ✅ `Array<in T>` (write-only) |

- Item 25: Reuse between different platforms by extracting common modules
    - Using Kotlin mulitplatform capabilities, we can implement business logic only once and reuse it between platforms

### Chapter 4: Abstraction design

In programming we do abstraction and composition.
- We use abstractions mainly to:
    - Hide complexity
        - By providing simpler, clearer interfaces, abstraction allows developers to focus on high-level structures while hiding the underlying details.
    - Organize our code
        - Abstraction helps to manage complexity by providing simpler and clearer interfaces, while hiding unnecessary details. By focusing on the high-level structure and ignoring unnecessary details, developers can create cleaner, more organized code.
    - Give creators the freedom to change.
        - Abstraction gives developers the freedom to change or replace underlying implementations without affecting the rest of the codebase, promoting code maintainability and extensibility.

Composition complements abstraction:
- By combining simple components (often abstracted away) to form complex structures, composition leads to reusable and scalable software designs.

---

- Item 26: Each function should be written in terms of a single level of abstraction
    - The Single Level of Abstraction Principle (SLAP) states that a function, method, or module should operate at only one level of abstraction—either high-level (what is being done) or low-level (how it is being done), but not both.
    - Why Follow SLAP?
        - Improves readability – makes it easier to understand the purpose of a function.
        - Enhances maintainability – changes in low-level details don't affect high-level logic.
        - Encourages reusability – well-structured functions can be reused in different contexts.
    - Functions should be small and have a minimal number of responsibilities. If one of them is more
      complex, we should extract intermediary abstractions.
- Item 27: Use abstraction to protect code against changes
    - Different kinds of abstractions give us freedom by protecting us from a variety of changes.
    - Types of Abstractions:
        - Constants
            - Use meaningful names for constant properties to improve readability and maintainability.
        - Functions
            - Encapsulate logic to improve reusability and clarity.
            - Extension functions can enhance existing types without modifying them.
        - Classes
            - Provide more powerful abstractions by encapsulating state and exposing related behavior.
            - Can have multiple functions but may introduce strong coupling if not designed carefully.
        - Interfaces
            - Decouple implementations, making it easier to swap components.
            - Modifying an interface requires updating all implementations, so changes should be considered carefully.
    - Finding the Right Balance:
        - Over-abstraction can lead to unnecessary complexity.
        - Under-abstraction can result in rigid, hard-to-maintain code.
        - A real-world anti-pattern: [FizzBuzz](https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition)
- Item 28: Specify API stability
    - Frequent or poorly managed changes in an API can cause significant issues for users. To maintain a stable and predictable experience, it's essential to establish clear communication between API creators and consumers.
    - Best Practices for API Stability:
        - Minimize breaking changes to avoid disrupting users.
        - Communicate changes effectively through:
            - Versioning – Clearly define and follow semantic versioning.
            - Documentation – Provide detailed explanations of updates, deprecations, and migration guides.
            - Annotations – Use annotations to mark experimental, deprecated, or evolving features. (Example: Annotations.kt)
- Item 29: Consider wrapping external API
    - Wrapping external APIs in an internal abstraction layer is a widely used technique for improving maintainability and stability.
    - Advantages of Wrapping External APIs:
        - Protects against API changes - Only the wrapper needs updating if the external API changes.
        - Aligns with project conventions – You can customize the API to fit your project's style and logic.
        - Eases future replacement – If the external library becomes problematic, swapping it out is much easier.
        - Enables controlled behavior modifications – You can tweak how the API behaves to fit your needs.
    - Design patterns
        - The pattern used to hide an external or legacy system behind an internal interface is called the Facade Pattern. This pattern provides a simplified, high-level interface to complex or unstable subsystems, improving modularity and maintainability.
        - The Anti-Corruption Layer (ACL) is another architectural pattern designed to decouple and protect your system from changes or inconsistencies in an external API or legacy system.
        - If the goal is merely to simplify an external API, the Facade Pattern is a good fit. However, if you want to fully isolate your domain from external influences and prevent dependency leaks, then the Anti-Corruption Layer is the best approach.

| Pattern                     | Purpose                                                                 | When to Use |
|-----------------------------|-------------------------------------------------------------------------|-------------|
| **Facade Pattern**          | Simplifies a complex subsystem by providing a unified, high-level interface. | When you want to make an external API easier to use within your application. |
| **Anti-Corruption Layer**   | Translates and isolates an external system to prevent it from "corrupting" your internal domain model. | When integrating with a third-party API or legacy system that has a different data model or business

- Item 30: Minimize elements visibility
    - Elements visibility should be as restrictive as possible.
- Item 31: Define contract with documentation
    - When defining an element, especially in an external API, it’s crucial to establish a clear contract.
    - We do that through names, documentation, comments, and types.
    - [KDoc format](https://kotlinlang.org/docs/reference/kotlin-doc.html)
        - Uses JavaDoc-style block tags, extended for Kotlin, and supports Markdown for inline markup.
    - [Dokka](https://github.com/Kotlin/dokka)
        - Kotlin’s official documentation tool for generating API docs
    - Type hierarchy is an important source of information about an object
    - Liskov Substitution Principle (LSP):
        - If a class sets an expectation, all its subclasses must adhere to it.
        - Violating LSP breaks the contract, leading to unpredictable behavior.
- Item 32: Respect abstraction contracts
    - If breaking a contract is unavoidable, document it thoroughly to ensure clarity for future maintainers.
    - Technical workarounds exist (e.g., reflection), but bypassing contracts leads to fragile code.

### Chapter 5: Object creation

- Item 33: Consider factory functions instead of constructors
    - **Factory functions are not a replacement for primary constructors but an alternative to secondary constructors.**
    - Factory functions provide an alternative to constructors with several advantages:
        - Named Functions: Unlike constructors, functions can have meaningful names, making object creation more readable.
        - Polymorphic Return Type: Factory functions can return any subtype of their declared return type.
        - Instance Control: They are not required to create a new object every time.
            - Example 1: Caching with Factory Functions
            - Example 2: Singleton Pattern
            - Example 3: Flyweight Pattern (Reusing Expensive Objects)
        - Lazy Object Creation: Useful for libraries that rely on annotation processing.
        - Visibility Control: A factory function can be restricted to a specific module or file.
        - Reified Generics: Factory functions can be inline, allowing reified type parameters.
        - Encapsulated Construction Logic: Factory functions simplify object creation when constructors require complex setup.
        - Constructor Constraints: A constructor must call a superclass or primary constructor immediately.
- Item 34: Consider a primary constructor with named optional arguments
    - Kotlin provides more concise and expressive ways to create objects compared to Java's traditional patterns like telescoping constructors and builder pattern.
    - Java developers mainly use the builder pattern, because it allows them to:
        - name parameters
        - specify parameters in any order
        - have default values
    - In most cases, Kotlin does not need the Builder Pattern due to default arguments and DSLs.
        - One feature that sets Kotlin apart from other programming languages is its ability to create Domain-Specific Languages (DSLs) quickly and easily. Kotlin DSLs are a way to create a language specific to a particular problem domain, allowing developers to write code more naturally and intuitively.
    - However, Builders may still be useful for cross-language compatibility and complex object creation.
- Item 35: Consider defining a DSL for complex object creation
    - Kotlin DSL (Domain-Specific Language) is a powerful feature for defining complex objects or hierarchical structures in a more readable and intuitive way. It simplifies configurations, eliminates boilerplate, and provides type safety—unlike Groovy.
    - DSLs are commonly used in various domains:
        - HTML generation: Classic HTML or React-like DSL.
        - API definitions: Ktor’s routing and request handling.
        - Gradle configuration: build.gradle.kts instead of Groovy.
    - When to Use DSLs?
        - When dealing with complex, hierarchical data structures.
        - When eliminating boilerplate significantly improves readability.
        - When an expressive API is needed for configuration-based workflows.
    - When to Avoid DSLs?
        - If a simpler feature like builders or constructors would suffice.
        - If the DSL is too hard for new developers to understand.
        - If defining a DSL adds unnecessary complexity.

### Chapter 6: Class design

- Item 36: Prefer composition over inheritance
    - Inheritance is meant to establish a clear `is-a` relationship between objects.
        - If this relationship is unclear, using inheritance can lead to design issues and unexpected behavior.
        - To prevent unintended inheritance, Kotlin classes are final by default.
            - A class must be explicitly marked as open to allow inheritance.
        - Problems with Inheritance:
            - Single inheritance limitation: In Kotlin, classes can only inherit from one class. This limits flexibility compared to composition, where you can combine many components.
            - Complex hierarchies: Inheritance often leads to deep, complex hierarchies, making code harder to maintain and understand.
            - Unwanted behavior: When we extend a class, we inherit all of its behaviors and methods, many of which may not be necessary or relevant. This violates the Interface Segregation Principle.
            - Encapsulation issues: Inheritance can break encapsulation since the child class has to understand and rely on the superclass’ implementation.
                - Inheritance breaks encapsulation because it exposes internal details of the superclass to its subclasses, creating a tight coupling between them. This leads to several issues:
            - Polymorphism constraints: While inheritance provides strong polymorphic behavior, it’s often inflexible. Modifying a superclass affects all subclasses, which can be undesirable.
    - If the goal is simply code reuse or extraction,
        - Composition is a more flexible and maintainable alternative to inheritance.
        - Instead of inheriting, we can compose our class by using delegation or by incorporating multiple interfaces. Kotlin’s `interface delegation` allows for forwarding methods and behaviors from one object to another.
        - Benefits of Composition:
            - Flexibility: You can compose multiple classes or interfaces, allowing you to select only the relevant functionality.
            - Better encapsulation: By composing objects, you don’t expose internal details that aren’t needed.
            - Behavior change: If a class we composed changes, our behavior will only change if the contract changes.
        - Downsides:
            - More explicit: Composition is explicit, meaning you have to interact directly with the composed objects. It requires more upfront design.
- Item 37: Use the data modifier to represent a bundle of data
    - When a class is marked as data, Kotlin automatically generates:
        - toString() → Provides a readable string representation.
        - equals() and hashCode() → Ensures proper comparison and hashing.
        - copy() → Allows creating modified copies of immutable objects.
            - Performs a shallow copy (only references are copied, not deep object structures).
            - Ideal for immutable objects since they don’t require deep copies.
        - componentN() (e.g., component1(), component2())
            - Enables position-based destructuring, which can be useful but also dangerous:
                - 🔴 Risky: Changing the order of properties in a data class breaks destructuring assignments.
                - 🔴 Easy to misuse: If you accidentally swap positions, your destructured variables may hold incorrect values.
                - ⚠️ Bad practice: Avoid destructuring just to extract a single value, especially inside lambda expressions.
                    - ```val (name, _) = user  // Instead, prefer: val name = user.name```
    - ✅ Prefer Data Classes Over Tuples
        - Kotlin's built-in tuples (e.g., Pair, Triple) are just generic data classes with:
            - Serialization support
            - Custom toString()
    - However, data classes are almost always a better choice because:
        - They provide meaningful property names instead of first, second, etc.
        - They improve code readability and self-documentation.
    - When to Use Tuples (Pair, Triple)?
        - Acceptable Uses:
            - ✔️ For short-lived, local aggregations (e.g., returning two values from a function).
            - ✔️ When the aggregate structure is unknown in advance (e.g., working with standard library functions).
        - Avoid Tuples When:
            - ❌ The values represent a well-defined entity → Use a data class instead.
    - Summary: Why Prefer Data Classes?
        - Auto-generates common methods, reducing boilerplate.
        - More readable than Pair or Triple.
        - Self-documenting: Named properties clarify intent.
        - Safer destructuring: Less prone to errors compared to componentN().
        - ⚠️ Use tuples only for temporary aggregations.
- Item 38: Use function types instead of interfaces to pass operations and actions
    - Before Kotlin 1.4: Function types were the recommended way to pass actions.
  ```kotlin
  fun setOnClickListener(listener: (View) -> Unit) {
      // Call the function when needed
  }
  ```
    - Advantages:
        - More concise than creating an interface.
        - No need to define extra classes.
        - Works seamlessly with higher-order functions.
    - 📌 Exception: If the class is intended for Java interoperability, a SAM interface may be better.
    - Kotlin 1.4+: SAM Interfaces Supported in Kotlin
        - Since Kotlin 1.4, you can use the fun interface keyword to define SAM interfaces natively.
        - A SAM interface is an interface with exactly one abstract method, allowing it to be used as a functional interface.
        - A functional interface is an interface that has exactly one abstract method (SAM - Single Abstract Method). It allows the interface to be used in lambda expressions and functional programming paradigms.
  ```kotlin
  fun interface ClickListener {
      fun onClick(view: View)
  }
  ```
    - Improvements Over Regular Interfaces:
        - Allows Java-like functional interface usage.
        - Can have non-abstract members.
        - Can extend other interfaces.

  ```kotlin
  val listener = ClickListener { view -> println("Clicked: $view") }
  listener.onClick(View())
  ```
    - Summary
        - Function types are the best choice for passing operations inside Kotlin code.
        - SAM interfaces are useful when working with Java or needing to extend other interfaces.
        - Since Kotlin 1.4, fun interface makes SAMs more convenient, but function types remain the default choice.

| Feature                 | Function Type (`(T) -> R`) | SAM Interface (`fun interface`) |
|-------------------------|--------------------------|---------------------------------|
| **Syntax**             | `(T) -> R`               | `fun interface MyInterface { fun method() }` |
| **Extending Another Interface?** | ❌ No  | ✅ Yes |
| **Multiple Methods Allowed?** | ❌ No (Only a single function) | ✅ Yes (Only one abstract method, but multiple default methods) |
| **Java Interoperability** | ❌ No | ✅ Yes |
| **Recommended Use Case** | Simple lambdas & callbacks | Event listeners & Java interop |

- Item 39: Use sealed classes and interfaces to express restricted hierarchies
    - Sealed classes and sealed interfaces are powerful tools for modeling restricted hierarchies, where the set of possible subclasses (or implementations) is fixed and known at compile time.
    - A `sealed class` is a class that can only be subclassed within the same file. It restricts the set of subclasses that can extend the class, providing better control over inheritance and making it easier to reason about the class hierarchy.
    - Similarly, a `sealed interface` restricts the implementations of the interface to within the same file. This is useful for situations where you want to limit the implementations of an interface, ensuring they come from a specific set of options.
    - Both `sealed classes` and `sealed interfaces` are typically used when you have a closed set of types (e.g., specific states, commands, or categories) that should not be extended or modified outside the defined hierarchy.
    - Benefits of Sealed Classes and Interfaces
        - Type Safety: Since all subclasses of a sealed class or interface must be defined in the same file, the compiler can check that all possible cases are covered, which helps avoid errors at runtime.
        - Exhaustiveness Checking: Kotlin’s when expressions can verify that all possible cases are handled in sealed hierarchies. This reduces the likelihood of missing cases when adding new subclasses or implementing new behaviors.
        - Maintainability: By restricting the set of subclasses, sealed classes and interfaces make it easier to maintain the code, as you can always see all the possible types in the hierarchy in one place.
    - When to use a sealed class?
        - You need a common base class
        - You need to add methods or properties
        - You want to model a hierarchy of types
    - When to use a sealed interface?
        - You don't need common behavior.
        - You want to allow different implementations.
        - You want to define a closed interface hierarchy.
        - You need to implement multiple interfaces.

| Feature                  | Sealed Class                             | Sealed Interface                        |
|--------------------------|------------------------------------------|-----------------------------------------|
| **Common Behavior**       | Can provide common behavior and properties to subclasses. | No common behavior, only defines a restricted set of implementations. |
| **Inheritance**           | Subclasses inherit behavior from the sealed class. | Implementations do not inherit behavior. |
| **Multiple Implementations** | Cannot implement multiple sealed classes (single inheritance). | Can implement multiple interfaces, offering greater flexibility. |
| **Use Case**              | When you need a closed hierarchy with shared behavior. | When you need a restricted set of implementations without shared behavior. |
| **Properties/Methods**    | Can hold state and methods common to all subclasses. | Cannot hold state, only defines contract. |

- Item 40: Prefer class hierarchies to tagged classes
    - You can model restricted class hierarchies in Kotlin in two primary ways: using Tagged Classes or Sealed Classes. Both approaches allow you to control the behavior and structure of your objects, but they do so in different ways, with distinct trade-offs.
    - Tagged Classes – Classes that use a constant mode field to determine their behavior. While this approach works, it is often less safe and harder to maintain than using sealed hierarchies.
        - Better Alternative: Use sealed classes or interfaces to enforce a well-defined set of possible states or behaviors.
        - Problems with this approach:
            - Uses a `mode field`/`enum`, making it prone to errors if new `modes` are added.
            - No compile-time enforcement of valid states.
            - More difficult to maintain and extend.
            - Violates OOP principles: Behaviors should belong to their respective types, not a single class.
    - A Sealed Class provides a more type-safe and extensible solution for defining restricted class hierarchies. With sealed classes, you define a closed set of subclasses, and the compiler ensures that all subclasses are accounted for in when statements or pattern matching. This approach is more robust and scalable, especially for complex hierarchies, and eliminates the need for an external mode or type discriminator.
        - Compile-time safety: The compiler enforces all cases in when.
        - Encapsulation: Each class holds only the relevant properties.
        - Scalability: Adding a new shape is easier and doesn't require modifying existing logic.
    - The State Pattern is a design pattern that allows an object to change its behavior when its internal state changes.
        - While both approaches are valid for implementing the State Pattern, sealed classes generally offer a cleaner and safer way to express restricted hierarchies and handle state transitions.
- Item 41: Use enum to represent a list of values
    - Use Enums when you have a fixed set of values that are logically related and need to:
        - Improve type safety
        - Provide better readability
        - Represent states, categories, or strategies
        - Attach behavior (methods) to values
        - Implement restricted hierarchies or finite state machines
    - When Not to Use Enums:
        - While enums are powerful, they are not always the right solution for every problem. If you need to represent a more dynamic set of values that can change over time (like user-defined data), enums may not be appropriate.
        - If the set of possible values is not fixed or could change based on external factors, consider alternatives like data classes, lists, or other structures.
        - Example: If your application needs to support dynamic feature toggles or settings that can be configured at runtime, a data class or a configuration object might be more appropriate than an enum.
    - `enum` classes automatically implement the Comparable interface, toString, hashCode and equals. This means that enum constants are comparable and can be compared using comparison operators (<, >, <=, >=, etc.) or explicitly through the compareTo() method.
    - Their serialization and deserialization are simple (they are represented just by name), efficient and automatically supported by most libraries for serialization (like Moshi,
      Gson, Jackson, Kotlinx Serialization, etc).
    - The comparison between enum constants is based on their ordinal value, which represents their position in the enum declaration. The first enum constant has the ordinal value of 0, the second one 1, and so on.
    - Enum or sealed class?
        - Use Enum when you have a fixed set of predefined values (e.g., payment methods like cash, card, transfer) and you don't need subclassing or different behaviors per value.
        - Use Sealed Class when you need a flexible set of related types that may have different properties or behaviors, and you want to restrict the hierarchy (e.g., representing different states, errors, or actions with associated data).
- Item 42: Respect the contract of equals
    - In Kotlin, equality can be classified into two types:
        - Structural equality: This is checked by the equals() method or the == operator.
        - Referential equality: This is checked by the === operator.
    - Do We Need to Implement equals() Ourselves?
        - In Kotlin, we rarely need to implement equals() ourselves because it’s automatically provided in the following cases:
            - Default equality: The equals() method is implemented in the Any class, which all Kotlin classes inherit from.
            - Data classes: Kotlin automatically generates an equals() method for data classes based on the properties in the primary constructor.
        - However, there are scenarios where we may need to implement equals() manually:
            - Custom equality logic: When we need the logic to differ from the default implementation (e.g., comparing specific properties or ignoring others).
            - Subset of properties: When we only need to compare a subset of properties rather than all properties of the object.
            - Non-data classes: When we don’t want our class to be a data class, or when the properties we want to compare aren’t in the primary constructor.
    - Contract of equals()
        - In order to ensure the correct behavior of the equals() method, it must adhere to the following contract, which is the same as in Java:
            - Reflexive: For any non-null value x, x.equals(x) should return true. In other words, an object must be equal to itself.
            - Symmetric: For any non-null values x and y, if x.equals(y) returns true, then y.equals(x) must also return true.
            - Transitive: For any non-null values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should also return true.
            - Consistent: For any non-null values x and y, multiple invocations of x.equals(y) should consistently return either true or false, provided the objects haven’t been modified in ways that affect equality.
            - Never equal to null: For any non-null value x, x.equals(null) should always return false.
- Item 43: Respect the contract of hashCode
    - In Kotlin, we typically define hashCode **only** when we override equals. A well-implemented hashCode function must adhere to the formal contract and use a good hashing strategy to ensure efficient performance in hash-based collections.
    - Consistency:
        - The hashCode method must return the same integer when called multiple times on the same object, as long as no properties affecting equality are modified.
    - Consistency with equals:
        - If a.equals(b) == true, then a.hashCode() must be equal to b.hashCode().
- Item 44: Respect the contract of compareTo
    - The `compareTo` method in Kotlin follows the contract of `Comparable`, ensuring consistent and predictable ordering.
    - The Contract of compareTo
        - When implementing compareTo, we must follow these rules:
            - **Consistent with equals**: If a == b, then a.compareTo(b) == 0.
            - **Transitive**: If a > b and b > c, then a > c.
            - **Symmetric**: If a < b, then b > a.
            - **Reflexive**: Any object must be equal to itself (a.compareTo(a) == 0).
- Item 45: Consider extracting non-essential parts of your API into extensions
    - In Kotlin, extension functions allow us to add new functionalities to existing classes without modifying their source code. By moving non-core functionalities into extensions, we:
        - Keep the core API clean and focused
        - Allow optional functionalities without bloating the class
        - Improve readability and maintainability
    - When Should You Use Extensions?
        - If the function is useful but not essential to the class
        - If modifying the original class is not possible (e.g., library classes)
        - If the function makes sense in a specific context (e.g., UI-related helpers)
    - Summary
        - Keep the core API focused on essential logic
        - Use extensions for non-essential functionalities
        - Make third-party or library classes more useful
        - Use extension properties to define computed values
        - Rule of Thumb: If a method isn't crucial to an object's behavior, it should be an extension, not a member function.

| Feature | Member Function | Extension Function |
|---------|----------------|--------------------|
| **Definition** | Defined inside the class | Defined outside the class using `fun ClassName.functionName()` |
| **Import Requirement** | Always available in the class | Needs to be imported if in a different package |
| **Overriding Priority** | Always takes precedence over extensions | Called only if no matching member function exists |
| **Exception** | N/A | If an extension in the Kotlin stdlib has `@kotlin.internal.HidesMembers`, it can override a member function |
| **Virtual Behavior** | Can be overridden in subclasses (participates in polymorphism) | **Not virtual** – Resolved at compile time, does not participate in polymorphism |
| **Usage in Inheritance** | Works well with inheritance | Should not be used for elements designed for inheritance |
| **Resolution Mechanism** | Resolved dynamically at runtime | **Resolved statically** at compile time |
| **Nullable and Generic Types** | Works on all instances of the class | Can be defined for nullable types or specific generic substitutions |
| **Class Reference** | Listed as a member in the class reference | **Not listed** as a member in the class reference |


```kotlin
open class Animal
class Dog : Animal()

fun Animal.speak() = "Animal sound"
fun Dog.speak() = "Bark"

fun main() {
    val myDog: Animal = Dog()
    println(myDog.speak()) // Output: "Animal sound", NOT "Bark"
}
```

In Kotlin, `Not virtual` means that extension functions are resolved at compile time based on the declared type of the variable, rather than the runtime type of the object. Unlike member functions, extension functions do not participate in polymorphism and cannot be overridden in subclasses.

Virtual functions are resolved at runtime, meaning the actual function that gets called depends on the runtime type of the object, not just the declared type. This is the core concept of polymorphism in object-oriented programming.

Key takeaways:
- Member functions take precedence over extension functions.
- Extension functions are statically resolved at compile time.
- Extensions do not override member functions and are not virtual.
- Extensions can be defined for nullable types or specific generic types.
- Use member functions for core functionality and extensions for API enhancements.

---

- Item 46: Avoid member extensions
    - While Kotlin allows the creation of member extensions, it is generally advisable to avoid them in most cases, with DSLs being one of the rare exceptions.
    - Reasons to Avoid Member Extensions
        - Restricting Visibility Is Not Enough
            - It is tempting to define member extensions just to limit their visibility within a specific scope. However, doing so can make the code less clear and harder to maintain.
        - Reference to the Receiver is Not Supported
            - When you define a member extension, the receiver class becomes part of the extension function, but the extension itself is not directly accessible by a reference. This can lead to confusion about where the extension function belongs.
        - Ambiguity Between Receivers
            - When using member extensions, the distinction between the extension receiver (the class the extension is added to) and the dispatch receiver (the object you are operating on) can become unclear.
            - This might make it confusing whether the modification is being done on the receiver or on the class where the extension function is defined.
        - Potential Confusion in Code Behavior
            - It’s often unclear whether an operation is performed on the extension function or the dispatch receiver. This ambiguity can lead to hard-to-debug issues, making the codebase less predictable and harder to maintain.

### Part 3: Efficiency - Chapter 7: Make it cheap

- Item 47: Avoid unnecessary object creation
    - The cost of wrapping primitives in objects
        - Memory overhead
            - Objects require extra space due to headers and references.
            - Example (64-bit JVM, -Xmx32G):
                - Object header: 16 bytes
                - Reference to the object: 8 bytes
                - Primitive int fits in just 4 bytes
        - Access overhead
            - Encapsulation adds an extra function call when accessing values inside objects.
        - Object creation overhead
            - Objects must be allocated and garbage-collected, adding runtime cost.
    - Optimizing object usage
        - Limit unnecessary objects
            - Use object declarations (singletons) instead of repeatedly creating instances.
        - Implement object caching in factory functions.
            - Use weak references to avoid preventing garbage collection.
            - Use soft references to keep values until memory is needed (ideal for caching).
        - Lift heavy objects to outer scope
            - Move heavy objects to outer scopes to avoid frequent re-creation.
        - Use lazy initialization
            - Delay object creation until first access using by lazy.
            - Helps distribute the cost of object creation over time.
        - Prefer primitives when possible
            - Kotlin automatically optimizes primitives on the JVM, but:
                - Boxing occurs when using nullable types (Int? → Integer).
                - Generics require boxed types (List<Int> uses Integer under the hood).
            - Only optimize primitives in performance-critical code—otherwise, prefer readability.
            - Int (Primitive) uses 4 bytes (32 bits) in memory.
            - Integer (Boxed) uses 16 bytes (on 64-bit JVM with compressed OOPs) due to object overhead:
                - 12-byte object header
                - 4-byte int value
    - Summary
        - Objects introduce memory, access, and creation overhead.
        - Use singletons, caching, lazy initialization, and lifting heavy objects to optimize performance.
        - Prefer primitives when performance matters but balance it with code readability.
- Item 48: Use inline modifier for functions with parameters of functional types
    - What this inline modifier does is that during compilation, all uses of this function are replaced with its body.
        - Functions with functional parameters are faster when they are inlined.
        - Inline functions cannot be recursive.
    - Higher-order functions (functions that take other functions as parameters) introduce some performance overhead because they involve object creation and function calls. The inline modifier helps mitigate this overhead by inlining the function body directly at the call site. This results in better performance, especially in scenarios with functional parameters.
    - Why Use inline?
        - When a function takes a functional parameter (such as a lambda), Kotlin usually compiles it into an anonymous class or a function object. This leads to:
            - Object allocation: An instance of the functional interface is created.
            - Additional function calls: Calling the lambda involves invoking invoke() on the function object.
        - By marking the function as inline, we tell the compiler to replace function calls with the actual function body at the call site. This eliminates:
            - Object creation
            - Extra function calls
            - Performance overhead
    - When to Use inline?
        - A function takes lambda parameters and is called frequently.
        - Avoiding unnecessary object creation improves performance.
        - The function is small, and inlining reduces function call overhead.
    - Avoid inline when:
        - The function is large (inlining large functions increases bytecode size).
            - inline functions in Kotlin can increase code size, which may impact performance under certain conditions.
                - Every time an inline function is called, its body is copied into the calling function.
                - If an inline function is large or used in many places, the bytecode grows, leading to larger APK/JAR files.
            - Inlining large functions can slow compilation
                - Since the compiler copies the function code everywhere it’s used, it needs to process more code, increasing compilation time.
        - The function is rarely called (inlining offers no benefit).
        - The function does not take lambdas (inlining is useless in this case).
        - The function is a part of a public API (inlining exposes implementation details).
    - Non-Local Returns in inline Functions
        - Because the function is inlined, `return` exits the entire calling function.
    - If a function has multiple lambda parameters, but you want to inline only some of them, you can use `noinline`.
    - Lambdas passed to inline functions can use non-local returns. However, sometimes you might want to restrict this behavior. `crossinline` prevents the lambda from using return.
- Item 49: Consider using inline value classes
    - An inline class is a special Kotlin feature that allows wrapping a single value inside a class without introducing additional runtime overhead. The Kotlin compiler replaces the inline class with its underlying value during compilation, avoiding unnecessary object allocations.
    - Inline classes can implement interfaces, but when referenced as an interface, they will no longer be inlined.
    - Advantages of Inline Classes
        - No Additional Memory Overhead
            - Unlike traditional wrapper classes, inline classes do not create extra objects at runtime.
        - Stronger Type Safety
            - Helps prevent accidental misuse of primitive types.
        - Better Performance
            - Reduces memory allocations, which leads to faster execution.
    - Use Cases for Inline Classes
        - Using Inline Classes to Represent Units of Measure
        - Preventing Type Misuse (Domain-Driven Design)
            - When using primitive types (like String or Int), it's easy to mix up values. Inline classes provide type safety to prevent errors.
    - Kotlin also provides type aliases, which are just alternative names for existing types but do not enforce type safety.
    - ✅ Use Inline Classes When:
        - Wrapping a single primitive value (to improve type safety).
        - Avoiding performance overhead of wrapper objects.
        - Preventing misuse of primitive types in function parameters.
    - ❌ Avoid Inline Classes When:
        - You need a class with multiple properties.
        - You require runtime reflection (inline classes may not work well).
        - You need to store the class in collections (because they become boxed).
    - Inline classes are a great tool for improving type safety while keeping performance optimal.
    - Summary
        - Use value class (inline class) when you need type safety and performance.
        - Use typealias when you need better readability and shorter type names.
- Item 50: Eliminate obsolete object references
    - Managing memory efficiently is crucial in Kotlin, especially when dealing with long-lived objects. Keeping unnecessary object references can lead to memory leaks and increased garbage collection pressure.
    - Common Sources of Memory Leaks
        - Variables Persisting Beyond Their Needed Scope
            - Keeping large objects in top-level properties or object declarations (including companion objects) can lead to memory waste.
            - Solution: Prefer local variables whenever possible to ensure automatic garbage collection.
  ```kotlin
  // ❌ Bad: Holding a large object in a top-level property
  object DataHolder {
      val heavyData = generateLargeDataset() // Stays in memory indefinitely
  }
  
  // ✅ Better: Keep it within a function scope
  fun processHeavyData() {
      val heavyData = generateLargeDataset()
      // Use heavyData within this scope only
  }
  ```
    - Holding Objects in Caches that Might Never Be Used
        - Caches can lead to memory leaks if they store unused objects indefinitely.
        - Solution: Use soft references to allow the garbage collector to reclaim memory when needed.
  ```kotlin
  import java.lang.ref.SoftReference
  
  class ImageCache {
      private val cache = mutableMapOf<String, SoftReference<ByteArray>>()
  
      fun putImage(key: String, data: ByteArray) {
          cache[key] = SoftReference(data)
      }
  
      fun getImage(key: String): ByteArray? {
          return cache[key]?.get() // Returns null if GC has collected it
      }
  }
  ```
    - Weak References for Non-Essential Objects
        - Some objects should not prevent garbage collection but still be accessible when available.
        - You need to hold a reference to an object without preventing it from being garbage collected, such as caching UI components that can be recreated when needed.
    - Explicitly Releasing Unused Objects
        - While manual memory management is rare in Kotlin, explicitly nullifying references can help in some cases.
        - This is especially useful in long-lived objects holding references to short-lived objects.
  ```kotlin
  class DataProcessor {
      private var temporaryData: ByteArray? = loadData()
  
      fun process() {
          // Process data
          temporaryData = null // Free memory explicitly
      }
  }
  ```

| Feature                   | Weak Reference                          | Soft Reference                             |
|---------------------------|-----------------------------------------|--------------------------------------------|
| **Garbage Collection**     | Eligible for garbage collection as soon as no strong references exist. | Eligible for garbage collection when the JVM needs memory. |
| **Use Case**               | Typically used for caches, listeners, and references to objects that are not essential to the program’s execution. | Used for caches where the object is only kept in memory if there is available memory. |
| **Memory Management**      | The object can be collected immediately once it's no longer reachable. | The object is collected only when memory is running low. |
| **JVM Behavior**           | Collected immediately during GC. | Only collected when the JVM is under memory pressure. |
| **Example**                | Cache of UI elements that can be recreated. | Cache of images or data in an app, retained as long as memory allows. |
| **Performance Impact**     | Less impact on memory; more frequent GC collections. | May allow longer retention of objects but can cause memory pressure. |

### Chapter 8: Efficient collection processing

- Item 51: Prefer Sequence for big collections with more than one processing step
    - Key Differences Between Iterable and Sequence
        - Processing Order:
            - Iterable: In this processing model, the operations are applied eagerly in a step-by-step manner. That means, for each operation, you will apply it to the entire collection before moving to the next operation. The collection is evaluated fully at each step.
            - Sequence: In contrast, sequences are lazy. They process elements element-by-element, applying operations one at a time, which means it does not evaluate the whole collection upfront. Instead, it processes elements as needed, keeping operations to a minimum.
        - Laziness:
            - Sequence: They are lazy, which means they defer computation until the elements are actually needed. This is useful when working with larger collections as it minimizes overhead and allows you to do operations only on the required data, without creating intermediate collections.
            - Iterable: In an Iterable, on the other hand, you would typically create a new collection at every operation, which can lead to higher memory consumption, especially with large datasets.
        - Natural Order of Operations:
            - Sequence: The operations are performed in the natural order, allowing you to chain them and ensure they apply incrementally. For example, elements can be processed one at a time, and each operation occurs only when necessary.
            - Iterable: Processing is generally eager: the collection is processed as a whole, applying each operation step-by-step to the entire collection.
        - Efficiency:
            - Sequence: They perform a minimal number of operations because operations are applied lazily. This means unnecessary steps are avoided, reducing time and resource consumption.
            - Iterable: Each operation in an Iterable generally leads to the creation of a new collection, making it less efficient when working with larger data sets.
        - Infinite Sequences:
            - Sequence: Sequences can represent infinite collections, which means you can work with unbounded data structures (e.g., infinite ranges or streams) and avoid running into memory limits.
            - Iterable: Iterables are not designed to handle infinite data streams in the same way because they must create the entire collection upfront, leading to potential memory issues.
    - For collections that are large or where you plan to perform multiple transformations, sequences are typically the better choice:
        - Lazy evaluation allows you to process one element at a time, without needing to load everything into memory at once.
        - Multiple processing steps: If you're chaining several operations together, such as map, filter, and reduce, a Sequence will allow you to perform these operations in a memory-efficient, incremental manner, as opposed to eagerly creating intermediate collections with Iterable.
    - When Iterable is More Efficient (or Necessary)
        - Sorting: If the collection needs to be sorted, Iterable might perform better because the entire collection needs to be considered for sorting. Since Sequence is lazy, it would first need to be materialized into a list before applying a sort operation, which could lead to inefficiencies.
    - Java 8 Streams vs Kotlin Sequences
        - Java 8 Streams: They offer a parallel mode which might give performance improvements for certain cases. However, there are known pitfalls associated with parallel streams, such as:
            - Potential issues with thread safety
                - Mutable Shared State: If you have a stream that operates on a mutable shared state, you may run into race conditions when parallel threads try to modify the state simultaneously. For example, if you use a Collector to accumulate results (like summing values, building a list, etc.), and the collector is not thread-safe, the result could be corrupted or inconsistent.
                - Concurrent Modification: If the underlying data structure is modified while being processed in parallel, this can lead to exceptions like ConcurrentModificationException or unexpected results. For example, trying to modify a list while a parallel stream is processing it might lead to unpredictable behavior.
                - Avoiding Thread Safety Issues:
                    - Use thread-safe data structures (like ConcurrentHashMap, AtomicInteger) or make sure that your operations on mutable data are safe.
                    - If you're using a custom Collector, make sure it is designed to handle concurrent threads appropriately.
                    - If thread safety is difficult to guarantee, consider using **sequential** streams or explicitly managing synchronization.
            - Problems when order matters in processing
                - Loss of Order: Parallel streams can result in operations being applied in any order, which could break functionality that depends on the order of elements. For example, if you're processing a list of items and the order in which the operations are applied is important, parallel streams might not maintain this order.
                - Order-Sensitive Operations: If you have operations that rely on the order of processing (e.g., reduce, collect, etc.), parallel streams could break the expected behavior because intermediate operations might be executed in different threads, each processing different parts of the data.
                - Dealing with Order in Parallel Streams:
                    - Use forEachOrdered: If you need to preserve the order when processing in parallel, you can use the forEachOrdered terminal operation. This ensures that the results are processed in the original order, even though the stream is running in parallel.
                    - Avoid Parallelism for Order-Sensitive Operations: In some cases, it’s better to use a sequential stream (stream() instead of parallelStream()) if you absolutely need to maintain the order of elements. Parallelism can be overkill and lead to unnecessary complexity when order is critical.
            - Overhead from managing multiple threads
        - Kotlin Sequences offer a simpler, non-parallel, lazy model that avoids these pitfalls but is also inherently more efficient in situations where parallelism is not required.
    - Kotlin Sequence Debugger
        - Kotlin provides tools like the Sequence Debugger, which can help developers understand the flow of operations in a lazy sequence, making it easier to debug performance issues and verify correctness in lazy processing.
- Item 52: Consider associating elements to a map
    - A Map is a collection of key-value pairs, where each key is unique and maps to exactly one value.
    - In certain situations, associating elements to a map can provide several benefits over using other collections like lists, arrays, or sets. A map offers fast lookups based on keys, which can be critical for performance when working with large datasets or when you need quick access to certain elements.
- Item 53: Consider using groupingBy instead of groupBy
    - groupBy:
        - The groupBy function directly creates a Map where the key is the value returned by the lambda function and the value is a List of elements that correspond to that key.
        - The entire collection is processed, and a new collection (a Map) is created, which can be less efficient in cases where further aggregation or transformation of the grouped elements is required.
    - groupingBy:
        - The groupingBy function creates a Grouping object, which allows lazy evaluation. This means the grouping operation itself doesn't immediately create a map; instead, it sets up a lazy transformation that can be followed by aggregation operations, such as counting or folding.
        - It is more efficient than groupBy because it allows for lazy operations and supports multiple aggregation steps without recomputing the grouped elements.
    - Performance Consideration:
        - groupBy always creates an immediate map that holds all the elements. This can be less efficient when you only need to apply subsequent transformations or aggregations to the grouped data.
        - groupingBy is lazy, meaning it only processes the elements as needed, which can be more memory- and computation-efficient, especially for large collections or complex operations.
    - Benefits of groupingBy Over groupBy:
        - Efficiency: groupingBy is more efficient, especially when the collection is large or when multiple aggregation steps are involved, as it avoids creating intermediate collections.
        - Multiple Aggregations: With groupingBy, you can perform various transformations like eachCount(), fold(), reduce(), etc., without re-processing the entire collection.
        - Lazy Evaluation: Operations like fold and eachCount are executed lazily, meaning the grouping itself doesn't trigger the creation of a full map, saving on memory and processing time.
    - When to Use groupBy vs groupingBy
        - Use groupBy when:
            - You need a straightforward grouping and don't need to perform multiple aggregations or transformations.
            - You want to immediately get a Map with the grouped data.
        - Use groupingBy when:
            - You need multiple aggregations or transformations on the grouped data, such as counting, folding, or reducing.
            - You are working with a large dataset, and you want to avoid holding the entire collection in memory when grouping.
            - You prefer lazy evaluation to avoid unnecessary computations or memory usage.
- Item 54: Limit the number of operations
    - Every collection processing method is a cost. For standard collection processing, the cost is:
        - additional iteration over the elements,
        - new collection created under the hood.
    - For sequence processing, it is:
        - another object wrapping the whole sequence,
        - lambda expression creation.

| **Instead of**                                                                     | **Use**                                                     |
|------------------------------------------------------------------------------------|-------------------------------------------------------------|
| `.filter { it != null } .map { it!! }`                                             | `.filterNotNull()`                                          |
| `.map<Transformation>().filterNotNull()`                                           | `.mapNotNull { <Transformation> }`                          |
| `.map<Transformation>().joinToString()`                                            | `.joinToString { <Transformation> }`                        |
| `.filter { <Predicate 1> } .filter { <Predicate 2> }`                              | `.filter { <Predicate 1> && <Predicate 2> }`                |
| `.filter { it is Type } .map { it as Type }`                                       | `.filterIsInstance<Type>()`                                 |
| `.sortedBy <Key 2> } .sortedBy <Key 1> }`                                          | `.sortedWith(compareBy { <Key 1> }, { <Key 2> })`           |
| `listOf(...) .filterNotNull()`                                                     | `listOfNotNull(...)`                                        |
| `.withIndex().filter { index, elem -> <Predicate using index> } .map { it.value }` | `.filterIndexed { index, elem -> <Predicate using index> }` |

- Item 55: Consider Arrays with primitives for performance-critical processing
- In most cases, a List or Set should be preferred over an Array.
- However, if you're working with large collections of primitives, using an Array with primitives (e.g., IntArray) can significantly improve both performance and memory usage.
- Item 56: Consider using mutable collections
    - The biggest advantage of using mutable collections instead of immutable is that they are faster in terms of performance. When we add an element to an immutable collection, we need to create a new collection and add all elements to it.

Dictionary:
- Function vs method
- Extension vs member
- Parameter vs argument
- Primary vs Secondary constructor
