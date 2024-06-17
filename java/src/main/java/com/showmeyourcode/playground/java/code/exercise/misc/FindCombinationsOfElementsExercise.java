package com.showmeyourcode.playground.java.code.exercise.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of a given array of chars.
 * <br>
 * <br>
 * Combinations refer to selections of items from a larger set where the order does not matter. In other words, combinations are all possible ways to select a subset of items without considering the order of selection.
 * <br>
 * Example: Using the set {A, B, C}, the combinations of size 2 (selecting 2 items at a time) include:
 * AB
 * AC
 * BC
 * <br>
 * Note that combinations like BA and CA are considered the same as AB and AC, respectively, because the order of selection does not matter.
 * <br>
 * <br>
 * Difference Between Permutations and Combinations:
 * <br>
 * Permutations consider the order of arrangement, while combinations do not.
 * <br>
 * Example: For the set {A, B, C}:
 * Permutations of size 2: AB, BA, AC, CA, BC, CB (6 permutations).
 * Combinations of size 2: AB, AC, BC (3 combinations).
 */
public class FindCombinationsOfElementsExercise {

    private FindCombinationsOfElementsExercise() {
    }

    public static List<String> findAllCombinations(char[] arr) {
        List<String> result = new ArrayList<>();
        backtrack(arr, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(char[] arr, int index, StringBuilder current, List<String> result) {
        // Base case: Add the current combination to the result
        if(!current.isEmpty()){
            result.add(current.toString());
        }

        // Explore all possibilities for the current index
        for (int i = index; i < arr.length; i++) {
            // Choose
            current.append(arr[i]);

            // Explore further
            backtrack(arr, i + 1, current, result);

            // Un-choose (backtrack)
            current.deleteCharAt(current.length() - 1);
        }
    }
}
