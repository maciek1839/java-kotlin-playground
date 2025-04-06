package com.showmeyourcode.playground.java.overview;

import lombok.extern.slf4j.Slf4j;

/**
 * A recursive function solves a particular problem by calling a copy of itself and solving smaller subproblems of the original problems.
 * https://www.geeksforgeeks.org/introduction-to-recursion-data-structure-and-algorithm-tutorials/
 *
 * Source code: https://developer.ibm.com/articles/l-recurs/
 *
 * What is Tail Recursion?
 * Tail recursion is defined as a recursive function in which the recursive call is the last statement that is executed by the function.
 * So basically nothing is left to execute after the recursion call.
 * Ref: https://www.geeksforgeeks.org/tail-recursion/
 */
@Slf4j
public class TailRecursionExample {

    public static void main(String[] args) {
        log.info("Factorial: {}", new TailRecursionExample().factorial(10));
    }

    int factorial(int n) {
        if(n <= 1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
}
