package com.showmeyourcode.playground.java.code;

import com.showmeyourcode.playground.java.code.pattern.behavioral.BehavioralDesignPatterns;
import com.showmeyourcode.playground.java.code.pattern.creational.CreationalDesignPatterns;
import com.showmeyourcode.playground.java.code.pattern.structural.StructuralDesignPatterns;
import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Samples {

    private Samples(){}

    public static void main(){
        LOGGER.info(Descriptions.header(Descriptions.CODE_SAMPLES));
        Arrays.main();
        Lists.main();
        Classes.main();
        Functions.main();
        Futures.main();
        Concurrency.main();
        CreationalDesignPatterns.main();
        StructuralDesignPatterns.main();
        BehavioralDesignPatterns.main();
    }
}
