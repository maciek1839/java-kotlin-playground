package com.showmeyourcode.playground.java.overview.collections;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class Lists {

    private Lists(){}

    public static void main(String[] args){
        log.info("\n{} Lists", Descriptions.INDENT1);
        log.info("All dynamic arrays implement an interface called List.");
        log.info("""
                
                The most important thing about a list is that it is an ordered collection.
                You can also call it a sequence.
                In Java, lists are homogeneous, that is, the elements of the list are of the same data type.
                """);

        List<Object> mixedTypes = List.of(1,"String",'a',4.2);
        log.info("mixed types: {}", mixedTypes);
    }
}
