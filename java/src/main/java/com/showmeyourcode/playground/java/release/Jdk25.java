package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jdk25 {

    private Jdk25() {
    }

    public static void main() {
        log.info("{} JDK 25", Descriptions.INDENT1);
        log.info("JDK 25 reached General Availability on 16th September 2025.");
        log.info("https://openjdk.org/projects/jdk/25/");

        compactSourceFiles();
        flexibleConstructorBodies();
        unnamedVariablesAndPatterns();
        scopedValues();
        streamGatherers();
        structuredConcurrency();
    }

    private static void compactSourceFiles() {
        // JEP 512: Compact Source Files and Instance Main Methods
        // https://openjdk.org/jeps/512
        log.info("\n{} Compact Source Files and Instance Main Methods (JEP 512)", Descriptions.INDENT2);
        log.info("""
                Removes boilerplate to make Java more accessible, especially for beginners.
                'void main()' replaces 'public static void main(String[] args)' with no class declaration needed.
                A new IO class in java.lang provides basic console I/O (e.g. IO.println).
                
                Example (standalone .java file, run with: java HelloWorld.java):
                
                  void main() {
                      IO.println("Hello Java 25 World!");
                  }
                """);
    }

    private static void flexibleConstructorBodies() {
        // JEP 513: Flexible Constructor Bodies
        // https://openjdk.org/jeps/513
        log.info("\n{} Flexible Constructor Bodies (JEP 513)", Descriptions.INDENT2);
        log.info("""
                Allows statements before super()/this() in a constructor.
                Enables early validation and field assignment without invoking the parent constructor unnecessarily.
                
                Example (requires JDK 25):
                
                  class Car extends Vehicle {
                      String color;
                      Car(int wheels, String color) {
                          if (wheels < 4 || wheels > 6) {
                              throw new IllegalArgumentException("A car must have 4-6 wheels.");
                          }
                          this.color = color;   // assigned BEFORE super()
                          super(wheels);        // parent only called on valid input
                      }
                  }
                
                Before JEP 513, super() had to be the very first statement, so the parent
                constructor would always be called even on invalid input.
                """);
    }

    private static void unnamedVariablesAndPatterns() {
        // JEP 456: Unnamed Variables and Patterns
        // https://openjdk.org/jeps/456
        log.info("\n{} Unnamed Variables and Patterns (JEP 456)", Descriptions.INDENT2);
        log.info("""
                Use _ (underscore) to mark variables or patterns that are intentionally unused.
                Applicable in catch blocks, switch pattern cases, and lambda parameters.
                
                Example (requires JDK 25):
                
                  // Unnamed catch variable
                  try {
                      Integer.parseInt("bad");
                  } catch (NumberFormatException _) {
                      System.out.println("Bad integer!");
                  }
                
                  // Unnamed switch pattern
                  switch (obj) {
                      case Integer _ -> "It's an Integer";
                      case String _  -> "It's a String";
                      default        -> "Unknown";
                  }
                """);
    }

    private static void scopedValues() {
        // JEP 506: Scoped Values — stable in JDK 25, preview in JDK 21
        // https://openjdk.org/jeps/506
        log.info("\n{} Scoped Values (JEP 506)", Descriptions.INDENT2);
        log.info("""
                A safer, more efficient alternative to ThreadLocal.
                ScopedValue is immutable within its bound scope, auto-cleaned up after the scope exits,
                and works seamlessly with virtual threads. No manual remove() needed.
                
                Example (requires JDK 25):
                
                  private static final ScopedValue<String> USER = ScopedValue.newInstance();
                
                  ScopedValue.where(USER, "alice").run(() -> {
                      System.out.println(USER.get()); // "alice"
                      ScopedValue.where(USER, "bob").run(() ->
                          System.out.println(USER.get()) // "bob" — nested rebinding
                      );
                      System.out.println(USER.get()); // back to "alice"
                  });
                  // USER.isBound() == false — auto cleaned up, no remove() needed
                """);
    }

    private static void streamGatherers() {
        // JEP 485: Stream Gatherers — stable in JDK 25, introduced in JDK 22
        // https://openjdk.org/jeps/485
        log.info("\n{} Stream Gatherers (JEP 485)", Descriptions.INDENT2);
        log.info("""
                Enhances the Stream API with custom intermediate operations via Gatherer.
                Allows building stateful, short-circuiting, and one-to-many pipeline steps.
                Built-in gatherers live in java.util.stream.Gatherers.
                
                Example (requires JDK 25):
                
                  // Sliding window: [[1,2,3],[2,3,4],[3,4,5]]
                  List.of(1, 2, 3, 4, 5)
                      .stream()
                      .gather(Gatherers.windowSliding(3))
                      .toList();
                
                  // Fixed (non-overlapping) window: [[a,b],[c,d],[e]]
                  List.of("a","b","c","d","e")
                      .stream()
                      .gather(Gatherers.windowFixed(2))
                      .toList();
                """);
    }

    private static void structuredConcurrency() {
        // JEP 480: Structured Concurrency — stable in JDK 25, preview in JDK 21
        // https://openjdk.org/jeps/480
        log.info("\n{} Structured Concurrency (JEP 480)", Descriptions.INDENT2);
        log.info("""
                Treats groups of related tasks running in different threads as a single unit of work.
                Simplifies error handling and cancellation — if one task fails, siblings are cancelled.
                The scope lifetime is tied to the try-with-resources block.
                
                Example (requires JDK 25):
                
                  try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                      var t1 = scope.fork(() -> fetchUserData());
                      var t2 = scope.fork(() -> fetchOrderData());
                
                      scope.join();           // wait for both
                      scope.throwIfFailed();  // propagate any error
                
                      process(t1.get(), t2.get());
                  }
                  // scope auto-closes: all subtasks are guaranteed done here
                """);
    }
}
