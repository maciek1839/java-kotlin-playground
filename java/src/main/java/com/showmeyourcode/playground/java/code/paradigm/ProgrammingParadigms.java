package com.showmeyourcode.playground.java.code.paradigm;

import com.showmeyourcode.playground.java.code.paradigm.functional.FunctionalProgramming;
import com.showmeyourcode.playground.java.code.paradigm.oop.ObjectOrientedProgramming;
import com.showmeyourcode.playground.java.code.paradigm.prcedural.ProceduralProgramming;
import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class ProgrammingParadigms {

    public static void main(String[] args) {
        LOGGER.info(Descriptions.header(Descriptions.PROGRAMMING_PARADIGMS));
        LOGGER.info(Descriptions.PROGRAMMING_PARADIGM);
        FunctionalProgramming.main();
        ProceduralProgramming.main();
        ObjectOrientedProgramming.main();
    }
}
