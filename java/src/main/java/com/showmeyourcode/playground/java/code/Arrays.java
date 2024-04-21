package com.showmeyourcode.playground.java.code;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Arrays {

    private Arrays(){}

    public static void main(){
        LOGGER.info("\n====> Arrays");
        var mixedTypes = new Object[]{ 1,"String",'a',4.2 };
        LOGGER.info("mixed types: {}", mixedTypes);
    }
}
