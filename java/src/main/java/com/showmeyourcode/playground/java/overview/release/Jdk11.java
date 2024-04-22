package com.showmeyourcode.playground.java.overview.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Jdk11 {

    private Jdk11(){}

    public static void main(){
        LOGGER.info("\n{} JDK 11", Descriptions.INDENT1);
        LOGGER.info("JDK 11 was released on 25th September 2018.");
        LOGGER.info("https://www.oracle.com/java/technologies/javase/11-relnote-issues.html");

        LOGGER.info("\n{} feature1", Descriptions.INDENT2);
    }
}
