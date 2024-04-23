package com.showmeyourcode.playground.java.overview.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class JavaReleases {

    private JavaReleases(){}

    public static void main(){
        LOGGER.info(Descriptions.header("Java Releases"));
        LOGGER.info("""
                For product releases after Java SE 8,
                Oracle will designate only certain releases as Long-Term-Support (LTS) releases.
                Fo instance, Java SE 8, 11, 17, 21, 25 are LTS releases.
                Oracle intends to make future LTS releases every two years.
                
                A Java LTS version, or long term support version,
                is a Java version that will remain the industry standard for several years.
                
                https://www.oracle.com/java/technologies/java-se-support-roadmap.html
                
                The website endoflife.date track JDK releases and support dates: https://endoflife.date/oracle-jdk
                """);
        Jdk21.main();
        Jdk17.main();
        Jdk11.main();
        Jdk8.main();
    }
}
