package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

// The Memento pattern provides the ability to restore an object to its previous state.
// https://www.baeldung.com/java-memento-design-pattern
@Slf4j
public record Memento(String state) {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        log.info("Current State: {}", originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        log.info("First saved State: {}", originator.getState());

        originator.getStateFromMemento(caretaker.get(1));
        log.info("Second saved State: {}", originator.getState());
    }
}

// Originator class
@Getter
@Setter
class Originator {

    private String state;

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.state();
    }
}

// Caretaker class
class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
