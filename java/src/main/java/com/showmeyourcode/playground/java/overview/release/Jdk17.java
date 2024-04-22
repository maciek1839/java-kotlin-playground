package com.showmeyourcode.playground.java.overview.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Jdk17 {

    private Jdk17(){}

    public static void main(){
        LOGGER.info("{} JDK 17", Descriptions.INDENT1);
        LOGGER.info("JDK 17 reached General Availability on 14th September 2021.");
        LOGGER.info("https://www.oracle.com/java/technologies/javase/17-relnote-issues.html");

        LOGGER.info("\n{} feature1", Descriptions.INDENT2);
    }
}
// https://docs.oracle.com/en/java/javase/17/language/pattern-matching.html
