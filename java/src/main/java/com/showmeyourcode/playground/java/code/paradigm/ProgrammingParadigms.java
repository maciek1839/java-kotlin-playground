package com.showmeyourcode.playground.java.code.paradigm;

import com.showmeyourcode.playground.java.code.paradigm.functional.FunctionalProgramming;
import com.showmeyourcode.playground.java.code.paradigm.oop.ObjectOrientedProgramming;
import com.showmeyourcode.playground.java.code.paradigm.prcedural.ProceduralProgramming;
import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProgrammingParadigms {

    public static void main(String[] args) {
        log.info(Descriptions.header(Descriptions.PROGRAMMING_PARADIGMS));
        log.info(Descriptions.PROGRAMMING_PARADIGM);
        FunctionalProgramming.main(new String[0]);
        ProceduralProgramming.main(new String[0]);
        ObjectOrientedProgramming.main(new String[0]);
    }
}
