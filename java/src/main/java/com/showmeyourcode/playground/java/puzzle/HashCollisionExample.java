package com.showmeyourcode.playground.java.puzzle;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;

@Slf4j
public class HashCollisionExample {

    public static class Test{

        String string;
        int integer;

        private Test(String string, int integer) {
            this.string = string;
            this.integer = integer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Test test = (Test) o;
            return integer == test.integer && Objects.equals(string, test.string);
        }

        @Override
        public int hashCode() {
            // this is correct:
            return Objects.hash(string, integer);
            // return Objects.hash(string); // a wrong implementation!
        }
    }

    public static void main(String[] args) {
        var t1 = new Test("test", 1);
        var t2 = new Test("test", 2);

        var map = new HashSet<Test>();
        map.add(t1);
        map.add(t2);
        log.info("Size: {}", map.size());
    }
}
