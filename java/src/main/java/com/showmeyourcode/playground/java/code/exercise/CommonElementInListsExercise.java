package com.showmeyourcode.playground.java.code.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonElementInListsExercise {

    // Sorting the list first would require at least O(nlogn) time. If you are looking for a more efficient algorithm you could get O(n) using hashmaps.
    //
    // Reference: https://stackoverflow.com/questions/27849344/find-indices-of-common-elements-in-arraylists-in-java
    public static void main(String[] args) {
        int[][] lists = {{0, 1, 2}, {3, 0, 4}, {5, 6, 0}};

        // Create the hashmap
        Map<Integer, List<Integer>> H = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[0].length; j++) {
                // create the list if this is the first occurrence
                if (!H.containsKey(lists[i][j]))
                    H.put(lists[i][j], new ArrayList<Integer>());

                // add the index to the list
                H.get(lists[i][j]).add(j);
            }
        }

        // Print out indexes for elements that are shared between all lists
        for (Map.Entry<Integer, List<Integer>> e : H.entrySet()) {
            // check that the list of indexes matches the # of lists
            if (e.getValue().size() == lists.length) {
                System.out.println(e.getKey() + ":" + e.getValue());
            }
        }
    }
}
