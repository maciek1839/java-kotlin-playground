package com.showmeyourcode.playground.java.puzzle;

import lombok.extern.slf4j.Slf4j;

/**
 * Check the 'main' and 'modify' methods and tell the program output.
 * How do Strings behave in Java?
 * <br>
 * <br>
 * <a href="https://stackoverflow.com/questions/1270760/passing-a-string-by-reference-in-java">Question source</a>
 * <br>
 * <br>
 * Answer:
 * String is a special class in Java.
 * It is Thread Safe which means
 * "Once a String instance is created, the content of the String instance will never change".
 */
@Slf4j
public class StringReferencePuzzle {

    public static void main(String[] args) {
        String a ="modify me";
        String b = modify(a);
        log.info("a: {} b: {}", a, b);
    }

    static String modify(String s){
        log.info("I am modifying...");
        s = s + " EXTRA CHARACTERS";
        return s;
    }

}
