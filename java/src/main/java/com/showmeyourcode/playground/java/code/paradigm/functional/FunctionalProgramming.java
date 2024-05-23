package com.showmeyourcode.playground.java.code.paradigm.functional;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class FunctionalProgramming {

    public static void main() {
        LOGGER.info("\n{} {}", Descriptions.FUNCTIONAL_PROGRAMMING, Descriptions.INDENT1);

        HandleSideEffects.main();
        PureFunctionsAndExceptions.main();
        StreamAPI.main();
        FunctionalInterfaces.main();
        StreamOperations.main();
        CurryUncurry.main();
        FunctionChains.main();
        ReferentialTransparency.main();
    }
}

class ReferentialTransparency {
    public static void main() {
        int result = add(2, 3) + add(2, 3);
        // Can be replaced with 5 + 5 without changing the program's behavior
        System.out.println(result); // Output: 10
    }

    public static int add(int x, int y) {
        return x + y;
    }
}

class FunctionChains{
    public static void main(){
        // Function that doubles a number
        Function<Integer, Integer> doubleValue = x -> x * 2;

        // Function that adds 3 to a number
        Function<Integer, Integer> addThree = x -> x + 3;

        // Applying the doubleValue function
        Integer applyResult = doubleValue.apply(5);
        System.out.println("Apply Result: " + applyResult); // Output: 10

        // Composing functions: (x + 3) * 2
        Function<Integer, Integer> addThenDouble = doubleValue.compose(addThree);
        Integer composeResult = addThenDouble.apply(5);
        System.out.println("Compose Result: " + composeResult); // Output: 16 ((5 + 3) * 2)

        // Composing functions: (x * 2) + 3
        Function<Integer, Integer> doubleThenAdd = doubleValue.andThen(addThree);
        Integer andThenResult = doubleThenAdd.apply(5);
        System.out.println("AndThen Result: " + andThenResult); // Output: 13 ((5 * 2) + 3)
    }
}

class CurryUncurry{
    public static void main(){
        currying();
        uncurrying();
    }

    private static void uncurrying() {
        // Curried function
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

        // Uncurried function
        BiFunction<Integer, Integer, Integer> uncurriedAdd = (a, b) -> curriedAdd.apply(a).apply(b);

        // Using the uncurried function
        Integer result = uncurriedAdd.apply(5, 3);

        System.out.println("Uncurried Result: " + result); // Output: 8
    }

    private static void currying() {
        // Original BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // Curried function
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> add.apply(a, b);

        // Using the curried function
        Function<Integer, Integer> addFive = curriedAdd.apply(5);
        Integer result = addFive.apply(3);

        System.out.println("Curried Result: " + result); // Output: 8
    }
}

class StreamOperations{

    public static void main(){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Edward");

        // Intermediate operations: filter, map, sorted
        // Terminal operation: collect
        List<String> result = names.stream()
                .filter(name -> name.length() > 3)    // Intermediate operation
                .map(String::toUpperCase)             // Intermediate operation
                .sorted()                             // Intermediate operation
                .collect(Collectors.toList());        // Terminal operation

        System.out.println(result); // Output: [ALICE, CHARLIE, DAVID, EDWARD]

        // Intermediate operation: peek (used to demonstrate the effect)
        // Terminal operation: forEach
        names.stream()
                .filter(name -> name.length() > 3)    // Intermediate operation
                .map(String::toUpperCase)             // Intermediate operation
                .peek(System.out::println)            // Intermediate operation (side effect)
                .sorted()                             // Intermediate operation
                .forEach(name -> {});                 // Terminal operation (triggers stream processing)
    }
}

class FunctionalInterfaces{

    public static void main(){
        functionalInterfaces();
    }

    private static void functionalInterfaces() {
        // Predicate example
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Predicate: " + isEmpty.test("")); // Output: true
        System.out.println("Predicate: " + isEmpty.test("Hello")); // Output: false

        // Consumer example
        Consumer<String> print = System.out::println;
        System.out.print("Consumer: ");
        print.accept("Hello, World!"); // Output: Hello, World!

        // Function example
        Function<String, Integer> stringLength = String::length;
        System.out.println("Function: " + stringLength.apply("Hello")); // Output: 5

        /* Supplier example */
        Supplier<String> supplier = () -> "Hello, World!";
        System.out.println("Supplier: " + supplier.get()); // Output: Hello, World!

        // UnaryOperator example
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println("UnaryOperator: " + square.apply(5)); // Output: 25

        // BinaryOperator example
        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("BinaryOperator: " + sum.apply(3, 7)); // Output: 10

        // BiPredicate example
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("BiPredicate: " + startsWith.test("Hello", "He")); // Output: true
        System.out.println("BiPredicate: " + startsWith.test("Hello", "Hi")); // Output: false

        // BiConsumer example
        BiConsumer<String, Integer> printMultipleTimes = (str, num) -> {
            System.out.println("BiConsumer: ");
            for (int i = 0; i < num; i++) {
                System.out.println(str);
            }
        };
        printMultipleTimes.accept("Hello", 3); // Output: Hello (three times)

        // BiFunction example
        BiFunction<String, String, Integer> compare = String::compareTo;
        System.out.println("BiFunction: " + compare.apply("Hello", "World")); // Output: -15

        // ToIntFunction example
        ToIntFunction<String> length = String::length;
        System.out.println("ToIntFunction: " + length.applyAsInt("Hello")); // Output: 5

        // ToDoubleFunction example
        ToDoubleFunction<String> parseDouble = Double::parseDouble;
        System.out.println("ToDoubleFunction: " + parseDouble.applyAsDouble("42.0")); // Output: 42.0

        // ToLongFunction example
        ToLongFunction<String> parseLong = Long::parseLong;
        System.out.println("ToLongFunction: " + parseLong.applyAsLong("42")); // Output: 42
    }
}

