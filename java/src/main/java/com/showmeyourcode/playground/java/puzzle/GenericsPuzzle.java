package com.showmeyourcode.playground.java.puzzle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericsPuzzle {

    private GenericsPuzzle() {
    }

    private interface Box<T> {
        T method();
    }

    @SuppressWarnings({"java:S1854","java:S125", "java:S3740"})
    // Reference: https://stackoverflow.com/questions/58333231/why-in-java-generics-listinteger-is-a-subtype-of-list-extends-integer
    public static void main(String[] args) {
        // without it there might be an error about variable might not be initialized
        Box<Integer> boxInteger = null;
        Box<? super Integer> boxSuperInteger = null;
        Box<Number> boxNumber = null;
        Box box;

        box = boxInteger;
        box = boxNumber;

        boxNumber = box;
        boxInteger = box;

        // compilation error
        // boxInteger = boxNumber;
        // boxNumber = boxInteger;

        // Look, you have to explicitly say boundaries like this:
        boxSuperInteger = boxInteger;

        log.info("Box: {} boxInteger: {} superBoxInteger: {} boxNumber: {}", box, boxInteger, boxSuperInteger, boxNumber);
    }
}
