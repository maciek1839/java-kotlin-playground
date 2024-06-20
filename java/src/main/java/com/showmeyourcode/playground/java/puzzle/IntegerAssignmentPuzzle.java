package com.showmeyourcode.playground.java.puzzle;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * How are Integers managed in Java?
 * Do they share common reference when assigning one to another variable?
 * <br>
 * <br>
 * Reference: <a href="https://stackoverflow.com/questions/39997078/shallow-copy-integer-in-java">Shallow copy Integer in Java</a>
 * <br>
 * <br>
 * Explanation:
 * In java java.lang.Integer is immutable. It means that you can't change the value of Integer object (in usual way).
 * Java always creates new object on assigning new integer literal because Integer is immutable.
 * For ex: i1=233; // it represent as i1= new Integer(233);
 * <br>
 *
 */
@Slf4j
public class IntegerAssignmentPuzzle {

    private IntegerAssignmentPuzzle() {
    }

    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(11);
        Integer i2 ;
        i2 = i1;
        log.info("Integers: {} {}", i1, i2);
        i1 = 233;
        log.info("Integers: {} {}", i1, i2);
        // now use AtomicInteger
        AtomicInteger ai1 = new AtomicInteger(11);
        AtomicInteger ai2 ;
        ai2 = ai1;
        log.info("Atomic: {} {}", ai1, ai2);
        ai1.set(233);
        log.info("Atomic: {} {}", ai1, ai2);
    }
}
