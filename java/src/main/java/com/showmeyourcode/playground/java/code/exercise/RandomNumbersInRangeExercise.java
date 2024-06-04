package com.showmeyourcode.playground.java.code.exercise;

public class RandomNumbersInRangeExercise {

    public static void main(String[] args) {
        int upper = 22, lower = 3;
        int r = (int) (Math.random() * (upper - lower)) + lower;
        System.out.println("Result: "+r);
    }
}
