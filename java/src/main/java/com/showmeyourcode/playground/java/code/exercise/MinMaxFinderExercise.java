package com.showmeyourcode.playground.java.code.exercise;

class MinMaxFinderExercise {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public int findMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MinMaxFinderExercise finder = new MinMaxFinderExercise();
        int[] nums = {3, 5, 1, 2, 4, 8, -1};

        System.out.println("Min: " + finder.findMin(nums)); // Output: -1
        System.out.println("Max: " + finder.findMax(nums)); // Output: 8
    }
}
