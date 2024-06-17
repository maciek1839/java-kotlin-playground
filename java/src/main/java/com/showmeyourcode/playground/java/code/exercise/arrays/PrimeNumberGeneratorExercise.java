package com.showmeyourcode.playground.java.code.exercise.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * The prime numbers from 1 to 100 are:
 * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97.
 * <br>
 * <br>
 * The sieve of Eratosthenes is one of the most efficient ways to find all primes smaller than n when n is smaller than 10 million or so.
 *
 * <br>
 * <br>
 * Reference: <a href="https://www.baeldung.com/java-generate-prime-numbers">Java - generate prime numbers</a>
 */
public class PrimeNumberGeneratorExercise {

    private PrimeNumberGeneratorExercise() {
    }

    static List<Integer> primeNumbersBruteForce(int toInclusive) {
        IntPredicate isPrimeBruteForce = number -> {
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        };

        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= toInclusive; i++) {
            if (isPrimeBruteForce.test(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    // Java 8 bruteForce
    static List<Integer> primeNumbersStream(int toInclusive) {
        IntPredicate isPrime = n -> {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        return IntStream.rangeClosed(2, toInclusive)
                .filter(isPrime)
                .boxed()
                .toList();
    }

    static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime numbers

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false; // Mark multiples of p as non-prime
                }
            }
        }

        return IntStream.rangeClosed(2, n)
                .filter(i -> isPrime[i])
                .boxed()
                .toList();
    }
}
