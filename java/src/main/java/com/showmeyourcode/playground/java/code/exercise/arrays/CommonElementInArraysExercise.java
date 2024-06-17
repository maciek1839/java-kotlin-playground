package com.showmeyourcode.playground.java.code.exercise.arrays;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find common elements in n lists given as an array of arrays and return shared element (number)
 * and corresponding indexes of any array which contains it.
 * You don't need to combine an index with an array, only with the shared element.
 */
@Slf4j
public class CommonElementInArraysExercise {

    private CommonElementInArraysExercise() {
    }

    /**
     * Sorting the list first would require at least O(nlogn) time.
     * If you are looking for a more efficient algorithm you could get O(n) using hashmaps.
     * <br>
     * Reference: <a href="https://stackoverflow.com/questions/27849344/find-indices-of-common-elements-in-arraylists-in-java">Find indices of common elements</a>
     */
    public static void main(String[] args) {
        int[][] lists = {{0, 1, 2}, {3, 0, 4}, {5, 6, 0}};

        Map<Integer, List<Integer>> map = getIndicesOfSharedElements(lists);

        // Print out indexes for elements that are shared between all lists
        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            // check that the list of indexes matches the # of lists
            if (e.getValue().size() == lists.length) {
                log.info("{}: {}", e.getKey(), e.getValue());
            }
        }
    }

    static @NotNull Map<Integer, List<Integer>> getIndicesOfSharedElements(int[][] lists) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] list : lists) {
            for (int j = 0; j < lists[0].length; j++) {
                // create the list if this is the first occurrence
                if (!map.containsKey(list[j]))
                    map.put(list[j], new ArrayList<>());

                // add the index to the list
                map.get(list[j]).add(j);
            }
        }
        return map;
    }
}
