package com.showmeyourcode.playground.java.code.exercise.arrays;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
class ContainsDuplicateExercise {

    private ContainsDuplicateExercise() {
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
