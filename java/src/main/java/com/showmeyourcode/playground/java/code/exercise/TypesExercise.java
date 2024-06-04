package com.showmeyourcode.playground.java.code.exercise;

public class TypesExercise {

    class Box<T> {

    }

    // Reference: https://stackoverflow.com/questions/4902723/why-cant-a-generic-type-parameter-have-a-lower-bound-in-java
    public static void main(String[] args) {
        // without it there might be an error about variable might not be initialized
        Box<Integer> boxInteger = null;
        Box<Number> boxNumber = null;
        Box box = null;

        box = boxInteger;
        box = boxNumber;

        boxNumber = box;
        boxInteger = box;

        // compilation error
        // boxInteger = boxNumber;
        // boxNumber = boxInteger;
    }
}
