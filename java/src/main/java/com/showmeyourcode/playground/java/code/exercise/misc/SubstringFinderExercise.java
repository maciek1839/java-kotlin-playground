package com.showmeyourcode.playground.java.code.exercise.misc;

import java.util.ArrayList;
import java.util.List;

public class SubstringFinderExercise {

    public int findSubstring(String str, String substr) {
        if (str == null || substr == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        return str.indexOf(substr);
    }

    public List<Integer> findAllSubstrings(String str, String substr) {
        if (str == null || substr == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        var indexes = new ArrayList<Integer>();
        int idx = str.indexOf(substr);
        while (idx >= 0) {
            indexes.add(idx);
            idx = str.indexOf(substr, idx + substr.length());
        }

        return indexes;
    }
}
