package com.showmeyourcode.playground.java.overview.collections;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Arrays {

    private Arrays(){}

    public static void main(String[] args){
        log.info("\n{} Arrays", Descriptions.INDENT1);
        log.info("""
                An array is a linear data structure that collects elements of the same data type and
                stores them in contiguous and adjacent memory locations.
                
                An array refers to a data structure that contains homogeneous elements.
                This means that all the elements in the array are of the same data type.
                Let's take an example: This is an array of seven elements. All the elements are integers and homogeneous.
                """);

        var mixedTypes = new Object[]{ 1,"String",'a',4.2 };
        log.info("mixed types: {}", mixedTypes);
    }
}
