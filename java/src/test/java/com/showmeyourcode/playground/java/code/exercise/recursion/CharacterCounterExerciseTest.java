package com.showmeyourcode.playground.java.code.exercise.recursion;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CharacterCounterExerciseTest {

    @Test
    void shouldRunMainMethodWithoutErrors(){
        assertDoesNotThrow(() -> CharacterCounterExercise.main(new String[]{}));
    }

    @Test
    void shouldCountAllCharacters(){
        String str = "XaaaYaaaZaaaYaaaaY";

        Map<Character, Integer> counterCharacters = CharacterCounterExercise.countEachChar(str);

        assertEquals(4,counterCharacters.size());
        assertEquals(1,counterCharacters.get('X'));
        assertEquals(13,counterCharacters.get('a'));
        assertEquals(3,counterCharacters.get('Y'));
        assertEquals(1,counterCharacters.get('Z'));
        assertNull(counterCharacters.get('z'));
    }
}
