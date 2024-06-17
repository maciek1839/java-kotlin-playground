package com.showmeyourcode.playground.java.code.exercise.streams;

import com.showmeyourcode.playground.java.CommonTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfIntegers;
import static com.showmeyourcode.playground.java.CommonTestUtil.toList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamReduceExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> StreamReduceExercise.main(new String[]{}));
    }

    @ParameterizedTest
    @CsvSource({
            "'a,null,b,null,c', 'a,b,c'",
            "'1,2,null,3,4,null', '1,2,3,4'",
            "'hello,null,world', 'hello,world'",
            "'null,null,null', ''",
            "'x,y,z', 'x,y,z'"
    })
    void testFilterNulls(String inputCsv, String expectedCsv) {
        String[] input = Arrays.stream(inputCsv.split(",")).map(s-> Objects.equals(s, "null") ? null : s).toArray(String[]::new);
        String[] expected = expectedCsv.isEmpty() ? new String[]{} : expectedCsv.split(",");
        String[] result = StreamReduceExercise.filterNulls(input);
        assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5',15",
            "'10,20,30,40,50',150",
            "'-1,-2,-3,-4,-5',-15",
            "'0,0,0,0,0',0"
    })
    void testSum(String numbersCsv, int expected) {
        List<Integer> numbers = toList(parseRowOfIntegers(numbersCsv));
        int result = StreamReduceExercise.sum(numbers);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'Alice,Bob,Charlie', 'AliceBobCharlie'",
            "'Java,Python,JavaScript', 'JavaPythonJavaScript'",
            "'Hello,World', 'HelloWorld'",
            "'Apple,Banana,Cherry', 'AppleBananaCherry'"
    })
    void testConcatenate(String lettersCsv, String expected) {
        List<String> letters = Arrays.asList(lettersCsv.split(","));
        String result = StreamReduceExercise.concatenate(letters);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'Alice,Bob,Charlie',;,'Alice;Bob;Charlie'",
            "'Java,Python,JavaScript',|,'Java|Python|JavaScript'",
            "'Hello,World',' ', Hello World",
            "'Apple,Banana,Cherry',-,Apple-Banana-Cherry"
    })
    void testConcatenate(String lettersCsv, String separator, String expected) {
        List<String> letters = Arrays.asList(lettersCsv.split(","));
        String result = StreamReduceExercise.concatenate(letters, separator);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "10,5,2",
            "15,3,5",
            "20,2,10",
            "12,3,4"
    })
    void testDivideListElements(String valuesCsv, int divider, int expected) {
        List<Integer> values = toList(parseRowOfIntegers(valuesCsv));
        int result = StreamReduceExercise.divideListElements(values, divider);
        assertEquals(expected, result);
    }
}
