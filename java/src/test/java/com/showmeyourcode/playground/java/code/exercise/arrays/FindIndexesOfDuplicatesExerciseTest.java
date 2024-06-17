package com.showmeyourcode.playground.java.code.exercise.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindIndexesOfDuplicatesExerciseTest {

    static Stream<Arguments> shouldFindDuplicateIndexes() {
        return Stream.of(common);
    }

    static Stream<Arguments> shouldFindDuplicateIndexesUsingStreams() {
        return Stream.of(common);
    }

    private static final Arguments[] common = new Arguments[]{
            Arguments.of(
                    new Integer[]{1, 2, 3, 4, 5, 5, 3, 3, 10},
                    new HashMap<Integer, List<Integer>>() {{
                        put(5, List.of(4, 5));
                        put(3, List.of(2, 6, 7));
                    }}
            ),
            Arguments.of(
                    new Integer[]{5, 4, 3, 5, 2, 1, 5},
                    new HashMap<Integer, List<Integer>>() {{
                        put(5, List.of(0, 3, 6));
                    }}
            ),
            Arguments.of(
                    new Integer[]{-1, -2, -3, -4, -5},
                    Collections.emptyMap()
            ),
            Arguments.of(
                    new Integer[]{100, 100},
                    new HashMap<Integer, List<Integer>>() {{
                        put(100, List.of(0, 1));
                    }}
            ),
            Arguments.of(
                    new Integer[]{3, 5, 7, 2, 8},
                    Collections.emptyMap()
            )
    };

    @ParameterizedTest
    @MethodSource
    void shouldFindDuplicateIndexes(Integer[] input, Map<Integer, List<Integer>> expected) {
        assertEquals(expected, FindIndexesOfDuplicatesExercise.findDuplicateIndexes(input));
    }

    @ParameterizedTest
    @MethodSource
    void shouldFindDuplicateIndexesUsingStreams(Integer[] input, Map<Integer, List<Integer>> expected) {
        assertEquals(expected, FindIndexesOfDuplicatesExercise.findDuplicateIndexesUsingStreams(input));
    }

}
