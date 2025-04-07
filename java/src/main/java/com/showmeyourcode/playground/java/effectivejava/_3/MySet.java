package com.showmeyourcode.playground.java.effectivejava._3;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Favour static member classes over nonstatic
//
// If you declare a member class that does not require access to an enclosing instance,
// always put the static modifier in its declaration.
//
// If you omit this modifier, each instance will have a hidden extraneous reference to its enclosing instance
// and it will take time and space.
public class MySet {

    private final String[] elements;
    private int size;

    public MySet(String... initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    public Iterator<String> iterator() {
        return new MyIterator(elements, size);
    }

    // Static nested class – does NOT hold a reference to MySet instance
    private static class MyIterator implements Iterator<String> {

        private final String[] data;
        private final int length;
        private int currentIndex = 0;

        public MyIterator(String[] data, int length) {
            this.data = data;
            this.length = length;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[currentIndex++];
        }
    }

    public static void main(String[] args) {
        MySet set = new MySet("apple", "banana", "cherry");

        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }
}
