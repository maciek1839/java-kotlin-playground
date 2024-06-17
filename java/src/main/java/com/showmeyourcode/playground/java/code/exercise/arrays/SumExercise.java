package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.List;

public class SumExercise {

    private SumExercise() {
    }

    static int sumUsingStream(List<Integer> numbers){
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    static int sumUsingStreamReduce(List<Integer> numbers){
        return numbers.stream().reduce(0, Integer::sum);
    }

    static int sumRecursive(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0; // Base case: sum of an empty list is 0
        } else {
            return numbers.getFirst() + sumRecursive(numbers.subList(1, numbers.size())); // Recursive case
        }
    }

    static int sumTailRecursion(List<Integer> numbers) {
        return sumHelper(numbers, 0);
    }

    private static int sumHelper(List<Integer> numbers, int accumulator) {
        if (numbers.isEmpty()) {
            return accumulator; // Base case: return the accumulated sum
        } else {
            return sumHelper(numbers.subList(1, numbers.size()), accumulator + numbers.getFirst()); // Tail recursive call
        }
    }
}
