package com.showmeyourcode.playground.java.puzzle;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

/**
 * Check out not obvious Java collections behaviour.
 * <br>
 * <br>
 * <a href="https://www.youtube.com/watch?v=w6hhjg_gt_M&t=1">Reference</a>
 */
@Slf4j
public class JavaCollectionsPuzzle {

    @SuppressWarnings("java:S4143")
    public static void main(String[] args) {
        var arrayString = new String[]{"one", "two", "three"};
        var stringList = Arrays.asList(arrayString);

        int[] arrayInt = new int[]{1, 2, 3};
        var intList = Arrays.asList(arrayInt);

        log.info("Does contain 'one' ? {}",  stringList.contains("one"));
        // The java.util.Collection type and its subtypes provide methods
        // to access and modify collections such as
        // Collection.remove(Object o) and Collection.contains(Object o).
        // Some of these methods accept arguments of type java.lang.Object
        // and will compare said argument with objects already in the collection.
        // If the actual type of the argument is unrelated to the type of object
        // contained in the collection, these methods will always return
        // false, null, or -1.
        // This behavior is most likely unintended and can be indicative of a design issue.
        log.info("Does contain 1 ? {}", intList.contains(1));
        log.info("Integer.valueOf(1) == 1 ? {}", Integer.valueOf(1) == 1);

        // =================================================

        String[] strings = {"a", "b", "c", null};
        var strings1 = Stream.of(strings).toList();
        log.info("strings size: {}", strings1.size());

        // var strings2 = List.of(strings); // NullPointerException!

        List<String> strings3 = Arrays.asList(strings);
        // strings3.removeIf(Objects::isNull); // error! UnsupportedOperationException
        log.info("strings3 size: {}", strings3.size());

        // =================================================

        Map<Integer, String> map = new HashMap<>();
        map.put(4, null);

        log.info("map.get(4): {}", map.getOrDefault(4, "four"));

        map.putIfAbsent(4, "four");
        log.info("map.get(4): {}", map.get(4));
        log.info("map.get(0): {}", map.get(0));

        // =================================================

        var numbers = List.of(-1, 0, 1);
        Map<Integer, List<Integer>> map2 = new HashMap<>();
        numbers.forEach(number->{
            // putIfAbsent returns a value and it's null as the previous value does not exists, check documentation
            log.info("{}, {}", number, map2.putIfAbsent(number, new ArrayList<>()));
        });
        // normal operation, nothing extraordinary
        numbers.forEach(number-> map2.computeIfAbsent(number, ArrayList::new).add(number));

        // =================================================
        // does not allow to have null elements - NullPointerException
        Set<String> hashSet = new HashSet<>(List.of("a","b","c"));
        Set<String> treeSet = new TreeSet<>(CASE_INSENSITIVE_ORDER);

        treeSet.addAll(List.of("A","B","C"));

        log.info("treeSet.equals(hashSet): {}", treeSet.equals(hashSet));
        log.info("hashSet.equals(treeSet): {}", hashSet.equals(treeSet));

        // =================================================

        var map3 = new IdentityHashMap<Integer, String>();
        map3.put(1, "one");
        map3.put(10, "ten ");
        map3.put(100, "hundred");
        map3.put(1000, "thousand");

        map3.put(1, "one again");
        map3.put(10, "one ten");
        map3.put(100, "one hundred");
        map3.put(1000, "one thousand");


        log.info("map3.size: {}", map3.size());

        // =================================================

        Map<String, Integer> map4 = LinkedHashMap.newLinkedHashMap(3);
        map4.put("a",1);
        map4.put("b",2);
        map4.put("c",3);
        map4.put("b",4);
        log.info("map4.get(\"a\"): {}", map4.get("a"));
        log.info("map4: {}", map4);

        // =================================================

        var ints2 = List.of(1,2,3);
        var ints22 = Arrays.asList(1,2,3);
        var ints2uc = Collections.unmodifiableCollection(ints2);
        var lints22uc = Collections.unmodifiableList(ints2);

        // It is stored in the heap. Array is an object, and objects are stored at heap.
        // https://www.quora.com/Is-java-array-of-primitives-stored-in-stack-or-heap
        log.info("ints22.equals(ints2uc): {}", ints22.equals(ints2uc));
        log.info("ints22.equals(lints22uc): {}", ints22.equals(lints22uc));
        log.info("ints2uc.equals(lints22uc): {}", ints2uc.equals(lints22uc));

    }
}
