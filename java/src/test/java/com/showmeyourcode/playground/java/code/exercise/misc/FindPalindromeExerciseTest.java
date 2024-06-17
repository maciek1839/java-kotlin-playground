package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindPalindromeExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'radar', true",
            "'Level', true",
            "'A man, a plan, a canal, Panama!', true",
            "hello, false",
            "'Was it a car or a cat I saw?', true",
            "'No 'x' in Nixon', true",
            "'not a palindrome', false"
    })
    void shouldDetectPalindrome(String input, boolean isPalindrome){
        assertEquals(isPalindrome, FindPalindromeExercise.isPalindrome(input));
    }
}
