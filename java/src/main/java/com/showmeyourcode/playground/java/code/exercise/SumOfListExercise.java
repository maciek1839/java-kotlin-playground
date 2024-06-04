package com.showmeyourcode.playground.java.code.exercise;

import java.util.Arrays;
import java.util.List;

public class SumOfListExercise {
    public static void main(String[] args) {
        RecursiveSumOfList.main();
        TailRecursiveSumOfList.main();
    }
}

class RecursiveSumOfList {
    public static void main() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of list: " + sum(numbers)); // Output: 15
    }

    public static int sum(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0; // Base case: sum of an empty list is 0
        } else {
            return numbers.get(0) + sum(numbers.subList(1, numbers.size())); // Recursive case
        }
    }
}


class TailRecursiveSumOfList {
    public static void main() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of list: " + sum(numbers)); // Output: 15
    }

    public static int sum(List<Integer> numbers) {
        return sumHelper(numbers, 0);
    }

    private static int sumHelper(List<Integer> numbers, int accumulator) {
        if (numbers.isEmpty()) {
            return accumulator; // Base case: return the accumulated sum
        } else {
            return sumHelper(numbers.subList(1, numbers.size()), accumulator + numbers.get(0)); // Tail recursive call
        }
    }
}
