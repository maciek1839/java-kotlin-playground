package com.showmeyourcode.playground.java.code.pattern;

import com.showmeyourcode.playground.java.code.pattern.behavioral.BehavioralDesignPatterns;
import com.showmeyourcode.playground.java.code.pattern.creational.CreationalDesignPatterns;
import com.showmeyourcode.playground.java.code.pattern.structural.StructuralDesignPatterns;
import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class DesignPatterns {

    public static void main(String[] args) {
        LOGGER.info(Descriptions.header(Descriptions.DESIGN_PATTERNS));
        CreationalDesignPatterns.main(new String[]{});
        StructuralDesignPatterns.main(new String[]{});
        BehavioralDesignPatterns.main(new String[]{});
    }
}
