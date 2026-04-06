package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class Jdk11 {

    private Jdk11() {
    }

    public static void main() {
        log.info("\n{} JDK 11", Descriptions.INDENT1);
        log.info("JDK 11 was released on 25th September 2018.");
        log.info("https://www.oracle.com/java/technologies/javase/11-relnote-issues.html");
        log.info("Features overview: https://www.digitalocean.com/community/tutorials/java-11-features");

        localVarLambdaParameters();
        newStringMethods();
        collectionToArray();
        optionalIsEmpty();
        predicateNot();
        httpClient();
    }

    private static void localVarLambdaParameters() {
        log.info("\n{} Local-Variable Syntax for Lambda Parameters (JEP 323)", Descriptions.INDENT2);
        log.info("""
                JDK 11 allows 'var' to be used in lambda expressions.
                This enables adding annotations to lambda parameters
                without losing the benefits of type inference.
                """);

        // Using var in lambda parameters
        List<String> languages = List.of("Java", "Kotlin", "Scala", "Groovy");
        var filtered = languages.stream()
                .filter((var s) -> s.startsWith("J"))
                .collect(Collectors.toList());
        log.info("Languages starting with 'J': {}", filtered);
    }

    private static void newStringMethods() {
        log.info("\n{} New String Methods", Descriptions.INDENT2);
        log.info("""
                JDK 11 introduced several new utility methods to the String class:
                - isBlank() - checks if a string is empty or contains only whitespace
                - lines() - returns a stream of lines extracted from the string
                - strip(), stripLeading(), stripTrailing() - Unicode-aware whitespace removal
                - repeat(int) - repeats the string a given number of times
                """);

        String multiline = "Hello\nKotlin\nJava\n";
        log.info("Lines from multiline string: {}", multiline.lines().collect(Collectors.toList()));

        String blank = "   ";
        log.info("Is '   ' blank? {}", blank.isBlank());

        String padded = "  Hello  ";
        log.info("strip('  Hello  '): '{}'", padded.strip());
        log.info("stripLeading: '{}'", padded.stripLeading());
        log.info("stripTrailing: '{}'", padded.stripTrailing());

        log.info("repeat: {}", "ab".repeat(3));
    }

    private static void collectionToArray() {
        log.info("\n{} Collection.toArray(IntFunction)", Descriptions.INDENT2);
        log.info("""
                A new default method toArray(IntFunction) was added to java.util.Collection.
                This allows easy conversion of a collection to an array of the desired type.
                """);

        List<String> items = List.of("one", "two", "three");
        String[] array = items.toArray(String[]::new);
        log.info("Array from collection: [{}, {}, {}]", array[0], array[1], array[2]);
    }

    private static void optionalIsEmpty() {
        log.info("\n{} Optional.isEmpty()", Descriptions.INDENT2);
        log.info("""
                A new isEmpty() method was added to Optional, complementing isPresent().
                This makes code more readable when checking for absent values.
                """);

        Optional<String> empty = Optional.empty();
        Optional<String> present = Optional.of("value");
        log.info("Optional.empty().isEmpty(): {}", empty.isEmpty());
        log.info("Optional.of('value').isEmpty(): {}", present.isEmpty());
    }

    private static void predicateNot() {
        log.info("\n{} Predicate.not()", Descriptions.INDENT2);
        log.info("""
                A static method Predicate.not() was added for negating predicates.
                This is particularly useful with method references where
                negate() would not be directly applicable.
                """);

        List<String> words = List.of("hello", "", "world", "", "java");
        var nonEmpty = words.stream()
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        log.info("Non-empty words: {}", nonEmpty);
    }

    private static void httpClient() {
        log.info("\n{} Standardized HTTP Client API (JEP 321)", Descriptions.INDENT2);
        log.info("""
                The HTTP Client API was standardized in JDK 11.
                It was first introduced as an incubator module in JDK 9.
                The API supports HTTP/1.1 and HTTP/2, both synchronous
                and asynchronous programming models.
                """);

        // Demonstrating the API construction (without making actual network calls)
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .build();
        log.info("HTTP Client version: {}", client.version());
        log.info("HTTP Request URI: {}, method: {}", request.uri(), request.method());
    }
}