class StreamAPI{

    // The Stream API allows you to perform operations on collections in a functional style, such as map, filter, and reduce.
    public static void main(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squared = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(squared); // Output: [1, 4, 9, 16, 25]
    }
}

class HandleSideEffects {

    /**
     * In Java, a side effect occurs when a method modifies some state or interacts with the outside world
     * (e.g., printing to the console, modifying a global variable).
     * Functional programming encourages the use of pure functions,
     * which avoid side effects by not altering any state or depending on mutable data.
     */
    public static void main(){
        // A side effect is an interaction with the outside world.
        // Here are some examples of these types of interactions: Reading a file, and/or writing to a file.
        // Vavr's Try class handles exceptions in a functional way.
        String filePath = "example.txt";

        // Using Vavr's Try to handle the side effect of reading a file
        Try<List<String>> lines = readFile(filePath);

        // Handling the result
        lines.onSuccess(HandleSideEffects::printLines)
                .onFailure(Throwable::printStackTrace);
    }

    // Function that uses Vavr's Try to handle IO exceptions
    public static Try<List<String>> readFile(String filePath) {
        return Try.of(() -> Files.lines(Paths.get(filePath)).collect(Collectors.toList()));
    }

    // Function with side effect (printing)
    public static void printLines(List<String> lines) {
        lines.forEach(System.out::println);
    }
}

class PureFunctionsAndExceptions{

    /**
     * Throwing an exception does not inherently disqualify a function from being pure.
     * A function can still be considered pure if it throws an exception, as long as it meets the following conditions:
     * - The exception is deterministic, meaning that for the same input, the function will either consistently throw the same exception or produce the same result.
     * - The function does not cause any side effects before throwing the exception.
     */
    public static void main(){
        try {
            System.out.println(divide(10, 2)); // Output: 5.0
            System.out.println(divide(10, 0)); // Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }

        // Refactoring with Functional Programming Principles
        System.out.println(divideFunctional(10, 2).orElse(Double.NaN)); // Output: 5.0
        System.out.println(divideFunctional(10, 0).orElse(Double.NaN)); // Output: NaN

        // Or when an exception should be returned
        Result<Double> result1 = divideWithException(10, 2);
        Result<Double> result2 = divideWithException(10, 0);

        handleResult(result1);
        handleResult(result2);
    }

    // Pure function: Throws an exception for invalid input
    public static double divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) numerator / denominator;
    }

    // Pure function: Returns an Optional to handle error cases
    public static Optional<Double> divideFunctional(int numerator, int denominator) {
        if (denominator == 0) {
            return Optional.empty();
        }
        return Optional.of((double) numerator / denominator);
    }

    public static class Result<T> {
        private final T value;
        private final Exception exception;

        private Result(T value, Exception exception) {
            this.value = value;
            this.exception = exception;
        }

        public static <T> Result<T> success(T value) {
            return new Result<>(value, null);
        }

        public static <T> Result<T> failure(Exception exception) {
            return new Result<>(null, exception);
        }

        public boolean isSuccess() {
            return exception == null;
        }

        public T getValue() {
            if (exception != null) {
                throw new IllegalStateException("Result is failure. Check isSuccess() before calling getValue().");
            }
            return value;
        }

        public Exception getException() {
            if (exception == null) {
                throw new IllegalStateException("Result is success. Check isSuccess() before calling getException().");
            }
            return exception;
        }
    }

    // Function that performs division and returns a Result
    public static Result<Double> divideWithException(int numerator, int denominator) {
        if (denominator == 0) {
            return Result.failure(new ArithmeticException("Cannot divide by zero"));
        }
        return Result.success((double) numerator / denominator);
    }

    // Function to handle the Result
    public static void handleResult(Result<Double> result) {
        if (result.isSuccess()) {
            System.out.println("Result: " + result.getValue());
        } else {
            System.err.println("Error: " + result.getException().getMessage());
        }
    }
}
