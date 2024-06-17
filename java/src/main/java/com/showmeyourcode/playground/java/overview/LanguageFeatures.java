package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LanguageFeatures {

    private LanguageFeatures(){}

    public static void main(String[] args){
        log.info(Descriptions.header(Descriptions.LANGUAGE_FEATURES));
        log.info("Below some Java features/syntax good to know:");

        log.info("\n{}  Ternary operator", Descriptions.INDENT1);
        log.info("variable = (condition) ? expressionTrue :  expressionFalse");
    }
}
