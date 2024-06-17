package com.showmeyourcode.playground.java.code.exercise.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Some stream exercises: <a href="https://www.baeldung.com/java-stream-reduce">Java Stream Reduce</a>
 * <br>
 * Below you can find examples of grouping and partitioning as well.
 */
@Slf4j
public class StreamReduceExercise {

    private StreamReduceExercise() {
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20),
                new Person("David", 25),
                new Person("Eve", 17)
        );

        // Example 1: Partitioning persons into adults (age >= 18) and minors (age < 18)
        Map<Boolean, List<Person>> partitionedByAge =
                persons.stream()
                        .collect(Collectors.partitioningBy(person -> person.age() >= 18));

        log.info("Partitioned by age (Adults and Minors):");
        log.info("Adults: {}", partitionedByAge.get(true));
        log.info("Minors: {}", partitionedByAge.get(false));

        // Example 2: Grouping persons by their age
        Map<Integer, List<Person>> groupedByAge =
                persons.stream()
                        .collect(Collectors.groupingBy(Person::age));

        log.info("Grouped by age:");
        groupedByAge.forEach((age, list) -> log.info("Age {}: {}", age, list));
    }

    record Person(String name, int age) {
    }

    /**
     * Use Optional to filter nulls.
     */
    public static String[] filterNulls(String[] strings){
        return Stream.of(strings).map(Optional::ofNullable).filter(Optional::isPresent).map(Optional::get).toArray(String[]::new);
    }

    public static int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static String concatenate(List<String> letters, String separator) {
        return String.join(separator, letters);
    }

    public static String concatenate(List<String> letters) {
        return letters.stream().reduce("", String::concat);
    }

    public static int divideListElements(List<Integer> values, int divider) {
        Objects.requireNonNull(values, "List cannot be null");
        if (divider == 0) {
            throw new IllegalArgumentException("Divider cannot be 0");
        }
        return values.stream()
                .reduce(0, (a, b) -> a / divider + b / divider);
    }
}
