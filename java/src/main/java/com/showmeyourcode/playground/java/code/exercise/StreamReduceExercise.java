package com.showmeyourcode.playground.java.code.exercise;

import java.util.Arrays;
import java.util.List;

// https://www.baeldung.com/java-stream-reduce
public class StreamReduceExercise {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("Sum: "+sum(numbers));
        System.out.println("Sum: "+sum2(numbers));

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println("All: "+sumStrings(letters));
        System.out.println("All: "+sumStrings2(letters));

        divideListElements(Arrays.asList(5, 6), 1);
    }

    public static int sum(List<Integer> numbers){
        return numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public static int sum2(List<Integer> numbers){
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static String sumStrings(List<String> letters){
        return letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
    }

    public static String sumStrings2(List<String> letters){
        return letters.stream().reduce("", String::concat);
    }

    public static int divideListElements(List<Integer> values, int divider) {
        return values.stream()
                .reduce(0, (a, b) -> {
                    try {
                        return a / divider + b / divider;
                    } catch (ArithmeticException e) {
                        // do error logic
                    }
                    return 0;
                });
    }
}
