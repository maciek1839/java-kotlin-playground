package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Jvm {

    private Jvm(){}

    public static void main(){
        LOGGER.info(Descriptions.header("JVM specific"));

        LOGGER.info("""
                {} The Java string constant pool is an area in heap memory where Java stores literal string values.
                Heap space is used for the dynamic memory allocation of Java objects and classes at runtime
                """,
                Descriptions.INDENT1
        );
    }
}
