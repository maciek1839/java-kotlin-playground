package com.showmeyourcode.playground.java.code.exercise.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfIntegers;
import static com.showmeyourcode.playground.java.CommonTestUtil.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubstringFinderExerciseTest {

    private final SubstringFinderExercise finder = new SubstringFinderExercise();

    @ParameterizedTest
    @CsvSource({
            "'Hello, world!','world',7",
            "'Hello, world!', 'Java', -1",
            "'Java programming', 'Java',0",
            "'Java programming', 'programming',5",
            "'', 'non-empty',-1",
            "'','',0",
            "'a','a',0",
            "'a','b',-1"
    })
    void shouldFindSubstringIndex(String input, String substring, int expectedIndex) {
        assertEquals(expectedIndex, finder.findSubstring(input, substring));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFindingSubstringAndArgumentIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring("test", null));
        assertThrows(IllegalArgumentException.class, () -> finder.findSubstring(null, null));
    }

    @ParameterizedTest
    @CsvSource({
            "'Hello, world! Hello again!','Hello','0,14'",
            "'Hello, world!', 'Java', ''",
            "'Java programming', 'Java',0",
            "'Java programming', 'programming',5",
            "'', 'non-empty',''",
            "'aaaaa','a','0,1,2,3,4'",
            "'a','a',0",
            "'a','b',''",
            "'abcXdefXghiX','X','3,7,11'"
    })
    void shouldFindAllSubstrings(String input, String substring, String expectedIndexes) {
        assertEquals(toList(parseRowOfIntegers(expectedIndexes)), finder.findAllSubstrings(input, substring));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFindingAllSubstringAndArgumentIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings("test", null));
        assertThrows(IllegalArgumentException.class, () -> finder.findAllSubstrings(null, null));
    }
}
