package com.showmeyourcode.playground.java.code.exercise.misc;

import java.util.ArrayList;
import java.util.List;


/**
 * Take an integer n as input and return a list of prime factors of n.
 */
public class PrimeFactorsExercise {

    private PrimeFactorsExercise() {
    }

    public static List<Integer> generatePrimeFactors(int n) {
        List<Integer> primeFactors = new ArrayList<>();

        // Divide the number by 2 until it is divisible
        // and add 2 as prime factor each time
        while (n % 2 == 0) {
            primeFactors.add(2);
            n /= 2;
        }

        // Check for odd factors starting from 3
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }

        // If the number is a prime greater than 2
        if (n > 2) {
            primeFactors.add(n);
        }

        return primeFactors;
    }
}
