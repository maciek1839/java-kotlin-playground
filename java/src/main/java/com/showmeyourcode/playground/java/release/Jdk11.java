package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jdk11 {

    private Jdk11() {
    }

    public static void main() {
        log.info("\n{} JDK 11", Descriptions.INDENT1);
        log.info("JDK 11 was released on 25th September 2018.");
        log.info("https://www.oracle.com/java/technologies/javase/11-relnote-issues.html");
        log.info("Features overview: https://www.digitalocean.com/community/tutorials/java-11-features");
        log.info("\n{} Code examples from previous and current release", Descriptions.INDENT2);
    }
}


