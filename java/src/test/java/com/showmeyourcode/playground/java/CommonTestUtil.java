package com.showmeyourcode.playground.java;

import java.util.Arrays;
import java.util.List;

public class CommonTestUtil {

    private CommonTestUtil() {
    }

    public static int[] parseRowOfInts(String row) {
        return Arrays.stream(row.split(",")).filter(s->!s.isBlank()).map(String::trim).mapToInt(Integer::valueOf).toArray();
    }

    public static Integer[] parseRowOfIntegers(String row) {
        return Arrays.stream(parseRowOfInts(row)).boxed().toArray(Integer[]::new);
    }

    public static List<Integer> toList(Integer[] arr){
        return Arrays.stream(arr).toList();
    }
}
