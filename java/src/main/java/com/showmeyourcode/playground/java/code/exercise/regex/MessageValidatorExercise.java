package com.showmeyourcode.playground.java.code.exercise.regex;

import java.util.regex.*;

/**
 *  In this kata, you have an input string and you should check whether it is a valid message. To decide that, you need to split the string by the numbers, and then compare the numbers with the number of characters in the following substring.
 *
 * For example "3hey5hello2hi" should be split into 3, hey, 5, hello, 2, hi and the function should return true, because "hey" is 3 characters, "hello" is 5, and "hi" is 2; as the numbers and the character counts match, the result is true.
 *
 * Notes:
 *
 * Messages are composed of only letters and digits
 * Numbers may have multiple digits: e.g. "4code13hellocodewars" is a valid message
 * Every number must match the number of character in the following substring, otherwise the message is invalid: e.g. "hello5" and "2hi2" are invalid
 * If the message is an empty string, you should return true
 */
public class MessageValidatorExercise {
    public static boolean isValidMessage(String message) {
        if (message.isEmpty()) return true;

        Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]*)");
        Matcher matcher = pattern.matcher(message);

        int lastMatchEnd = 0;

        while (matcher.find()) {
            if (matcher.start() != lastMatchEnd) {
                return false; // Invalid format
            }

            int num = Integer.parseInt(matcher.group(1));
            String text = matcher.group(2);

            if (text.length() != num) {
                return false;
            }

            lastMatchEnd = matcher.end();
        }

        return lastMatchEnd == message.length();
    }
}
