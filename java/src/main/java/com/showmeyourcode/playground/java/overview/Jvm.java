package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jvm {

    private Jvm(){}

    public static void main(String[] args){
        log.info(Descriptions.header("JVM specific"));

        log.info("""
                {} The Java string constant pool is an area in heap memory where Java stores literal string values.
                Heap space is used for the dynamic memory allocation of Java objects and classes at runtime
                """,
                Descriptions.INDENT1
        );
    }
}
