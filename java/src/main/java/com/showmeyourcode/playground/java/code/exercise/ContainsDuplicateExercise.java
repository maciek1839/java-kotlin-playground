package com.showmeyourcode.playground.java.code.exercise;

import java.util.HashSet;

class ContainsDuplicateExercise {

    public static void main(String[] args) {
        ContainsDuplicateExercise solution = new ContainsDuplicateExercise();

        int[] nums1 = {1, 2, 3, 4};
        System.out.println(solution.containsDuplicate(nums1)); // Output: false

        int[] nums2 = {1, 2, 3, 1};
        System.out.println(solution.containsDuplicate(nums2)); // Output: true

        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(solution.containsDuplicate(nums3)); // Output: true

        int[] nums4 = {};
        System.out.println(solution.containsDuplicate(nums4)); // Output: false

        int[] nums5 = {1};
        System.out.println(solution.containsDuplicate(nums5)); // Output: false
    }

    public boolean containsDuplicate(int[] nums) {
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
