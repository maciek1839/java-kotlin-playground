package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

// Aggregate interface
interface Container {
    IteratorInterface getIterator();
}

// Iterator interface
interface IteratorInterface {
    boolean hasNext();
    Object next();
}

// Concrete Aggregate
class NameRepository implements Container {
    public final String names[] = {"Robert", "John", "Julie", "Lora"};

    @Override
    public IteratorInterface getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements IteratorInterface {
        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// The Iterator pattern provides a way to access the elements of an aggregate object sequentially
// without exposing its underlying representation.
@Slf4j
public class Iterator {

    private Iterator() {
    }

    public static void main(String[] args){
        NameRepository namesRepository = new NameRepository();

        for (IteratorInterface iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            log.info("Name: {}", name);
        }
    }
}
