package com.showmeyourcode.playground.java.code.exercise.ccsu.arrays.chap49D;

// This program prompts the user to enter an ISBN,
// removes dashes and converts 'X' to 10 if it's the last character.
// Then it calculates the check digit based on the first nine digits and compares it with the last digit of the ISBN.
// If they match, it prints "ISBN is correct"; otherwise, it prints "ISBN is incorrect".
//
// https://chortle.ccsu.edu/java5/Notes/chap49D/progExercises49D.html
public class ISBNVerifierExercise {
    public static void main(String[] args) {
        String[] isbns = {
                "0-201-48558-3",
                "0-670-03441-X",
                "1-4000-3136-2",
                "0-201-48558-2",
                "0-670-03441-9",
                "1-4000-3136-3",
                "0-201-48558-X",
                "0-670-03441-1",
                "1-4000-3136-0"
        };

        for (String isbn : isbns) {
            if (isValidISBN(isbn)) {
                System.out.println(isbn + " is correct.");
            } else {
                System.out.println(isbn + " is incorrect.");
            }
        }
    }

    public static boolean isValidISBN(String isbn) {
        // Remove dashes and convert 'X' to 10
        String isbnDigitsOnly = isbn.replaceAll("-", "").toUpperCase();
        if (isbnDigitsOnly.charAt(isbnDigitsOnly.length() - 1) == 'X') {
            isbnDigitsOnly = isbnDigitsOnly.substring(0, isbnDigitsOnly.length() - 1) + "10";
        }

        // Calculate check digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(isbnDigitsOnly.charAt(i)) * (10 - i);
        }
        int checkDigit = (11 - (sum % 11)) % 11;

        // Check if the calculated check digit matches the last digit of ISBN
        char lastDigit = isbnDigitsOnly.charAt(isbnDigitsOnly.length() - 1);
        return checkDigit == 10 && lastDigit == 'X' || checkDigit == Character.getNumericValue(lastDigit);
    }
}
