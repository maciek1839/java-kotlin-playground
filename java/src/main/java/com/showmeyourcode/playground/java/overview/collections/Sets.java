package com.showmeyourcode.playground.java.overview.collections;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

@Slf4j
public class Sets {

    private Sets(){}

    public static void main(String[] args) {
        log.info("\n{} Sets", Descriptions.INDENT1);
        log.info("It is an unordered collection of objects in which duplicate values cannot be stored.");

        var hashSet = new HashSet<>(List.of(5, 5, 10, 10, 3, 3));
        var sortedSet = new TreeSet<>(List.of(5, 5, 10, 10, 3, 3));
        var linkedSet = new LinkedHashSet<>(List.of(5, 5, 10, 10, 3, 3));
        log.info("HashSet: {}", hashSet);
        log.info("SortedSet: {}", sortedSet);
        log.info("LinkedSet: {}", linkedSet);
    }
}
