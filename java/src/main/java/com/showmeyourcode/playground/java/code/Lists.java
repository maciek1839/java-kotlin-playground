package com.showmeyourcode.playground.java.code;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import java.util.List;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Lists {

    private Lists(){}

    public static void main(){
        LOGGER.info("\n{} Lists", Descriptions.INDENT1);
        List<Object> mixedTypes = List.of(1,"String",'a',4.2);
        LOGGER.info("mixed types: {}", mixedTypes);
    }
}
