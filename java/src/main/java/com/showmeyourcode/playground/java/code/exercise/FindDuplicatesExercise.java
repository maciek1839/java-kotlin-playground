package com.showmeyourcode.playground.java.code.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindDuplicatesExercise {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 1, 3, 6, 3, 6, 6};
        Integer[] arrayInteger = {1, 2, 3, 1, 3, 6, 3, 6, 6};

        Map<Integer, List<Integer>> duplicates1 = findDuplicateIndexes(array);

        for (Map.Entry<Integer, List<Integer>> entry : duplicates1.entrySet()) {
            System.out.println("Element " + entry.getKey() + " is duplicated at indexes: " + entry.getValue());
        }

        Map<Integer, List<Integer>> duplicates2 = findDuplicateIndexesNestedLoops(array);

        for (Map.Entry<Integer, List<Integer>> entry : duplicates2.entrySet()) {
            System.out.println("Element " + entry.getKey() + " is duplicated at indexes: " + entry.getValue());
        }

        Map<Integer, List<Integer>> duplicates3 = findDuplicateIndexesStreams(arrayInteger);

        for (Map.Entry<Integer, List<Integer>> entry : duplicates3.entrySet()) {
            System.out.println("Element " + entry.getKey() + " is duplicated at indexes: " + entry.getValue());
        }
    }

    static Map<Integer, List<Integer>> findDuplicateIndexesNestedLoops(int[] array) {
        Map<Integer, List<Integer>> duplicates = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    if (!duplicates.containsKey(array[i])) {
                        duplicates.put(array[i], new ArrayList<>(Arrays.asList(i, j)));
                    } else if (!duplicates.get(array[i]).contains(j)) {
                        duplicates.get(array[i]).add(j);
                    }
                }
            }
        }

        return duplicates;
    }

    static Map<Integer, List<Integer>> findDuplicateIndexes(int[] array) {
        // Map to store the element as key and list of its indexes as value
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        // Populate the map with elements and their corresponding indexes
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            if (!indexMap.containsKey(element)) {
                indexMap.put(element, new ArrayList<>());
            }
            indexMap.get(element).add(i);
        }

        // Create a result map to store duplicates and their indexes
        Map<Integer, List<Integer>> duplicates = new HashMap<>();

        // Collect elements with more than one index (duplicates)
        for (Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }

        return duplicates;
    }

    static Map<Integer, List<Integer>> findDuplicateIndexesStreams(Integer[] array) {
        Map<Integer, List<Integer>> indexMap = IntStream.range(0, array.length)
                .boxed()
                .collect(Collectors.groupingBy(i -> array[i]));

        return indexMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
