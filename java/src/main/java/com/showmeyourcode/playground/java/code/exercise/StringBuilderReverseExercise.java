package com.showmeyourcode.playground.java.code.exercise;

public class StringBuilderReverseExercise {

    public static void main(String[] args) {
        String life = "life";
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<life.length();i++){
            stringBuilder.append(life.charAt(i));
        }
    }
}
