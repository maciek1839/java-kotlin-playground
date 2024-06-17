package com.showmeyourcode.playground.java.code.paradigm.prcedural;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProceduralProgramming {

    private ProceduralProgramming() {
    }

    // Reference: https://java-programming.mooc.fi/part-7/1-programming-paradigms
    public static void main(String[] args) {
        // comparing to OOP, in OOP you will create objects and delegate below method to related objects e.g. clok
        print(14, 44, 0);
    }

    public static void print(int hours, int minutes, int seconds) {
        print(hours);
        print(minutes);
        print(seconds);
    }

    public static void print(int value) {
        if (value < 10) {
            log.info("0");

            log.info("{}", value);
        }
    }
}
