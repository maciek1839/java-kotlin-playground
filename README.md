# Java & Kotlin playground

| Branch |                                                                                            Pipeline                                                                                            |                                                                                         Code coverage                                                                                          |                                       Jacoco test report                                        |                                 SonarCloud                                 |
|:------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------:|
|  main  | [![pipeline status](https://gitlab.com/ShowMeYourCodeYouTube/java-kotlin-playground/badges/main/pipeline.svg)](https://gitlab.com/ShowMeYourCodeYouTube/java-kotlin-playground/-/commits/main) | [![coverage report](https://gitlab.com/ShowMeYourCodeYouTube/java-kotlin-playground/badges/main/coverage.svg)](https://gitlab.com/ShowMeYourCodeYouTube/java-kotlin-playground/-/commits/main) | [link](https://showmeyourcodeyoutube.gitlab.io/java-kotlin-playground/jacoco-report/index.html) | [link](https://sonarcloud.io/organizations/showmeyourcodeyoutube/projects) |

---


Releases overview with code samples and language specific features.

Technology / Tools:
- Java
- Kotlin

Below you can find some references to interesting parts of documentation:
- [What Kotlin has that Java does not](https://kotlinlang.org/docs/comparison-to-java.html#what-kotlin-has-that-java-does-not)
- [What Java has that Kotlin does not](https://kotlinlang.org/docs/comparison-to-java.html#what-java-has-that-kotlin-does-not)

## Getting started

*Run the main method of a given module and follow printed information and examples.*


## Object in Java vs Kotlin

In Java, an Object typically refers to an instance of a class. However, in Kotlin, the object keyword introduces singleton objects, companion objects, and object expressions.

## Are Java and Kotlin strongly typed languages?

- Java is strongly typed, meaning types are strictly enforced at compile time, and the types are explicitly declared.
  - Java 10 introduced the var keyword, which allows local variable type inference similar to Kotlin’s type inference. With var, the compiler can infer the type of a variable based on the assigned value, reducing the need for explicit type declarations.
- Kotlin is also strongly typed but is more flexible due to features like type inference and null safety.
  - Kotlin provides type inference, allowing the compiler to deduce the types of variables and function return types based on the assigned value.
  - Type inference makes Kotlin concise and flexible, reducing the need for explicit type declarations, while still maintaining type safety.
- Weakly typed languages (e.g., JavaScript, Python) allow variables to change types dynamically at runtime without much restriction or compile-time checking.

## What are the differences between mutable and immutable objects?

- Mutability:
    - Mutable: Can change state.
    - Immutable: Cannot change state.
- Memory Management:
    - Mutable: Modifies existing memory.
    - Immutable: Creates new objects for changes.
- Thread Safety:
    - Mutable: Not inherently thread-safe.
    - Immutable: Thread-safe by default.
- Performance:
    - Mutable: Generally more efficient for frequent changes.
    - Immutable: May have performance overhead due to object creation.
- Use Cases:
    - Mutable: Entities with frequently changing state.
    - Immutable: Constant data or scenarios needing thread safety and predictability.

### How can mutable objects be created?

Creating a class and generate setters in the IDE making it mutable.

```java
class Person {
    private String name;  // Mutable field

    // Constructor
    public Person(String name) {
        this.name = name;
    }

    // Setter (allows modification)
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice");
        System.out.println(person.getName()); // Alice

        person.setName("Bob"); // Modify state
        System.out.println(person.getName()); // Bob
    }
}
```

```kotlin
class Person(var name: String)  // 'var' makes 'name' mutable

fun main() {
    val person = Person("Alice")
    println(person.name) // Alice

    person.name = "Bob" // Modify state
    println(person.name) // Bob
}
```

Unlike Java code, which requires explicit getter and setter methods, Kotlin automatically provides these methods by default, simplifying property management.
