package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindIndexesOfDuplicatesExercise {

    private FindIndexesOfDuplicatesExercise() {
    }

    static Map<Integer, List<Integer>> findDuplicateIndexes(Integer[] array) {
        // Map to store the element as key and list of its indexes as value
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        // Populate the map with elements and their corresponding indexes
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            indexMap.computeIfAbsent(element, k -> new ArrayList<>()).add(i);
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

    static Map<Integer, List<Integer>> findDuplicateIndexesUsingStreams(Integer[] array) {
        Map<Integer, List<Integer>> indexMap = IntStream.range(0, array.length)
                .boxed()
                .collect(Collectors.groupingBy(i -> array[i]));

        return indexMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
