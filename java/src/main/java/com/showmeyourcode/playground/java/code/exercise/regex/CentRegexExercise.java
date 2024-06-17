package com.showmeyourcode.playground.java.code.exercise.regex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CentRegexExercise {

    public Integer toCents(String price) {
        // your code here
        return price.matches("\\$\\d+\\.\\d{1,2}") ? Integer.valueOf(price.replace("$","").replace(".", "")) : null;
    }
}
