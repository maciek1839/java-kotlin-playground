package com.showmeyourcode.playground.java.code.exercise.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class MessageValidatorExerciseTest {

    static Stream<Arguments> shouldCorrectlyValidateMessage(){
        return Stream.of(
                Arguments.of(
                        "3hey5hello2hi",
                        true
                ),
                Arguments.of(
                        "4code13hellocodewars",
                        true
                ),
                Arguments.of(
                        "hello5",
                        false
                ),
                Arguments.of(
                        "2hi2",
                        false
                ),
                Arguments.of(
                        "",
                        true
                )
        );
    }


    @ParameterizedTest
    @MethodSource
    void shouldCorrectlyValidateMessage(String message, boolean isValid){
        Assertions.assertEquals(isValid, MessageValidatorExercise.isValidMessage(message));
    }
}
