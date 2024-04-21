package com.showmeyourcode.playground.java.overview;


import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class LanguageFeatures {

    private LanguageFeatures(){}

    public static void main(){
        LOGGER.info(Descriptions.header(Descriptions.LANGUAGE_FEATURES));
        LOGGER.info("Below some Java features/syntax good to know:");

        LOGGER.info("\n====>  Ternary operator");
        LOGGER.info("variable = (condition) ? expressionTrue :  expressionFalse");
    }
}
