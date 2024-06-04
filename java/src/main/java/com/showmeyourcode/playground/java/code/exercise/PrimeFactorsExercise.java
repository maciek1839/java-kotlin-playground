package com.showmeyourcode.playground.java.code.exercise;

import java.util.ArrayList;
import java.util.List;

// This method takes an integer n as input and returns a list of prime factors of n. It first divides the number by 2 until it's divisible and adds 2 as a prime factor each time. Then, it checks for odd factors starting from 3 up to the square root of n. If a factor is found, it's added to the list of prime factors, and the number is divided by that factor. Finally, if the remaining number is a prime greater than 2, it's also added to the list.
public class PrimeFactorsExercise {

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

    public static void main(String[] args) {
        int number = 315;
        List<Integer> primeFactors = generatePrimeFactors(number);
        System.out.println("Prime factors of " + number + " are: " + primeFactors);
    }
}
